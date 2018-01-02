package cn.felixgu.fantasticcustomer.crawl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public interface CrawlService {
    void process(URI uri) throws URISyntaxException, IOException;
}
