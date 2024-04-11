import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.nio.file.*;

import java.util.Random;
import Backend.*;

public class Controller {
    private boolean isNewGame;

    Random random = new Random();
    menu menu = new menu();

    @FXML
    private Button newGameButton;

    @FXML
    private Button rollDiceButton;

    @FXML
    private Button holdButton;

    @FXML
    private Label name0;

    @FXML
    private Label name1;

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
    private TextField score0;

    @FXML
    private TextField score1;

    @FXML
    private TextField current0;

    @FXML
    private TextField current1;
    
    @FXML
    private Pane player0;

    @FXML
    private Pane player1;

    @FXML
    private Circle active0;

    @FXML
    private Circle active1;


    @FXML
    private void initialize() {
        // Initialize UI components and setup initial state
        isNewGame = true;

        menu.game.resetScore();


        score0.setText("0");
        score1.setText("0");

        current0.setText("0");
        current1.setText("0");

        player0.setStyle("-fx-background-color: #e9e9e9;");
        player1.setStyle("-fx-background-color: #f4f4f4;");

        active0.setVisible(true);
        active1.setVisible(false);

        name0.setFont(Font.font("System", FontWeight.BOLD, 16));
        name1.setFont(Font.font("System", FontWeight.BLACK, 12));

        name0.setText("PLAYER 1");
        name1.setText("PLAYER 2");

        name0.setStyle("-fx-text-fill: #000000;");
        name1.setStyle("-fx-text-fill: #000000;");

        rollDiceButton.setVisible(true);
        holdButton.setVisible(true);

        diceImage.setVisible(false);
    }

    @FXML
    private void onClickNewGame(ActionEvent event) {
        // Handle the "NEW GAME" button click event
        initialize();
    }

    // @FXML
    // private void onClickRollDice(ActionEvent event) {
    //     // Handle the "ROLL DICE" button click event
    // }

    @FXML
    private void diceUpdate()
    {
        int[] temp = new int[2];
        temp = menu.game.getActivescore();
        current0.setText(Integer.toString(temp[0]));
        current1.setText(Integer.toString(temp[1]));
    }
    

    @FXML
    private void onClickHold(ActionEvent event) {
        // Handle the "HOLD" button click event
        if(isNewGame == true) {
            menu.game.updateScore();
            this.diceUpdate();
            int[] scores = new int[2];
            scores = menu.game.getScore();
            score0.setText(Integer.toString(scores[0]));
            score1.setText(Integer.toString(scores[1]));

            if(menu.game.isGameOver()) 
            {
                isNewGame = false;

                diceImage.setVisible(false);

                rollDiceButton.setVisible(false);
                holdButton.setVisible(false);

                if(menu.game.getActiveplayer() == 0) {
                    name0.setText("Winner");
                    name0.setStyle("-fx-text-fill: #EB4D4D;");
                    name0.setFont(Font.font("System", FontWeight.BOLD, 20));
                    name1.setText("Loser");
                    active0.setVisible(false);
                } else {
                    name1.setText("Winner");
                    name1.setStyle("-fx-text-fill: #EB4D4D;");
                    name1.setFont(Font.font("System", FontWeight.BOLD, 20));
                    name0.setText("Loser");
                    active1.setVisible(false);
                }
            } 
            else 
            {
                switchToNextPlayer();
                menu.game.switchActive();
            }
        }
    }
    @FXML
    private void onClickMenu(ActionEvent event) {
        // Handle the "MENU" button click event
        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Тоглоомын танилцуулга");

        alert.setContentText(menu.getInstruction());

        ButtonType closeButton = new ButtonType("OKAYYY", ButtonData.OK_DONE);
        alert.getButtonTypes().add(closeButton);

        Button closeBtn = (Button) alert.getDialogPane().lookupButton(closeButton);
        closeBtn.setOnAction(e -> alert.close());


        alert.getDialogPane().setPrefWidth(350); // Set your preferred width
        alert.getDialogPane().setPrefHeight(250);
        alert.showAndWait();
    }
    @FXML
    private void  setCurrentPlayerImage(ImageView view){
        this.diceImage=view;
    }

    @FXML
    private void onClickRollDice(ActionEvent event) {
        rollDiceButton.setDisable(true);
        diceImage.setVisible(true);
        Thread thread = new Thread() {
            public void run() {
                Path currentPath = Paths.get(System.getProperty("user.dir"));
                if(isNewGame == true) {
                    try {
                        int diceNumber;
                        for (int i = 0; i < 15; i++) {
                            diceNumber = random.nextInt(6) + 1;
                            File file = new File("DiceGame/Dices/dice-" + diceNumber + ".png");
                            diceImage.setImage(new Image(file.toURI().toString()));
                            Thread.sleep(50);
                            if(i == 14) {
                                diceNumber = rand.diceRoll();
                                file = new File("DiceGame/Dices/dice-" + diceNumber + ".png");
                            if(i == 14) 
                            {
                                diceNumber = menu.game.rolldice();
                                if(diceNumber == 0)
                                    file = new File(currentPath + "/Dices/dice-1.png");
                                else
                                    file = new File(currentPath + "/Dices/dice-" + diceNumber + ".png");
                                diceImage.setImage(new Image(file.toURI().toString()));
                                diceUpdate();
                                if(diceNumber == 0) 
                                {
                                    menu.game.switchActive();
                                    switchToNextPlayer();
                                    menu.game.switchActive();
                                }
                            }
                        }
                        rollDiceButton.setDisable(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("togloom duussan");
                }
            }
        };
        thread.start();
    }

    public void switchToNextPlayer() {
        if(menu.game.getActiveplayer() == 0) {
            active0.setVisible(false);
            active1.setVisible(true);

            player0.setStyle("-fx-background-color: #f4f4f4;");
            player1.setStyle("-fx-background-color: #e9e9e9;");

            name0.setFont(Font.font("System", FontWeight.BLACK, 12));
            name1.setFont(Font.font("System", FontWeight.BOLD, 16));
        }
        else 
        {
            active0.setVisible(true);
            active1.setVisible(false);

            player0.setStyle("-fx-background-color: #e9e9e9;");
            player1.setStyle("-fx-background-color: #f4f4f4;");

            name0.setFont(Font.font("System", FontWeight.BOLD, 16));
            name1.setFont(Font.font("System", FontWeight.BLACK, 12));
        }
    }

}
