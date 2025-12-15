package module10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/**
 * FanDatabaseApp
 *
 * This application allows a user to view and update fan records
 * stored in the MySQL database "databasedb".
 *
 * Table: fans
 * Fields: id, firstname, lastname, favoriteteam
 *
 * Author: Kyle Klausen
 * Course: CSD-420
 * Module: 10
 * Date: 12/13/25
 */
public class FanDatabaseApp extends JFrame {

    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField favoriteTeamField;

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/databasedb?useSSL=false&serverTimezone=UTC";
    private static final String USER = "student1";
    private static final String PASS = "pass";

    /**
     * Constructs the GUI and initializes components.
     */
    public FanDatabaseApp() {
        setTitle("Fan Database Viewer");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        inputPanel.add(firstNameField);

        inputPanel.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        inputPanel.add(lastNameField);

        inputPanel.add(new JLabel("Favorite Team:"));
        favoriteTeamField = new JTextField();
        inputPanel.add(favoriteTeamField);

        JPanel buttonPanel = new JPanel();

        JButton displayButton = new JButton("Display");
        JButton updateButton = new JButton("Update");

        displayButton.addActionListener(this::displayFan);
        updateButton.addActionListener(this::updateFan);

        buttonPanel.add(displayButton);
        buttonPanel.add(updateButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Displays a fan record based on the ID entered.
     */
    private void displayFan(ActionEvent e) {
        String sql = "SELECT firstname, lastname, favoriteteam FROM fans WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(idField.getText()));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                firstNameField.setText(rs.getString("firstname"));
                lastNameField.setText(rs.getString("lastname"));
                favoriteTeamField.setText(rs.getString("favoriteteam"));
            } else {
                JOptionPane.showMessageDialog(this, "No record found.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error retrieving record.");
        }
    }

    /**
     * Updates a fan record using the values in the text fields.
     */
    private void updateFan(ActionEvent e) {
        String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, firstNameField.getText());
            stmt.setString(2, lastNameField.getText());
            stmt.setString(3, favoriteTeamField.getText());
            stmt.setInt(4, Integer.parseInt(idField.getText()));

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating record.");
        }
    }

    /**
     * Test code to ensure interface and database connectivity works.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FanDatabaseApp app = new FanDatabaseApp();
            app.setVisible(true);
        });
    }
}
