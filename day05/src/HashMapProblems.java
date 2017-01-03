/**
 * DSA Day05 HW Challenge Problem
 */

public class HashMapProblems {

    public static int countUnique(int[] arr) {
        // Setup hashmap to keep track of elements
        MyHashMap<Integer, Integer> countElements = new MyHashMap<>();

        for (int i : arr) {
            countElements.put(i, i);
        }

        return countElements.size();
    }

}
