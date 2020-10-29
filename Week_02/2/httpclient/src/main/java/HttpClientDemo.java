import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 使用 HttpClient 或 OkHttp 访问 http://localhost:8801
 *
 * @author zhoudy
 * @date 2020-10-28
 */
public class HttpClientDemo {

    public static void main(String[] args) {
        final String url = "http://localhost:8801";
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            System.out.println("响应状态为：" + response.getStatusLine());
            if (entity != null) {
                System.out.println("响应内容长度为：" + entity.getContentLength());
                System.out.println("响应内容为：");
                System.out.println(EntityUtils.toString(entity, StandardCharsets.UTF_8.name()));
                EntityUtils.consume(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
