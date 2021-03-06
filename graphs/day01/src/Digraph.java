import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Digraph implements Graph {

    private List<Collection<Integer>> adj;
    private Collection<Integer> vertices;
    private int numEdges;

    public Digraph(int n) {
        this.numEdges = 0;
        adj = new ArrayList<>();
        vertices = new LinkedList<>();
        for (int v = 0; v < n; v++) {
            adj.add(new LinkedList<>());
            vertices.add(v);
        }
    }

    @Override
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        numEdges++;
    }

    @Override
    public Iterable<Integer> vertices() {
        return vertices;
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    @Override
    public Iterable<Integer> getNeighbors(int v) {
        return adj.get(v);
    }

    @Override
    public boolean hasEdgeBetween(int v, int w) {
        throw new UnsupportedOperationException();
    }

}
