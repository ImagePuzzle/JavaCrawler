
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entity.Page;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

public class WebCrawler {

    public WebCrawler() {

    }

    public void getPageLinks(String URL) {
        
            try {
            	Page pageEntity = new Page(new URL(URL).toString());
            	if (!pageEntity.contains()) {
            		pageEntity.add();
                }else
                {
                	
                }

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");

                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println(URL + ": " + e.getMessage());
            }
    }

}