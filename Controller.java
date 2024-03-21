package DiceGame;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.Random;

public class Controller {

    Random random = new Random();

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
    private ImageView diceImage;

    @FXML
    private void initialize() {
        // Initialize UI components and setup initial state
    }

    @FXML
    private void onClickNewGame(ActionEvent event) {
        // Handle the "NEW GAME" button click event
    }

    // @FXML
    // private void onClickRollDice(ActionEvent event) {
    //     // Handle the "ROLL DICE" button click event
    // }

    @FXML
    private void onClickHold(ActionEvent event) {
        // Handle the "HOLD" button click event


    }
    @FXML
    private void onClickMenu(ActionEvent event) {
        // Handle the "MENU" button click event
    }
    @FXML
    private void  setCurrentPlayerImage(ImageView view){
        this.diceImage=view;
    }

    @FXML
    private void onClickRollDice(ActionEvent event) {
        rollDiceButton.setDisable(true);

        Thread thread = new Thread() {
            public void run() {
                System.out.println("Thread Running");
                try {
                    for (int i = 0; i < 15; i++) {
                        int diceNumber = (random.nextInt(6) + 1);
                        File file = new File("DiceGame/Dices/dice-" + diceNumber + ".png");
                        diceImage.setImage(new Image(file.toURI().toString()));
                        Thread.sleep(50);
                        System.out.println(diceNumber);
                    }
                    rollDiceButton.setDisable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

}
