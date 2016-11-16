import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utilities.StopWords;
import utilities.WikiFetcher;

import java.io.IOException;
import java.util.*;

public class Indexer {

    private Map<String, Map<String, Integer>> indexingData = new HashMap<>();
    private WikiFetcher wf = new WikiFetcher();
    private Set<String> STOP_WORDS;
    private static Indexer singletonInstance;

    private Indexer() throws IOException {
        if (STOP_WORDS == null) STOP_WORDS = StopWords.getStopWords();
    }

    public static Indexer getInstance() throws IOException {
        if (singletonInstance == null) singletonInstance = new Indexer();
        return singletonInstance;
    }

    public void indexPage(Elements page, String url) throws IOException {
        Map<String, Integer> index = new HashMap<>();

        for (Element paragraph : page) {
            countWords(paragraph, index);
        }

        indexingData.put(url, index);
    }

    public void countWords(Element paragraph, Map<String, Integer> index) {
        List<String> words = new ArrayList<>(Arrays.asList(paragraph.ownText().split(" ")));

        Integer count;
        for (String word : words) {
            String w = word.toLowerCase().replaceAll("[^a-zA-Z]", "");
            if (w.length() > 1 && !STOP_WORDS.contains(w)) {
                count = index.get(w);
                if (count != null) {
                    index.put(w, count + 1);
                } else {
                    index.put(w, 1);
                }
            }
        }
    }

    public Map<String, Map<String,Integer>> translateData() {
        Map<String, Map<String, Integer>> redisSub = new HashMap<>();

        for (Map.Entry<String, Map<String,Integer>> e : indexingData.entrySet()) {
            String url = e.getKey();
            Map<String, Integer> index = e.getValue();

            for (Map.Entry<String, Integer> entry : index.entrySet()) {
                String term = entry.getKey();
                Integer score = entry.getValue();

                Map<String, Integer> pages = redisSub.get(term);
                if (pages == null) {
                    pages = new HashMap<>();
                }

                pages.put(url, score);

                redisSub.put(term, pages);
            }
        }

        indexingData.clear();
        return redisSub;
    }

    public List<String> getPagesContainingTerm(String term) {
        // getDataFromRedis();
        Map<String, Map<String, Integer>> index = translateData();
        List<String> relevantPages;

        Map<String, Integer> urls = index.get(term);

        if (urls == null && urls.size() == 0) {
            return null;
        }

        return null;

    }

    public static void main(String[] args) throws IOException {
        Indexer i = new Indexer();
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements page = i.wf.fetchWikipedia(url);

        i.indexPage(page, url);

        i.translateData();
    }

    public void sendToRedis() {
        // Translate and send indexingData to Redis
    }
}
