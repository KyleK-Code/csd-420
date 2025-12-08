package module9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author kyleklausen
 */
public class Module9 {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/databasedb";
        String user = "student1";
        String password = "pass";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NOW();"); // test query
            while (rs.next()) {
                System.out.println("Query output: " + rs.getString(1));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


