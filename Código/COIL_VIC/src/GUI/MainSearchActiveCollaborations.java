package GUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainSearchActiveCollaborations extends Application{

    @Override
    public void start(Stage primaryStage){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/activeCollaborations.fxml"));
            Parent root = loader.load();
            
            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show();

        }catch(IOException loaderException){
            loaderException.printStackTrace();
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }


}
