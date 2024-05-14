package GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log;
import logic.classes.Admin;

public class AdminHomeController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AdminHomeController.class);

    @FXML
    private Button buttonCollaborations;

    @FXML
    private Button buttonProfessors;
    
    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonMinimize;

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonNumeralia;

    @FXML
    private Button buttonConfiguration;
    
    @FXML
    private Label labelName;

    @FXML
    private void initialize(){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        labelName.setText(adminData.getAdminName());
    }

    @FXML
    private void minimizeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void closeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void goToNumeralia(ActionEvent event){
        

        FXMLLoader numeraliaLoader = new FXMLLoader(getClass().getResource("/GUI/numeralia.fxml"));
        try {
            Parent root = numeraliaLoader.load();
            Scene numeraliaScene = new Scene(root);
            Stage numeraliaStage = new Stage();
            numeraliaStage.initStyle(StageStyle.TRANSPARENT);
            numeraliaStage.setScene(numeraliaScene);
            numeraliaStage.show();

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException goToNumeraliaException){
            LOG.error(goToNumeraliaException);
        }
    }

    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try {
            UserSessionManager.getInstance().logoutAdmin();
            Parent root = loginLoader.load();
            Scene loginScene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setScene(loginScene);
            loginStage.show();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException goToLoginException){
            LOG.error(goToLoginException);
        }
    }
}