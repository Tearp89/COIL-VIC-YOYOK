package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import log.Log;
import logic.classes.Professor;

public class ProfessorSettingsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ProfessorSettingsController.class);

    @FXML
    private Label labelName;

    @FXML
    private Label labelPhone;

    @FXML
    private Label labelAcademicArea;

    @FXML
    private Label labelEmail;

    @FXML
    private TextField textFieldOldPassword;

    @FXML
    private TextField textFieldNewPassword;

    @FXML
    private Button buttonChangePassword;

    @FXML
    private Button buttonMinimize;

    @FXML 
    private Button buttonClose;

    @FXML
    private Button buttonHome;

    @FXML
    private Button buttonCollaboration;

    @FXML
    private Button buttonStudents;

    @FXML
    private Button buttonLogout;

    @FXML
    public void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelName.setText(professorData.getName());
        labelPhone.setText(professorData.getPhoneNumber());
        labelEmail.setText(professorData.getEmail());
        labelAcademicArea.setText(professorData.getAcademicArea());
    }

    @FXML
    public void minimizeWindow(ActionEvent event){
        ChangeWindowManager.minimizeWindow(event);
    }

    @FXML
    public void closeWindow(ActionEvent event){
        ChangeWindowManager.closeWindow(event);
    }

    @FXML
    public void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try {
            ChangeWindowManager.logout(event, loginLoader);
            UserSessionManager.getInstance().logoutProfessor();
        } catch (IOException ioException){
            LOG.error(ioException);
        }
    }
}
