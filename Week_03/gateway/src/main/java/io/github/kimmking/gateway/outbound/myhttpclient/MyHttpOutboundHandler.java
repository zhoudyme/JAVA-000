package io.github.kimmking.gateway.outbound.myhttpclient;

import io.github.kimmking.gateway.filter.Impl.HttpRequestFilterImpl;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * 整合上次HttpClient的代码，以及实现过滤器功能
 *
 * @author zhoudy
 * @date 2020/11/03
 */
public class MyHttpOutboundHandler {

    private String backendUrl;

    public MyHttpOutboundHandler(String backendUrl) {
        this.backendUrl = backendUrl.endsWith("/") ? backendUrl.substring(0, backendUrl.length() - 1) : backendUrl;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullRequest.uri();
        fetchGet(fullRequest, ctx, url);
    }

    private void fetchGet(final FullHttpRequest inbound, final ChannelHandlerContext ctx, final String url) {
        final HttpGet httpGet = new HttpGet(url);

        //通过过滤器添加key为“nio”的header
        HttpRequestFilterImpl httpRequestFilter = new HttpRequestFilterImpl();
        httpRequestFilter.filter(inbound, ctx);

        // 复制header到新的http请求中
        System.out.println("复制前header个数：" + httpGet.getAllHeaders().length);
        for (Map.Entry<String, String> header : inbound.headers()) {
            httpGet.addHeader(header.getKey(), header.getValue());
        }
        System.out.println("复制后header个数：" + httpGet.getAllHeaders().length);
        for (Header header : httpGet.getAllHeaders()) {
            System.out.println(header.getName() + ":" + header.getValue());
        }

        //利用try-with-resource机制自动关闭连接
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse endpointResponse = httpClient.execute(httpGet)) {
            handleResponse(inbound, ctx, endpointResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final HttpResponse endpointResponse) throws Exception {
        FullHttpResponse response = null;
        try {
            byte[] body = EntityUtils.toByteArray(endpointResponse.getEntity());

            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(endpointResponse.getFirstHeader("Content-Length").getValue()));

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(response);
                }
            }
            ctx.flush();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
