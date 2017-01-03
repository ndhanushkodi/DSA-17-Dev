import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class HashMapProblemsTest {

    /**
     * Test method for int countUnique(int[] arr)
     */

    @Test
    public void testCountUnique() {
        int[] arr = {0, 5, 6, 5, 9, 1, 6, 5, 3};
        assertThat(HashMapProblems.countUnique(arr), is(6));
    }
}