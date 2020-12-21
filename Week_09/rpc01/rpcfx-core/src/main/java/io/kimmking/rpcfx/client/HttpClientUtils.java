package io.kimmking.rpcfx.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * HttpClient工具
 *
 * @author zhoudy
 * @date 2020/12/19
 */
public class HttpClientUtils {

    private static CloseableHttpClient httpClient = null;

    private static CloseableHttpClient getHttpClient() {
        if (Objects.isNull(httpClient)) {
            return HttpClientBuilder.create().build();
        } else {
            return httpClient;
        }
    }

    public static String post(String url, String jsonParam) {
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(jsonParam, StandardCharsets.UTF_8.name());
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        String responseStr = null;
        try (CloseableHttpResponse response = getHttpClient().execute(httpPost)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseStr = EntityUtils.toString(entity, StandardCharsets.UTF_8.name());
                EntityUtils.consume(entity);
            }
            return responseStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
