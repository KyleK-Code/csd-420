/*
 * Author: Kyle Klausen
 * Course: CSD-420
 * Module: 5.2
 * Assignment: WordSorter.Java
 * Description:Read words from a text file, show non-duplicates in ascending and descending order, with test code.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class WordSorter {

    public static void main(String[] args) {
        // Change this path if needed, but normally the file should be
        // in the same directory as this .java/.class when you run it.
        String fileName = "collection_of_words.txt";

        // Read the words from the file into a sorted set (TreeSet removes duplicates).
        Set<String> uniqueWords = readWordsFromFile(fileName);

        if (uniqueWords.isEmpty()) {
            System.out.println("No words were found in " + fileName);
        } else {
            // Display words in ascending order (TreeSet's natural order).
            System.out.println("Unique words in ascending order:");
            for (String word : uniqueWords) {
                System.out.println(word);
            }

            System.out.println();

            // Display words in descending order.
            System.out.println("Unique words in descending order:");
            List<String> descendingList = new ArrayList<>(uniqueWords);
            Collections.sort(descendingList, Collections.reverseOrder());
            for (String word : descendingList) {
                System.out.println(word);
            }
        }

        // Run simple test code to confirm behavior.
        runTests();
    }

    /**
     * Reads words from the given file into a TreeSet to ensure they are:
     *  - unique (no duplicates),
     *  - automatically sorted in ascending order.
     *
     * @param fileName name of the text file to read
     * @return a set of unique, sorted words
     */
    private static Set<String> readWordsFromFile(String fileName) {
        Set<String> words = new TreeSet<>();

        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                // Optional: normalize to lower case so "Word" and "word" are treated the same
                word = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

                if (!word.isEmpty()) {
                    words.add(word);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find file: " + fileName);
        }

        return words;
    }

    /**
     * Basic test code to ensure core logic works.
     * This is not a full unit test framework, but it gives some confidence
     * that non-duplicates and sorting behavior are correct.
     */
    private static void runTests() {
        System.out.println("\n--- Running basic tests ---");

        // Test 1: duplicates should be removed, and words sorted ascending
        Set<String> testSet = new TreeSet<>();
        testSet.add("banana");
        testSet.add("apple");
        testSet.add("orange");
        testSet.add("banana");  // duplicate
        testSet.add("Apple");   // different case

        // To simulate our normalization, convert all to lower case
        Set<String> normalizedTestSet = new TreeSet<>();
        for (String w : testSet) {
            normalizedTestSet.add(w.toLowerCase());
        }

        System.out.println("Ascending (expected: apple, banana, orange):");
        for (String w : normalizedTestSet) {
            System.out.println(w);
        }

        System.out.println("\nDescending (expected: orange, banana, apple):");
        List<String> descendingList = new ArrayList<>(normalizedTestSet);
        Collections.sort(descendingList, Collections.reverseOrder());
        for (String w : descendingList) {
            System.out.println(w);
        }

        System.out.println("--- Tests complete ---");
    }
}
