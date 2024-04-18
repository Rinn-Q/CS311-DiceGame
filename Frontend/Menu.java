package Frontend;

import Backend.menu;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Menu {
    menu menu = new menu();

    public void showMenu() {
        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Тоглоомын танилцуулга");

        alert.setContentText(menu.getInstruction());

        ButtonType closeButton = new ButtonType("OKAYYY", ButtonData.OK_DONE);
        alert.getButtonTypes().add(closeButton);

        Button closeBtn = (Button) alert.getDialogPane().lookupButton(closeButton);
        closeBtn.setOnAction(e -> alert.close());


        alert.getDialogPane().setPrefWidth(350);
        alert.getDialogPane().setPrefHeight(250);
        alert.showAndWait();
    }
}
