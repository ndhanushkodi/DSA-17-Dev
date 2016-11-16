import java.io.IOException;
import java.util.List;

public class SearchTool {
    Indexer indexer;
    Crawler crawler;

    public static enum BOOLEAN_SEARCH {
        AND, OR, MINUS
    };

    public SearchTool() throws IOException {
        indexer = Indexer.getInstance();
        crawler = new Crawler();
    }

    public List<String> search(String w1, String w2, BOOLEAN_SEARCH operator) {
        return null;
    }
}
