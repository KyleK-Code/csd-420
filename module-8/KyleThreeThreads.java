import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * KyleThreeThreads.java
 * Author: Kyle Klausen
 * Date: 11/29/25
 * Class: CSD-420
 * Assignment: Module-8
 * Description: This program creates three threads that output random letters, digits, 
 * and special characters to a JTextArea. Each thread outputs a minimum of 10,000 characters.
 * Optimized to append in batches for better performance.
 */
public class KyleThreeThreads {

    private static final int MIN_CHARS = 10000;
    private static final int BATCH_SIZE = 100; // Number of characters per batch append

    public static void main(String[] args) {
        JFrame frame = new JFrame("Three Threads Output");
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        frame.add(new JScrollPane(textArea));
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Thread for random letters
        Thread lettersThread = new Thread(() -> appendRandomChars(textArea, 'a', 26, MIN_CHARS));

        // Thread for random digits
        Thread digitsThread = new Thread(() -> appendRandomChars(textArea, '0', 10, MIN_CHARS));

        // Thread for random special characters
        Thread specialThread = new Thread(() -> {
            Random random = new Random();
            char[] specials = {'!', '@', '#', '$', '%', '&', '*'};
            int count = 0;
            StringBuilder batch = new StringBuilder();
            while (count < MIN_CHARS) {
                batch.append(specials[random.nextInt(specials.length)]);
                count++;
                if (batch.length() >= BATCH_SIZE) {
                    String toAppend = batch.toString();
                    SwingUtilities.invokeLater(() -> textArea.append(toAppend));
                    batch.setLength(0);
                }
            }
            // Append remaining characters
            if (batch.length() > 0) {
                String toAppend = batch.toString();
                SwingUtilities.invokeLater(() -> textArea.append(toAppend));
            }
        });

        lettersThread.start();
        digitsThread.start();
        specialThread.start();

        // Wait for threads to finish
        try {
            lettersThread.join();
            digitsThread.join();
            specialThread.join();
            System.out.println("All threads finished outputting characters.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void appendRandomChars(JTextArea textArea, char startChar, int range, int totalChars) {
        Random random = new Random();
        int count = 0;
        StringBuilder batch = new StringBuilder();
        while (count < totalChars) {
            batch.append((char) (startChar + random.nextInt(range)));
            count++;
            if (batch.length() >= BATCH_SIZE) {
                String toAppend = batch.toString();
                SwingUtilities.invokeLater(() -> textArea.append(toAppend));
                batch.setLength(0);
            }
        }
        if (batch.length() > 0) {
            String toAppend = batch.toString();
            SwingUtilities.invokeLater(() -> textArea.append(toAppend));
        }
    }
}
