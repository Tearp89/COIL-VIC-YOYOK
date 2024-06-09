package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log;

public class ChangeWindowManager {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ChangeWindowManager.class);

    public static void minimizeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

    public static void closeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public static void changeWindowTo(ActionEvent event, FXMLLoader loader){
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
            Node source = (Node) event.getSource();
            Stage currenStage = (Stage) source.getScene().getWindow();
            currenStage.close();
        } catch (IOException changeWindowException){
            LOG.error("ERROR:", changeWindowException);
        }
    }

    public static void logout(ActionEvent event, FXMLLoader loginLoader) throws IOException{
        Parent root = loginLoader.load();
        Scene loginScene = new Scene(root);
        Stage loginStage = new Stage();
        loginStage.setScene(loginScene);
        loginStage.show();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
