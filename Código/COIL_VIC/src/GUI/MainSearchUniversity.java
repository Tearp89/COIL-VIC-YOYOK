package GUI;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log;
import logic.DAO.CollaborationDAO;

public class MainSearchUniversity extends Application{
    private static final org.apache.log4j.Logger LOG = Log.getLogger(MainSearchUniversity.class);
    @Override
    public void start(Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/searchUniversity.fxml"));
            Parent root = loader.load();
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            
            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show();
            primaryStage.setOnShown(event -> primaryStage.centerOnScreen());
        
        } catch(IOException loaderException){
            LOG.error("ERROR:", loaderException);
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }


}
