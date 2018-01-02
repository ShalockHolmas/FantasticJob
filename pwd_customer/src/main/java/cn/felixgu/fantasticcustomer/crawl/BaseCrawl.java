package cn.felixgu.fantasticcustomer.crawl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author Felix
 */
public abstract class BaseCrawl {
    private HttpClient httpClient;
    private HttpGet httpGet;
    private HttpPost httpPost;
    private HttpEntity httpEntity;
    private HttpResponse response;

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    private Charset charset = Charset.forName("UTF-8");

    public void initClient(){
        //getHttpClient().getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "gb2312");
    }

    public HttpClient getHttpClient() {
        if (null == httpClient) {
            httpClient = new DefaultHttpClient();
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


        response = getHttpClient().execute(httpGet);
        httpEntity = response.getEntity();

        InputStream in = httpEntity.getContent();
        int n = 0;
        byte[] context = new byte[1024];
        StringBuffer stringBuffer = new StringBuffer();
        while ((n = in.read(context)) != -1) {
            stringBuffer.append(new String(context,charset));
        }

        return stringBuffer.toString();
    }
}
