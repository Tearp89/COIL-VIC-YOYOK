package GUI;

import java.io.IOException;

import dataAccess.DatabaseConnectionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Professor;

public class CollaborationOptionsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(CollaborationOptionsController.class);
    @FXML
    private Button buttonHome;

    @FXML
    private void goToHomePage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML 
    private Button buttonCollaboration;

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationsLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsLoader);

    }
    @FXML
    private Button buttonStudents;
    
    @FXML
    private void goToStudents(ActionEvent event){
        FXMLLoader goToStudentsLoader = new FXMLLoader(getClass().getResource("/GUI/StudentOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, goToStudentsLoader);

    }
    @FXML
    private Button buttonSettings;

    @FXML
    private void goToSettings(ActionEvent event){
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorSettingsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, settingsLoader);

    }

    @FXML
    private Button buttonAddCollaboration;

    @FXML
    private void addCollaboration(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader addCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/AddCollaborationWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, addCollaborationLoader);
    }

    @FXML
    private Button buttonPublishCollaboration;
    @FXML
    private void publishCollaboration(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader publishCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/AcceptedCollaborationsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, publishCollaborationLoader);
    }

    @FXML
    private Button buttonEditCollaboration;
    @FXML
    private void editCollaboration(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader editCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/DeclinedCollaborationsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, editCollaborationLoader);
    }

    @FXML
    private Button buttonOpenCollaboration;
    @FXML
    private String professorUser;
    @FXML
    private void openCollaboration(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader openCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/OpenCollaborationWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, openCollaborationLoader);

    }

    @FXML
    private Button buttonCloseCollaboration;

    @FXML
    private void closeCollaboration(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader closeCollaboraitionLoader = new FXMLLoader(getClass().getResource("/GUI/CloseCollaborationWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, closeCollaboraitionLoader);
    }

    @FXML 
    private Button buttonGradeCollaboration;

    @FXML
    private void gradeCollaboration(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader gradeCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorFeedbackCollaborationWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, gradeCollaborationLoader);

    }

    @FXML
    private Button buttonSendRequest;

    @FXML
    private void sendRequest(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader sendRequestLoader = new FXMLLoader(getClass().getResource("/GUI/PublishedCollaborationsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, sendRequestLoader);
    }

    @FXML
    private Button buttonAnswerRequest;

    @FXML
    private void answerRequest(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader answerRequestLoader = new FXMLLoader(getClass().getResource("/GUI/SearchRequestToCollaborateWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, answerRequestLoader);
    }

    @FXML
    private Button buttonLogout;

    @FXML
    private void logOut(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
        try{
            ChangeWindowManager.logout(event, loginLoader);
        } catch (IOException logoutException){
            LOG.error("ERROR:", logoutException);
        }
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
    private Button buttonSearchActivity;
    @FXML
    private void searchActivity(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader searchActivityLoader = new FXMLLoader(getClass().getResource("/GUI/SearchActivitiesWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, searchActivityLoader);

    }

    @FXML
    private Button buttonAddActivity;
    @FXML
    private void addActivity(ActionEvent event){
        checkDatabaseConnection();
        FXMLLoader addActivityLoader = new FXMLLoader(getClass().getResource("/GUI/AddActivityWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, addActivityLoader);
    }

    @FXML 
    private Label labelUser; 

    @FXML
    private void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        this.professorUser = professorData.getUser();
        checkDatabaseConnection();

        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
        int validateActiveCollaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Activa", professorId).size();
        if(validateActiveCollaborations == 0){
            buttonCloseCollaboration.setDisable(true);
        } else if(validateActiveCollaborations > 0){
            buttonPublishCollaboration.setDisable(true);
        }

        int validateClosedCollaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Cerrada", professorId).size();
        if(validateClosedCollaborations == 0){
            buttonGradeCollaboration.setDisable(true);
        }

        int validatePublishedCollaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Publicada", professorId).size();
        if(validatePublishedCollaborations == 1){
            buttonPublishCollaboration.setDisable(true);
            buttonSendRequest.setDisable(true);
        } else if (validateActiveCollaborations == 0){
            buttonAddActivity.setDisable(true);
            buttonSearchActivity.setDisable(true);
            buttonOpenCollaboration.setDisable(true);
            buttonAnswerRequest.setDisable(true);
        }

        int validateDeclinedCollaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Rechazada", professorId).size();
        if(validateDeclinedCollaborations == 0){
            buttonEditCollaboration.setDisable(true);
        }

    }


    private void checkDatabaseConnection(){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            buttonAddActivity.setDisable(true);
            buttonAddCollaboration.setDisable(true);
            buttonAnswerRequest.setDisable(true);
            buttonCloseCollaboration.setDisable(true);
            buttonCollaboration.setDisable(true);
            buttonEditCollaboration.setDisable(true);
            buttonGradeCollaboration.setDisable(true);
            buttonOpenCollaboration.setDisable(true);
            buttonPublishCollaboration.setDisable(true);
            buttonSearchActivity.setDisable(true);
            buttonSendRequest.setDisable(true);
        }
    }

}
