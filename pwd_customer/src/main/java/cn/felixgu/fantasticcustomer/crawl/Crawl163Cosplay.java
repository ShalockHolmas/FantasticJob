package cn.felixgu.fantasticcustomer.crawl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Crawl163Cosplay extends BaseCrawl implements CrawlService{
    @Override
    public void process(URI uri) throws URISyntaxException, IOException {
        Document document = Jsoup.connect(uri.toString()).get();
        Elements elements = document.getElementsByTag("a");

        List<URI> uriList = new LinkedList<>();
        for (Element node :
                elements) {
            String detailUrl = node.attr("href");
            String json = Jsoup.connect(detailUrl).get().select("[name=gallery-data]").text();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            JsonNode listNode = rootNode.path("list");
            for (int i = 0; i < listNode.size(); i++) {
                if (listNode.get(i) == null){
                    continue;
                }

                String imgUrl = listNode.get(i).get("img").textValue();
                System.out.println(imgUrl);

                saveFile(URI.create(imgUrl));
            }

        }

    }



    public static void main(String[] args) throws IOException, URISyntaxException {
        for (int i = 2; i <= 15; i ++) {
            String url = "http://play.163.com/special/photo/tuku_cos_" + (i<10 ? 0 : "")  + i + ".html";
            new Crawl163Cosplay().process(URI.create(url));

        }
    }
}
