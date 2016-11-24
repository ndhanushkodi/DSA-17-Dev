public class BinarySearchTree<T> implements BSTInterface<T> {
    Node root;

    @Override
    public void add(Node node) {

    }

    @Override
    public Node delete(T key) {
        return null;
    }

    @Override
    public Node findSuccessor(T key) {
        return null;
    }

    @Override
    public Node findPredecessor(T key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(T key) {
        return false;
    }

    public class Node<T> {
        public T data;
        public Node parent, leftChild, rightChid;

        public Node(T data) {
            this.data = data;
        }
    }
}
