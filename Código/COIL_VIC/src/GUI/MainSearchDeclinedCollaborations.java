package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.DAO.CollaborationDAO;

public class MainSearchDeclinedCollaborations extends Application{

    @Override
    public void start(Stage primaryStage)throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/DeclinedCollaborations.fxml"));
        Parent root = loader.load();

        SearchDeclinedCollaborationsController controller = loader.getController();

        
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }



}
