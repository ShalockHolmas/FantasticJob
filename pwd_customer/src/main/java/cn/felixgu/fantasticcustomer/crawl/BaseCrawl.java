package cn.felixgu.fantasticcustomer.crawl;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

/**
 * @author Felix
 */
public class BaseCrawl {
    private HttpClient httpClient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private HttpEntity httpEntity;
    private HttpResponse response;
    private HttpClientContext httpClientContext;
    private Charset charset = Charset.forName("UTF-8");

    private String cookieFile;

    public String getCookieFile() {
        return cookieFile;
    }

    public void setCookieFile(String cookieFile) {
        this.cookieFile = cookieFile;
    }

    public HttpClientContext getHttpClientContext() {
        if (null == httpClientContext) {
            httpClientContext = HttpClientContext.create();
        }
        return httpClientContext;
    }

    public void setHttpClientContext(HttpClientContext httpClientContext) {
        this.httpClientContext = httpClientContext;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }


    public void initClient(){
        getHttpGet().setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");

    }

    public HttpClient getHttpClient() {
        if (null == httpClient) {
            httpClient = HttpClientBuilder.create().build();
            initClient();
        }
        return httpClient;
    }

    public HttpGet getHttpGet() {
        if (null == httpGet) {
            httpGet = new HttpGet();
        }
        return httpGet;
    }

    public HttpPost getHttpPost() {
        if (null == httpPost) {
            httpPost = new HttpPost();
        }
        return httpPost;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public String getString() throws IOException,NullPointerException {

        response = getHttpClient().execute(httpGet,getHttpClientContext());

        httpEntity = response.getEntity();

        InputStream in = httpEntity.getContent();
        int n = 0;
        byte[] context = new byte[1024];
        StringBuffer stringBuffer = new StringBuffer();
        while ((n = in.read(context)) != -1) {
            stringBuffer.append(new String(context,charset));
        }

        destory();

        return stringBuffer.toString();
    }

    private void saveCookies(HttpClientContext context) throws IOException {
        List<Cookie> cookies = context.getCookieStore().getCookies();
        setCookieFile(UUID.randomUUID().toString());
        OutputStream ou = new FileOutputStream(new File(getCookieFile()));
        for (Cookie c :
                cookies) {
            ou.write(c.getName().getBytes());
//            ou.write();
        }

    }


    public void destory(){
        httpEntity = null;
        httpClientContext = null;
        httpGet = null;
        httpPost = null;
        httpClient = null;
        response = null;
    }


    public void saveFile(URI uri) throws IOException {
        String path = uri.getRawPath();
        String type = (path.split("/")[path.split("/").length - 1]).split("\\.")[((path.split("/")[path.split("/").length - 1]).split("\\.").length) - 1];
        getHttpGet().setURI(uri);


        response = getHttpClient().execute(httpGet,getHttpClientContext());

        httpEntity = response.getEntity();

        InputStream in = httpEntity.getContent();
        OutputStream out = new FileOutputStream(new File(UUID.randomUUID().toString() + "." + type));

        int n = 0;
        byte[] context = new byte[1024];
        while ((n = in.read(context)) != -1) {
            out.write(context, 0 ,n);
        }
        out.flush();

        in.close();
        out.close();
        destory();
    }
}
