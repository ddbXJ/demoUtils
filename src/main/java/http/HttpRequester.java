package http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Http请求帮助类, 包含重试机制
 * 传入url,param以及header,download文件
 */
public class HttpRequester {

    private int retryCount = 3;
    private int retrySleep = 1000;
    private String url = null;
    private Map<String, Object> params = new HashMap<>();
    private Map<String, Object> headers = new HashMap<>();

    public HttpRequester setHeader(String key, Object value) {
        headers.put(key, value);
        return this;
    }

    public HttpRequester setParams(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public HttpRequester setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 下载数据
     */
    public byte[] downloadBytes() throws Exception{

        // 构造完整url
        String paramPart = String.join("&", params.entrySet()
                .stream()
                .map((x) -> x.getKey() + "=" + x.getValue().toString())
                .collect(Collectors.toList()));
        if (paramPart != null) {
            url += '?' + paramPart;
        }

        // 发送http请求
        HttpGet httpGet = new HttpGet(url);
        headers.entrySet()
                .forEach(x -> httpGet.setHeader(x.getKey(), x.getValue().toString()));

        return execute(httpGet);
    }

    /**
     * 下载文本内容
     */
    public String downloadString() throws Exception {
        byte[] bytes = downloadBytes();
        String content = new String(bytes);
        return content;
    }

    /**
     * 执行Http请求
     */
    private byte[] execute(HttpGet httpGet) throws Exception{

        IOException ex = null;
        int code = 0;

        for (int i = 0; i < this.retryCount; ++i) {
            try{
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        HttpEntity entity = response.getEntity();
                        code = response.getStatusLine().getStatusCode();
                        if (entity != null && code == 200) {
                            // 读取结果
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            entity.writeTo(stream);
                            return stream.toByteArray();
                        }

                        Thread.sleep(this.retrySleep);
                    }
                }
            }
            catch (IOException ex2) {
                ex = ex2;
                Thread.sleep(this.retrySleep);
            }
        }

        String msg = String.format("Failed pulling %s, http code: %d", httpGet.getURI(), code);
        ex = ex != null ? new IOException(msg, ex) : new IOException(msg);
        throw ex;
    }
}

