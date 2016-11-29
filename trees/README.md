# Binary Search Trees Problem Set

1. Implement the following methods in `BinarySearchTree.java`:
 * `public T[] inOrderTraversal(Node n)` Performs an in-order traversal of the tree whose root is `n`
 * `public T findSuccessor(T key)` Finds the node immediately following `n` in the in-order traversal of the entire tree
 * `public T findPredecessor(T key)` Finds the node immediately preceding `n` in the in-order traversal of the entire tree
  Run corresponding tests in `BinarySearchTreeTest.java` to make sure your implementations are correct.

2. What is the worst case runtime of `inOrderTraversal`, `findSuccessor` and `findPredecessor`? (think carefully)

3. Draw the binary search tree resulted from inserting the keys [20,10,15,12,18,11,14,16,19,13,17]. Create this tree using the `BinarySearchTree` class.

4. Explain the step by step process of deleting each of the following keys from the tree in question 3, noting down all pointer changes in order:
  * `delete(15)` then
  * `delete(10)` then
 * `delete(19)`
