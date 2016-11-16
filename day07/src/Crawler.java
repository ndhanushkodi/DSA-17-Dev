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

    public void crawl(String url) throws IOException {
        q.add(new Object[]{url, 1});
        visited.add(url);
        crawlHelper(2, 3); // Crawl depth 2, first 5 links.
    }

    public void crawlHelper(int depth, int firstN) throws IOException {

        while (!q.isEmpty()) {

            Object[] urlDepth = q.remove();
            String url = (String) urlDepth[0];
            int currDepth = (Integer) urlDepth[1];

            Elements page = fetcher.fetchWikipedia(url);
            Indexer.getInstance().indexPage(page, url);

            if (currDepth >= depth)
                continue;

            List<String> neighbors = getHyperlinks(page);

            for (String u : neighbors.subList(0, Math.min(neighbors.size(), firstN))) {

                if (!visited.contains(u)) {
                    visited.add(u);
                    q.add(new Object[]{u, currDepth + 1});
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
//        Elements page = c.fetcher.fetchWikipedia(url);
        c.crawl(url);
//        c.getHyperlinks(page);
    }
}
