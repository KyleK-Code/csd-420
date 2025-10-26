    /* Author: Kyle Klausen
    * Date : 10/24/25
    * Assignment : Module 1.2
    * Description : The program randomly displays four playing cards from a deck with a refresh button to generate new random cards.
    */
    import javafx.application.Application;
    import javafx.geometry.Pos;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.HBox;
    import javafx.scene.layout.VBox;
    import javafx.stage.Stage;
    import java.io.FileInputStream;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;

    public class RandomCards extends Application {

        private static final int NUMBER_OF_CARDS = 52;
        private HBox cardBox = new HBox(10);

        @Override
        public void start(Stage primaryStage) {

            // Initial display of cards
            showRandomCards();

            Button refreshButton = new Button("Refresh");

            // Lambda Expression: Handles refreshing random cards when clicked
            refreshButton.setOnAction(e -> showRandomCards());

            VBox layout = new VBox(15, cardBox, refreshButton);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout, 500, 250);
            primaryStage.setTitle("Random Card Drawer");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private void showRandomCards() {
            List<Integer> deck = new ArrayList<>();
            for (int i = 1; i <= NUMBER_OF_CARDS; i++) {
                deck.add(i);
            }

            Collections.shuffle(deck); // Randomize card order

            cardBox.getChildren().clear(); // Clear previous cards

            // Display first four cards
            for (int i = 0; i < 4; i++) {
                try {
                    Image card = new Image("file:cards/" + deck.get(i) + ".png");
                    ImageView cardView = new ImageView(card);
                    cardView.setFitHeight(150);
                    cardView.setFitWidth(100);
                    cardBox.getChildren().add(cardView);
                } catch (Exception ex) {
                    System.out.println("Error loading card image: " + ex.getMessage());
                }
            }
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
