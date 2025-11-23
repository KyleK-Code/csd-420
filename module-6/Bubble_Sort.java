import java.util.Arrays;
import java.util.Comparator;

    /* Author: Kyle Klausen
     * Date: 11/22/25
     * Class: CSD-420
     * Assignemt: Module 6.2
     * Description: Bubble sort is a simple sorting algorithm that repeatedly steps through the list,
     * compares adjacent elements, and swaps them if they are in the wrong order. 
     * 
     * This class also includes test cases to demonstrate both sorting methods.
     * 
     * * Demonstrates generic bubble sort methods in Java:
     * 1. Using the Comparable interface
     * 2. Using a Comparator interface
     */

    public class Bubble_Sort {

    /**
     * Generic bubble sort using Comparable.
     * Sorts the array in ascending order.
     * 
     * @param <E>  Type that extends Comparable
     * @param list Array of elements to sort
     */
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean swapped;
        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    // Swap elements
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                    System.out.println("Switch made");
                }
            }
            printArray(list); // Print array after each pass
            if (!swapped) break; // Exit early if no swaps
        }
    }

    /**
     * Generic bubble sort using Comparator.
     * Sorts the array based on the provided comparator.
     * 
     * @param <E>        Type of elements
     * @param list       Array of elements to sort
     * @param comparator Comparator defining the sort order
     */
    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean swapped;
        for (int i = 0; i < list.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (comparator.compare(list[j], list[j + 1]) > 0) {
                    E temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                    System.out.println("Switch made");
                }
            }
            printArray(list); // Print array after each pass
            if (!swapped) break;
        }
    }

    /**
     * Prints an array of any type.
     * 
     * @param <E>        Type of elements
     * @param arrayParam Array to print
     */
    public static <E> void printArray(E[] arrayParam) {
        System.out.print("array = {");
        for (E e : arrayParam) {
            System.out.print(" [" + e + "] ");
        }
        System.out.println("};");
    }

    /**
     * Main method to test both bubble sort methods.
     */
    public static void main(String[] args) {
        // Test Comparable bubble sort
        Integer[] numbers = {5, 3, 4, 9, 0, 1, 2, 7, 6, 8};
        System.out.println("Original numbers:");
        printArray(numbers);
        bubbleSort(numbers);
        System.out.println("Sorted numbers (Comparable):");
        printArray(numbers);

        // Test Comparator bubble sort (reverse order)
        String[] words = {"apple", "banana", "cherry", "date"};
        System.out.println("\nOriginal words:");
        printArray(words);
        bubbleSort(words, Comparator.reverseOrder());
        System.out.println("Sorted words (Comparator reverse):");
        printArray(words);

        // Test Comparator bubble sort (sort by string length)
        String[] fruits = {"kiwi", "banana", "apple", "fig"};
        System.out.println("\nOriginal fruits:");
        printArray(fruits);
        bubbleSort(fruits, (a, b) -> Integer.compare(a.length(), b.length()));
        System.out.println("Sorted fruits by length:");
        printArray(fruits);
    }
}