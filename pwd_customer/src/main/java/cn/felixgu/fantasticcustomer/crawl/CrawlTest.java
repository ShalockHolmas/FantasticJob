package cn.felixgu.fantasticcustomer.crawl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class CrawlTest extends BaseCrawl implements CrawlService {

    @Override
    public void process(URI uri) throws URISyntaxException, IOException {
        getHttpGet().setURI((uri));
        setCharset(Charset.forName("gb2312"));
        File file = new File("./index.html");
        FileOutputStream out = new FileOutputStream(file);
        out.write((getString().getBytes()));
        out.close();
        System.out.println(getString());
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        new CrawlTest().process(new URI("http://www.27270.com/ent/rentiyishu/"));
    }
}
