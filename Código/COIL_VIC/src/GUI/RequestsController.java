package GUI;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import log.Log;
import logic.classes.Admin;

public class RequestsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(RequestsController.class);
    @FXML
    private ComboBox<String> comboBoxRequestType;
    @FXML
    private Button buttonAnswerRequest;

    @FXML
    private void answerRequest(ActionEvent event){
        if(comboBoxRequestType.getSelectionModel().getSelectedItem() == "Académico"){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SearchPendingProfessorWindow.fxml"));
            ChangeWindowManager.changeWindowTo(event, loader);;
            

        }else{   
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/SearchPendingCollaborationsWindow.fxml"));
            ChangeWindowManager.changeWindowTo(event, loader);
        }
    }

    @FXML
    private Button buttonCancel;
    @FXML
    private void cancelAnswer(ActionEvent event){
        FXMLLoader requestsLoader = new FXMLLoader(getClass().getResource("/GUI/RequestsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, requestsLoader);

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
        FXMLLoader numeraliaLoader = new FXMLLoader(getClass().getResource("/GUI/NumeraliaWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, numeraliaLoader);
    }

    @FXML
    private Button buttonLogout;
    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
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
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminCollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
    }

    @FXML
    private Button buttonProfessors;
    @FXML
    private void goToProfessors(ActionEvent event){
        FXMLLoader professorOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminProfessorOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, professorOptionsLoader);
    }

    @FXML
    private Button buttonUniversities;
    @FXML
    private void goToUniversities(ActionEvent event){
        FXMLLoader universitiesOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminUniversityOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, universitiesOptionsLoader);
    }

    @FXML
    private Button buttonHome;
    @FXML
    private void goToHomepage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/AdminHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Label labelUser;
    @FXML
    private void initialize(){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        labelUser.setText(adminData.getAdminName());
        ObservableList<String> requestTypes = comboBoxRequestType.getItems();
        requestTypes.setAll("Académico", "Colaboración");
        comboBoxRequestType.setItems(requestTypes);
    }

}
