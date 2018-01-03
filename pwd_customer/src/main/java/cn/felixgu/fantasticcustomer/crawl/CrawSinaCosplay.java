package cn.felixgu.fantasticcustomer.crawl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CrawSinaCosplay extends BaseCrawl implements CrawlService {

    @Override
    public void process(URI uri) throws URISyntaxException, IOException {
        getHttpGet().setURI(uri);
        setCharset(Charset.forName("gb2312"));
        getHttpClient().execute(getHttpGet());
        String html = getString();
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("picBox");

        List<URI> uriList = new ArrayList<>();
        for (Element e :
                elements) {
            Elements node = e.select("div > table > tbody > tr > td > a[href]");
            if (node.isEmpty()) {
                continue;
            } else {
                String url = node.get(0).select("a[href]").attr("href");
                uriList.add(URI.create(url));
            }
        }

        for (URI detailUri :
                uriList) {
            System.out.println(detailUri.toString());
            getHttpGet().setURI(detailUri);
            setCharset(Charset.forName("gb2312"));

            getHttpClient().execute(getHttpGet());
            String detailHtml = getString();
            Document detailDocument = Jsoup.parse(detailHtml);
            Elements nodeEle = detailDocument.select("div#eData > dl");
            for (Element e :
                    nodeEle) {
                String picUrl = e.select("dl > dd").get(0).text();
                System.out.println(picUrl);
            }
            System.out.println("================");
        }
    }


    public static void
    main(String[] args) throws IOException, URISyntaxException {
        new CrawSinaCosplay().process(URI.create("http://slide.games.sina.com.cn/?cate=105"));
    }
}
