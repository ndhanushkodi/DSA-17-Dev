import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import utilities.JedisMaker;
import utilities.StopWords;
import utilities.WikiFetcher;

import java.io.IOException;
import java.util.*;

public class Indexer {

    private class UrlScore {
        public String url;
        public Integer score;

        public UrlScore(String url, Integer score) {
            this.url = url;
            this.score = score;
        }
    }

    private Map<String, Map<String, Integer>> indexingData = new HashMap<>();
    private Map<String, Set<UrlScore>> redisSub;
    private WikiFetcher wf = new WikiFetcher();
    private Set<String> STOP_WORDS;
    private static Indexer singletonInstance;
    private Jedis jedis;

    private Indexer() throws IOException {
        jedis = JedisMaker.make();
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

    public Map<String, Map<String, Integer>> translateData() {
        for (Map.Entry<String, Map<String,Integer>> e : indexingData.entrySet()) {

        }
        return null;
    }

    public void getPagesContainingTerm(String term) {
        // returns top 3 results

    }

    public static void main(String[] args) throws IOException {
        Indexer i = new Indexer();
        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
        Elements page = i.wf.fetchWikipedia(url);

        i.indexPage(page, url);

    }

    public void sendToRedis() throws IOException {
        jedis = JedisMaker.make();
        Transaction t = jedis.multi();
        Map<String, Map<String, Integer>> wordToUrlScore = translateData();
        for (String word : wordToUrlScore.keySet()) {
            Map<String, Integer> urlScore = wordToUrlScore.get(word);
            for (String url : urlScore.keySet()) {
                int count = urlScore.get(url);
                t.hset(word, url, Integer.toString(count));
            }
        }
        t.exec();
    }

    public List<String> getDataFromRedis(String word) {

    }
}
