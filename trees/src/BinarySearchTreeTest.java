import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BinarySearchTreeTest {
    protected BinarySearchTree<Integer> bst;

    @Before
    public void setUp() throws Exception {
        bst = new BinarySearchTree<>();
        bst.add(new Integer[]{30,15,60,12,20,40,90,13,25,35,70,14,28,31,65});
        assertThat(bst.size(), is(15));
    }

    @Test
    public void testPredecessor() {
        Integer predecessor;

        predecessor = bst.findPredecessor(12);
        assertThat(predecessor, is(nullValue()));
        predecessor = bst.findPredecessor(15);
        assertThat(predecessor, is(14));
        predecessor = bst.findPredecessor(31);
        assertThat(predecessor, is(30));
    }

    @Test
    public void testSuccessor() {
        Integer successor;

        successor = bst.findSuccessor(90);
        assertThat(successor, is(nullValue()));
        successor = bst.findSuccessor(60);
        assertThat(successor, is(65));
        successor = bst.findSuccessor(28);
        assertThat(successor, is(30));
    }

    @Test
    public void testInOrderTraversal() {
        Integer[] expected = new Integer[]{12,13,14,15,20,25,28,30,31,35,40,60,65,70,90};
        List<Integer> received = bst.inOrderTraversal();

        assertThat(expected.length, is(received.size()));
        for (int i=0; i<expected.length; i++) {
            assertThat(expected[i], is(received.get(i)));
        }
    }
}
