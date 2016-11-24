public interface BSTInterface<T> {
    void add(BinarySearchTree.Node node);

    BinarySearchTree.Node delete(T key);

    BinarySearchTree.Node findSuccessor(T key);

    BinarySearchTree.Node findPredecessor(T key);

    int size();

    boolean contains(T key);
}
