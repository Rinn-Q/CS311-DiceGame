import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Image icon = new Image(getClass().getResourceAsStream("game-icon.png"));
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
        
        // Show the Stage
        primaryStage.show();
    }   

    public static void main(String[] args) {
        launch(args);
    }
}
