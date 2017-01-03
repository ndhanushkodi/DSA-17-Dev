import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

public class HashMapProblemsTest {

    /**
     * Test method for int countUnique(int[] arr)
     */
    @Test
    public void testCountUnique() {
        int[] arr = {0, 5, 6, 5, 9, 1, 6, 5, 3};

        assertThat(soln.HashMapProblems.countUnique(arr), is(6));
    }

    /**
     * Test method for int removeDuplicates(int[] arr)
     */
    @Test
    public void testRemoveDuplicates() {
        int[] arr = {0, 5, 6, 5, 9, 1, 6, 5, 3};
        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0, 1, 3, 5, 6, 9));

        assertThat(HashMapProblems.removeDuplicates(arr), is(result));
    }
}