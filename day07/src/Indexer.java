import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utilities.StopWords;
import utilities.WikiFetcher;

import java.io.IOException;
import java.util.*;

public class Indexer {
    private static Map<String, Map<String, Integer>> indexingData = new HashMap<>();
    private static WikiFetcher wf = new WikiFetcher();
    private static Set<String> STOP_WORDS;

    public static void indexPage(Elements page, String url) throws IOException {
        if (STOP_WORDS == null) STOP_WORDS = StopWords.getStopWords();

        Map<String, Integer> index = new HashMap<>();

        for (Element paragraph : page) {
            countWords(paragraph, index);
        }

        indexingData.put(url, index);
    }

    public static void countWords(Element paragraph, Map<String, Integer> index) {
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

    public static void main(String[] args) throws IOException {
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements page = wf.fetchWikipedia(url);

        indexPage(page, url);

    }

    public void sendToRedis() {
        // Translate and send indexingData to Redis
    }
}
