import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utilities.WikiFetcher;

import java.io.IOException;
import java.util.*;

public class Crawler {

    WikiFetcher fetcher;
    Queue<Object[]> q;

    public Crawler() {
        this.fetcher = new WikiFetcher();
        this.q = new LinkedList<>();
    }

    public Elements crawl(String url, int depth) {

        return null;
    }

    public void crawlHelper() throws IOException {

        Set<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            Object[] urlDepth = q.remove();
            String url = (String) urlDepth[0];
            int depth = (Integer) urlDepth[1];

            if (visited.contains(url))
                continue;

            Elements page = fetcher.fetchWikipedia(url);
            for (String u : getHyperlinks(page).subList(0, 10)) {

            }

        }

    }

    public List<String> getHyperlinks(Elements page) {
        Elements aTags = page.select("a");
        List<String> links = new ArrayList<>(aTags.size());

        for (Element e : aTags) {
            links.add(e.attr("abs:href"));
        }

        return links;
    }

    public static void main(String[] args) throws IOException {
        Crawler c = new Crawler();
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements page = c.fetcher.fetchWikipedia(url);

        c.getHyperlinks(page);
    }
}
