package GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        ChangeWindowManager.minimizeWindow(event);
    }

    @FXML
    private void closeWindow(ActionEvent event){
        ChangeWindowManager.closeWindow(event);
    }

    @FXML
    private void goToNumeralia(ActionEvent event){
        FXMLLoader numeraliaLoader = new FXMLLoader(getClass().getResource("/GUI/numeralia.fxml"));
        ChangeWindowManager.changeWindowTo(event, numeraliaLoader);
    }

    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try {
            ChangeWindowManager.logout(event, loginLoader);
            UserSessionManager.getInstance().logoutAdmin();
        } catch (IOException ioException){
            LOG.error(ioException);
        }
    }
}