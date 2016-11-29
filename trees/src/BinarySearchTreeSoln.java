import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeSoln<T extends Comparable<T>> {
    private Node root;
    private int size;

    /**
     * An internal class for the BinarySearchTree. Node holds the key T, and pointers to the parent, left and right children nodes
     * @param <T>
     */
    private class Node<T> {
        private T key;
        private Node parent, leftChild, rightChild;

        private Node(T key) {
            this.key = key;
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        private boolean isLeftChild() {
            if (parent != null) {
                return parent.leftChild != null && parent.leftChild.equals(this);
            }
            return false;
        }

        private boolean isRightChild() {
            if (parent != null) {
                return parent.rightChild != null && parent.rightChild.equals(this);
            }
            return false;
        }

        private boolean isLeaf() {
            return (this.leftChild == null && this.rightChild == null);
        }

        /*
        equals is overloaded here to take in a different Node and compare the key attribute
         */
        private boolean equals(Node other) {
            return other != null && other.key.equals(this.key);
        }

        @Override
        public String toString() {
            String parent = this.parent == null ? "" : ", parent=" + this.parent.key.toString();
            String left = this.leftChild == null ? "" :  ", left=" + this.leftChild.key.toString();
            String right = this.rightChild == null ? "" : ", right=" + this.rightChild.key.toString();
            return "Node{key=" + key + parent + left + right + '}';
        }
    }

    /**
     * Returns size of the tree
     */
    public int size() {
        return size;
    }

    /**
     * Check whether a node holding the given key exists in the BST
     */
    public boolean contains(T key) {
        return find(key, root) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public void add(T key) {
        Node node = new Node(key);
        if (root == null) {
            root = node;
            size = 1;
        } else {
            insert(node, root);
            size++;
        }
    }

    /**
     * Add a list of nodes to the BST.
     */
    public void add(T[] keys) {
        for (T k : keys) {
            add(k);
        }
    }

    /**
     * Perform an inorder traversal of the BST, returns list of nodes in traversed order
     */
    public List<T> inOrderTraversal() {
        List<T> list = new ArrayList<T>();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(Node node, List<T> list) {
        if (node != null) {
            inOrderTraversal(node.leftChild, list);
            list.add((T) node.key);
            inOrderTraversal(node.rightChild, list);
        }
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        Node toDelete = find(key, root);
        Node replacement;

        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }

        if (toDelete.leftChild != null) {
            // Case 1: find the predecessor
            replacement = findPredecessor(toDelete);
            if (replacement.isRightChild() && replacement.leftChild != null) {
                replacement.parent.rightChild = replacement.leftChild;
                replacement.leftChild.parent = replacement.parent;
            }
        } else if (toDelete.rightChild != null) {
            // Case 2: find the successor
            replacement = findSuccessor(toDelete);
            if (replacement.isLeftChild() && replacement.rightChild != null) {
                replacement.parent.leftChild = replacement.rightChild;
                replacement.rightChild.parent = replacement.parent;
            }
        } else {
            // Case 3: replace it with null
            replacement = null;
        }

        /* The code from this point on makes sure that no node in the tree
        will still be pointing to the node being deleted
         */
        if (toDelete.parent != null) {
            if (toDelete.isLeftChild()) {
                toDelete.parent.leftChild = replacement;
            } else {
                toDelete.parent.rightChild = replacement;
            }
        }

        if (replacement != null) {
            replacement.parent = toDelete.parent;
        }

        if (toDelete.rightChild != null && !toDelete.rightChild.equals(replacement)) {
            replacement.rightChild = toDelete.rightChild;
            toDelete.rightChild.parent = replacement;
        }

        if (toDelete.leftChild != null && !toDelete.leftChild.equals(replacement)) {
            replacement.leftChild = toDelete.leftChild;
            toDelete.leftChild.parent = replacement;
        }

        size--;
        return true;
    }

    /**
     * Public facing method to find the predecessor of a node with the given key
     */
    public T findPredecessor(T key) {
        Node n = find(key, root);

        if (n != null) {
            Node predecessor = findPredecessor(n);

            if (predecessor != null) {
                @SuppressWarnings("unchecked")
                T predKey = (T) predecessor.key;
                return predKey;
            }
        }

        return null;
    }

    /**
     * Public facing method to find the successor of a node with the given key
     */
    public T findSuccessor(T key) {
        Node n = find(key, root);

        if (n != null) {
            Node successor = findSuccessor(n);

            if (successor != null) {
                @SuppressWarnings("unchecked")
                T succKey = (T) successor.key;
                return succKey;
            }
        }

        return null;
    }

    private Node findPredecessor(Node n) {
        Node predecessor;
        if (n.leftChild != null) {
            predecessor = n.leftChild;
            while (predecessor.rightChild != null) {
                predecessor = predecessor.rightChild;
            }
        } else {
            predecessor = n.parent;
            while (predecessor != null && n.equals(predecessor.leftChild)) {
                n = predecessor;
                predecessor = predecessor.parent;
            }
        }

        return predecessor;
    }

    private Node findSuccessor(Node n) {
        Node successor;
        if (n.rightChild != null) {
            successor = n.rightChild;
            while (successor.leftChild != null) {
                successor = successor.leftChild;
            }
        } else {
            successor = n.parent;
            while (successor != null && n.equals(successor.rightChild)) {
                n = successor;
                successor = successor.parent;
            }
        }

        return successor;
    }

    /**
     * Returns true if a node with the given key exists in the BST
     */
    private Node find(T key, Node currentNode) {
        if (currentNode == null) {
            return null;
        } else {
            @SuppressWarnings("unchecked")
            int cmp = key.compareTo((T) currentNode.key);

            if (cmp < 0) {
                return find(key, currentNode.leftChild);
            } else if (cmp > 0) {
                return find(key, currentNode.rightChild);
            } else {
                return currentNode;
            }
        }
    }

    /**
     * Recursively insert a new node into the BST
     */
    private void insert(Node node, Node currentNode) {
        @SuppressWarnings("unchecked")
        T newKey = (T) node.key;
        @SuppressWarnings("unchecked")
        T currentKey = (T) currentNode.key;

        int cmp = newKey.compareTo(currentKey);
        if (cmp < 0) {
            if (currentNode.leftChild != null) {
                insert(node, currentNode.leftChild);
            } else {
                currentNode.leftChild = node;
                node.parent = currentNode;
            }
        } else if (cmp > 0){
            if (currentNode.rightChild != null) {
                insert(node, currentNode.rightChild);
            } else {
                currentNode.rightChild = node;
                node.parent = currentNode;
            }
        }
    }
}
