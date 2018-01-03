package cn.felixgu.fantasticcustomer.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class CrawlTest extends BaseCrawl implements CrawlService {

    @Override
    public void process(URI uri) throws URISyntaxException, IOException {
        getHttpGet().setURI((uri));
        setCharset(Charset.forName("gb2312"));
//        setCharset(Charset.forName("gb2312"));
//        File file = new File("./index.html");
//        FileOutputStream out = new FileOutputStream(file);
        String result = getString();
//        out.write((result.getBytes()));
//        out.close();
        Document document = Jsoup.parse(result);
        System.out.println(document.title());

    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        new CrawlTest().process(new URI("http://slide.games.sina.com.cn/?cate=105"));
    }
}
