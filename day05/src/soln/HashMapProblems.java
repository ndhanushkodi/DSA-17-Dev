package soln;

/**
 * DSA Day05 HW Challenge Problem
 */

public class HashMapProblems {

    public static int countUnique(int[] arr) {
        // Setup hashmap to keep track of elements
        MyHashMapSoln<Integer, Integer> countElements = new MyHashMapSoln<>();

        for (int i : arr) {
            countElements.put(i, i);
        }

        return countElements.size();
    }

}