package GUI;

import java.io.IOException;

import javax.swing.Action;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import log.Log;
import logic.classes.Admin;

public class AdminProfessorOptionsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AdminProfessorOptionsController.class);
    
    @FXML
    private Label labelUser;


    @FXML
    private void initialize(){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        labelUser.setText(adminData.getAdminName());
    }

    @FXML
    private Button buttonMinimize;
    @FXML
    private void minimizeWindow(ActionEvent event){
        ChangeWindowManager.minimizeWindow(event);
    }

    @FXML
    private Button buttonClose;
    @FXML
    private void closeWindow(ActionEvent event){
        ChangeWindowManager.closeWindow(event);
    }


    @FXML
    private Button buttonNumeralia;
    @FXML
    private void goToNumeralia(ActionEvent event){
        FXMLLoader numeraliaLoader = new FXMLLoader(getClass().getResource("/GUI/numeralia.fxml"));
        ChangeWindowManager.changeWindowTo(event, numeraliaLoader);
    }
    @FXML
    private Button buttonLogout;
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

    @FXML
    private Button buttonCollaborations;
    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminCollaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
    }

    @FXML
    private Button buttonProfessors;
    @FXML
    private void goToProfessors(ActionEvent event){
        FXMLLoader professorOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminProfessorOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, professorOptionsLoader);
    }

    @FXML
    private Button buttonUniversities;
    @FXML
    private void goToUniversities(ActionEvent event){
        FXMLLoader universitiesOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminUniversityOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, universitiesOptionsLoader);
    }

    @FXML
    private Button buttonHome;
    @FXML
    private void goToHomePage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/adminHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonSearchProfessors;
    @FXML
    private void searchProfessors(ActionEvent event){
        FXMLLoader searchProfessorsController = new FXMLLoader(getClass().getResource("/GUI/consultProfessors.fxml"));
        ChangeWindowManager.changeWindowTo(event, searchProfessorsController);
    }

    @FXML
    private Button buttonAnswerRequest;
    @FXML
    private void answerRequest(ActionEvent event){
        FXMLLoader requestLaoder = new FXMLLoader(getClass().getResource("/GUI/requests.fxml"));
        ChangeWindowManager.changeWindowTo(event, requestLaoder);
    }

}
