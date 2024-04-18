import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import Frontend.*;

public class Controller {

    // ------------------------------------Controller variable declaration----------------------------------
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


    // ----------------------------------------------initial game-------------------------------------------------
    @FXML
    private void initialize() {
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

    //--------------------------------------------------OnClick new game-------------------------------------------
    @FXML
    private void onClickNewGame(ActionEvent event) {
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
        Menu mu = new Menu();
        System.out.println(menu.getInstruction());
        mu.showMenu();
    }

    @FXML
    private void  setCurrentPlayerImage(ImageView view){
        this.diceImage=view;
    }

    private void checkActive(int i , int diceNumber , File file) {
        if(i == 14) 
        {
            Path currentPath = Paths.get(System.getProperty("user.dir"));
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

    @FXML
    private void onClickRollDice(ActionEvent event) {
        rollDiceButton.setDisable(true);
        diceImage.setVisible(true);
        Thread thread = new Thread() {
            public void run() {
                if(isNewGame == true) {
                    try {
                        int diceNumber;
                        for (int i = 0; i < 15; i++) {
                            Path currentPath = Paths.get(System.getProperty("user.dir"));
                            diceNumber = random.nextInt(6) + 1;
                            File file = new File(currentPath + "/Dices/dice-" + diceNumber + ".png");
                            diceImage.setImage(new Image(file.toURI().toString()));
                            Thread.sleep(50);

                            checkActive(i , diceNumber , file);
                        
                        rollDiceButton.setDisable(false);
                    }
                    menu.game.printState();
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
