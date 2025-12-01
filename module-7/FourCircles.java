/*
 * File: FourCircles.java
 * Author: Kyle Klausen
 * Date: 11/28/2025
 * Class: CSD-420
 * Assignment: Module-7
 * Description:
 * This program displays four circles using JavaFX. An external CSS file
 * (mystyle.css) is used to define a style class for a white fill with
 * black stroke and IDs for red and green circles. The circles demonstrate
 * how to apply both style classes and IDs in JavaFX.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FourCircles extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Root pane
        Pane pane = new Pane();
        pane.getStyleClass().add("border"); // optional: use the .border class

        // Circle 1: plain white with black stroke (uses .plaincircle)
        Circle c1 = new Circle(80, 80, 40);
        c1.getStyleClass().add("plaincircle");

        // Circle 2: plain white with black stroke + dashed border style
        Circle c2 = new Circle(220, 80, 40);
        c2.getStyleClass().addAll("plaincircle", "circleborder");

        // Circle 3: red circle using #redcircle ID
        Circle c3 = new Circle(80, 200, 40);
        c3.setId("redcircle");

        // Circle 4: green circle using #greencircle ID
        Circle c4 = new Circle(220, 200, 40);
        c4.setId("greencircle");

        pane.getChildren().addAll(c1, c2, c3, c4);

        Scene scene = new Scene(pane, 300, 280);

        // Load external CSS
        scene.getStylesheets().add("mystyle.css");

        primaryStage.setTitle("Module 7 – Four Circles with CSS");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Run simple test code (console-based) to verify setup
        runTests(c1, c2, c3, c4);
    }

    /**
     * Simple test code to verify that style classes and IDs
     * have been applied correctly.
     *
     * @param c1 circle 1
     * @param c2 circle 2
     * @param c3 circle 3
     * @param c4 circle 4
     */
    private void runTests(Circle c1, Circle c2, Circle c3, Circle c4) {
        System.out.println("=== Test Output ===");

        // Test that c1 has style class "plaincircle"
        System.out.println("Circle 1 style classes: " + c1.getStyleClass());

        // Test that c2 has both "plaincircle" and "circleborder"
        System.out.println("Circle 2 style classes: " + c2.getStyleClass());

        // Test that c3 has ID "redcircle"
        System.out.println("Circle 3 ID: " + c3.getId());

        // Test that c4 has ID "greencircle"
        System.out.println("Circle 4 ID: " + c4.getId());

        // Basic assertions (manual/visual)
        boolean c1HasPlain = c1.getStyleClass().contains("plaincircle");
        boolean c2HasPlain = c2.getStyleClass().contains("plaincircle");
        boolean c2HasBorder = c2.getStyleClass().contains("circleborder");
        boolean c3IsRed = "redcircle".equals(c3.getId());
        boolean c4IsGreen = "greencircle".equals(c4.getId());

        System.out.println("c1 has .plaincircle: " + c1HasPlain);
        System.out.println("c2 has .plaincircle: " + c2HasPlain);
        System.out.println("c2 has .circleborder: " + c2HasBorder);
        System.out.println("c3 has ID #redcircle: " + c3IsRed);
        System.out.println("c4 has ID #greencircle: " + c4IsGreen);
        System.out.println("===================");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
