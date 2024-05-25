package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import log.Log;

public class StudentsHomeController {

    private static final org.apache.log4j.Logger LOG = Log.getLogger(StudentsHomeController.class);


    @FXML
    private Button buttonHome;
    @FXML
    private void goToHomepage(ActionEvent event){
        FXMLLoader homePagLoader = new FXMLLoader(getClass().getResource("/GUI/studentsHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePagLoader);
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
    private Button buttonGradeCollaboration;
    @FXML
    private void gradeCollaborations(ActionEvent event){
        FXMLLoader gradeCollaborationsLoader = new FXMLLoader(getClass().getResource("/GUI/studentsFeedbackCollaboration.fxml"));
        ChangeWindowManager.changeWindowTo(event, gradeCollaborationsLoader);

    }





}
