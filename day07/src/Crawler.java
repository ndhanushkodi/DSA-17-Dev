import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utilities.WikiFetcher;

import java.io.IOException;
import java.util.*;

public class Crawler {

    WikiFetcher fetcher;
    Queue<Object[]> q;
    Set<String> visited;

    public Crawler() {
        this.fetcher = new WikiFetcher();
        this.q = new LinkedList<>();
        this.visited = new HashSet<>();
    }

    public Elements crawl(String url) throws IOException {
        q.add(new Object[]{url, 1});
        visited.add(url);
        crawlHelper();
    }

    public void crawlHelper() throws IOException {

        while (!q.isEmpty()) {

            Object[] urlDepth = q.remove();
            String url = (String) urlDepth[0];
            int depth = (Integer) urlDepth[1];

            Elements page = fetcher.fetchWikipedia(url);
            Indexer.indexPage(page, url);

            if (depth >= 1)
                continue;

            List<String> neighbors = getHyperlinks(page);

            for (String u : neighbors.subList(0, Math.min(neighbors.size(), 10))) {

                if (!visited.contains(u)) {
                    visited.add(u);
                    q.add(new Object[]{u, depth + 1});
                }
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
