/* Author: Kyle Klausen
 * Date: 11/1/25
 * Assignment: Module 2
 * Class: CSD-420
 * Description: Generates five random integers and five random doubles, then writes them to a file, appending if the file exists. */

import java.io.*;
import java.util.Random;

public class WriteData {
    public static void main(String[] args) {
        Random rand = new Random();

        int[] intArray = new int[5];
        double[] doubleArray = new double[5];

        // Fill arrays with random values
        for (int i = 0; i < 5; i++) {
            intArray[i] = rand.nextInt(100); // random int 0-99
            doubleArray[i] = rand.nextDouble() * 100; // random double 0.0-100.0
        }

        // File to write data
        String filename = "Kyle_datafile.dat";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // Write integers
            for (int i = 0; i < intArray.length; i++) {
                writer.write(intArray[i] + " ");
            }
            writer.newLine();

            // Write doubles
            for (int i = 0; i < doubleArray.length; i++) {
                writer.write(doubleArray[i] + " ");
            }
            writer.newLine();

            System.out.println("Data written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }
    }
}