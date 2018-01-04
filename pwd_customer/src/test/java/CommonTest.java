import cn.felixgu.fantasticcustomer.crawl.Crawl163Cosplay;

import java.net.URI;
import java.util.ArrayList;

public class CommonTest {
    public static void main(String[] args) {
        String path = URI.create("http://img2.cache.netease.com/photo/0031/2017-01-04/C9T8K7EC6LRK0031.jpg").getRawPath();

        String type = (path.split("/")[path.split("/").length - 1]).split("\\.")[((path.split("/")[path.split("/").length - 1]).split("\\.").length) - 1];
        System.out.println(type);
        for (String s :
                path.split("/")) {
            System.out.println(s);
        }
    }
}
