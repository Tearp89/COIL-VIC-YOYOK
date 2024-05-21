package GUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import log.Log;
import logic.DAO.CollaborationDAO;

public class MainOpen extends Application{
    private static final org.apache.log4j.Logger LOG = Log.getLogger(CollaborationDAO.class);

    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/openCollaboration.fxml"));
            Parent root = loader.load();
            OpenCollaborationController controller = loader.getController();
            

            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show();
        }catch(IOException loaderException){
            LOG.error("ERROR:", loaderException);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}

