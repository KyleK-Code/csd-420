/* Author: Kyle Klausen
 * Date: 11/1/25
 * Assignment: Module 2
 * Class: CSD-420
 * Description: Reads the data from the file line by line and displays all stored integers and doubles in the console output. */

import java.io.*;
import java.util.Scanner;

public class ReadData {
    public static void main(String[] args) {
        String filename = "Kyle_datafile.dat";

        try (Scanner scanner = new Scanner(new File(filename))) {
            int lineNumber = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
}