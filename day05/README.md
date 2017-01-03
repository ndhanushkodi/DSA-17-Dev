# Day05 - `MyHashMap`

## Learning Goals

After completing this assignment you should be able to:
* Understand the key concepts of hashing
  * Understand hash functions and considerations when creating them
  * Understand how a hash map chooses which sub-map to place objects in
* Compare the efficiency of various hash map implementations
  * Understand how a hash map achieves average constant time
* Identify when using a hash map is effective

## Assignment

### Reading

- In *Think Algorithms*, read sections **15.4 and 15.5.**.

### Code

Write a couple of sentences describing the main purpose of a hash function. Are there any requirements or limitations when hashing? Find at least one problem with SillyString's hash function.

Finish the implementation of these methods in MyHashMap:
* `containsKey(Integer key)` return true if key is in map
* `containsValue(Integer value)` return true if value is in map
* `put(Integer key, Integer value)` add a new key-value pair to the map
* `remove(Integer key)` remove and return the key-value pair associated with the given key
* `rehash()` when the map has too many values, resize and copy all old elements into new map

Briefly describe the key differences and improvements between MyLinearMap and MyHashMap.

## Problems

* Complete the function 'countUnique(int[] arr)', which should take in an unsorted list of integers and return the number of unique integers in the list.  For example, given the list `[0, 5, 6, 5, 9, 1, 6, 5, 3]`, the function should return `6`.  Describe the runtime complexity of your solution in a comment as well as how your solution would differ if you did not have the HashMap data structure.

* Complete the function `removeDuplicates(int[] arr), which should take in an unsorted list of integers and return a List of integers (`List<Integer>`) with the unique integers in the list.  For example, given the list '[0, 5, 6, 5, 9, 1, 6, 5, 3]', the function should return '[0, 1, 3, 5, 6, 9]'.  Describe the runtime complexity of your solution in a comment as well as how your solution would differ if you did not have the HashMap data structure.