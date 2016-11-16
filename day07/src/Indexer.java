import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

public class Indexer {
    private Map<String, Map<String, Integer>> indexingData;

    public Map<String, Integer> indexPage(Elements page) {
        return null;
    }

    public void sendToRedis() {
        // Translate and send indexingData to Redis
    }
}
