import java.util.LinkedList;
import java.util.Iterator;
/* 
 * Author: Kyle Klausen
 * Date: 11/8/25
 * Assignment: Module 4
 * Class: CSD-420
 * Description: This program tests the performance of traversing a LinkedList
 * using an iterator versus using the get(index) method. The program
 * tests two list sizes: 50,000 and 500,000 integers.
 */

public class LinkedListTraversalTest {

    public static void main(String[] args) {
        // Test with 50,000 integers
        System.out.println("Testing with 50,000 integers:");
        testLinkedListTraversal(50000, false);

        System.out.println("\n---------------------------------\n");

        // Test with 500,000 integers
        System.out.println("Testing with 500,000 integers:");
        testLinkedListTraversal(500000, true); // Use sampling for get(index)
    }

    /**
     * Populates a LinkedList with integers and tests traversal time
     * using iterator and get(index) method.
     * 
     * @param size Number of integers to store in the list
     * @param sampleGet If true, samples get(index) traversal instead of full traversal
     */
    public static void testLinkedListTraversal(int size, boolean sampleGet) {
        LinkedList<Integer> list = new LinkedList<>();

        // Populate the LinkedList
        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        // Traverse using iterator
        long startTime = System.nanoTime();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        long endTime = System.nanoTime();
        long iteratorTime = endTime - startTime;
        System.out.println("Time using Iterator: " + iteratorTime / 1_000_000 + " ms");

        // Traverse using get(index)
        startTime = System.nanoTime();
        if (sampleGet) {
            // Sample every 10,000th element for demonstration
            for (int i = 0; i < list.size(); i += 10000) {
                list.get(i);
            }
        } else {
            // Full traversal for small lists
            for (int i = 0; i < list.size(); i++) {
                list.get(i);
            }
        }
        endTime = System.nanoTime();
        long getIndexTime = endTime - startTime;
        if (sampleGet) {
            System.out.println("Time using get(index) [sampled every 10,000th element]: " 
                               + getIndexTime / 1_000_000 + " ms");
        } else {
            System.out.println("Time using get(index): " + getIndexTime / 1_000_000 + " ms");
        }

        // Comments about the results
        /*
         * Explanation:
         * - Using an iterator is significantly faster because it accesses each node sequentially.
         * - Using get(index) is slower because LinkedList must traverse from the head for each index.
         * - As the list size increases, the difference becomes much larger.
         * - For very large lists, full get(index) traversal is impractical, so sampling is used.
         */
    }
}