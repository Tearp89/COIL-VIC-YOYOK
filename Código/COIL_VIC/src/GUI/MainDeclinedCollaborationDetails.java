package GUI;

import static javafx.application.Application.launch;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainDeclinedCollaborationDetails extends Application{

    @Override
    public void start(Stage primaryStage) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/declinedCollaborationDetails.fxml"));
        Parent root = loader.load();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnShown(event -> primaryStage.centerOnScreen());

        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
