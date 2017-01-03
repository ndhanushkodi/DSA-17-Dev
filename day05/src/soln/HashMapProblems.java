package soln;

/**
 * DSA Day05 HW Challenge Problems
 */

import java.util.List;
import java.util.ArrayList;

public class HashMapProblems {

    public static int countUnique(int[] arr) {
        // Return the number of unique elements in the input array
        MyHashMap<Integer, Integer> countElements = new MyHashMap<>();

        for (int i : arr) {
            countElements.put(i, i);
        }

        return countElements.size();
    }

    public static List<Integer> removeDuplicates(int[] arr) {
        // Return a list of the unique elements in the input array
        MyHashMap<Integer, Integer> uniqueElements = new MyHashMap<>();

        for (int i : arr) {
            uniqueElements.put(i, i);
        }

        List elements = new ArrayList(uniqueElements.values());

        return elements;
    }

}