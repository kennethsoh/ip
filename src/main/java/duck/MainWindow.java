package duck;

import java.io.IOException;

import duck.exception.UnknownCommandException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duck duck = new Duck();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image duckImage = new Image(this.getClass().getResourceAsStream("/images/duck.jpg"));

    /**
     * Creates an instance of MainWindow
     */
    public MainWindow() throws IOException {
    }

    /**
     * Initialise the Duck instance and display welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String text =
                "Hello! I'm DUCK\n"
                        + "What can I do for you?\n"
                        + "Enter command 'list' to see all saved tasks (if any)";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(text, duckImage));
    }

    /** Injects the Duke instance */
    public void setDuck(Duck d) {
        duck = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws UnknownCommandException, IOException {
        String input = userInput.getText();
        String response = duck.getResponse(input);

        if (response.equalsIgnoreCase("Quack. Hope to see you again soon!")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(response, duckImage));
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, duckImage)
        );
        userInput.clear();
    }
}
