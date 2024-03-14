package DiceGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Controller {

    @FXML
    private Button newGameButton;

    @FXML
    private Button rollDiceButton;

    @FXML
    private Button holdButton;

    @FXML
    private Label player1Label;

    @FXML
    private Label player2Label;

    @FXML
    private Label currentPlayerLabel1;

    @FXML
    private Label currentPlayerLabel2;

    @FXML
    private TextField player1TextField;

    @FXML
    private TextField player2TextField;

    @FXML
    private ImageView imageView;

    @FXML
    private void initialize() {
        // Initialize UI components and setup initial state
    }

    @FXML
    private void onClickNewGame(ActionEvent event) {
        // Handle the "NEW GAME" button click event
    }

    @FXML
    private void onClickRollDice(ActionEvent event) {
        // Handle the "ROLL DICE" button click event
    }

    @FXML
    private void onClickHold(ActionEvent event) {
        // Handle the "HOLD" button click event
    }
}
