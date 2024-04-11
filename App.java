import java.io.File;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.nio.file.*;
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Path currentPath = Paths.get(System.getProperty("user.dir"));
        File file = new File("DiceGame/Dices/game-icon.png");
        Image icon = new Image(file.toURI().toString());
        primaryStage.getIcons().add(icon);
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controller.fxml"));
        Parent root = loader.load();
        
        // Create a Scene
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("application.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        // Set the Scene to the Stage
    
        primaryStage.setScene(scene);
        
        // Set the title of the Stage
        primaryStage.setTitle("DICE GAME");
        primaryStage.show();
    }   

    public static void main(String[] args) {
        launch(args);
    }
}
