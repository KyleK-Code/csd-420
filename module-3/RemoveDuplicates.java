/*
 * Author: Kyle Klausen
 * Course: CSD-420
 * Assignment: Module 3
 * Date: 11/7/25
 * Description: This program fills an ArrayList with 50 random integers (1–20),
 * then removes duplicate values using a generic method called removeDuplicates().
 */

import java.util.ArrayList;
import java.util.Random;

public class RemoveDuplicates {

    public static void main(String[] args) {
        // Create an ArrayList of integers
        ArrayList<Integer> numbers = new ArrayList<>();
        Random rand = new Random();

        // Fill the ArrayList with 50 random values from 1 to 20
        for (int i = 0; i < 50; i++) {
            numbers.add(rand.nextInt(20) + 1);
        }

        // Display the original ArrayList
        System.out.println("Original ArrayList:");
        System.out.println(numbers);

        // Call the generic method to remove duplicates
        ArrayList<Integer> uniqueNumbers = removeDuplicates(numbers);

        // Display the new ArrayList with duplicates removed
        System.out.println("\nArrayList with duplicates removed:");
        System.out.println(uniqueNumbers);
    }

    /**
     * Generic method that removes duplicates from an ArrayList.
     * @param list The original ArrayList containing elements of any type.
     * @return A new ArrayList with duplicates removed.
     */
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        ArrayList<E> result = new ArrayList<>();

        for (E element : list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }

        return result;
    }
}