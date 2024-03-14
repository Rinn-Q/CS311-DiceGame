package DiceGame;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Controller.fxml"));
        Parent root = loader.load();
        
        // Create a Scene
        Scene scene = new Scene(root);
        
        // Set the Scene to the Stage
    
        primaryStage.setScene(scene);
        
        // Set the title of the Stage
        primaryStage.setTitle("DICE GAME");
        
        // Show the Stage
        primaryStage.show();
    }   

    public static void main(String[] args) {
        launch(args);
    }
}
