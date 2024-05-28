package GUI;

import java.io.IOException;

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
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/professorHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML 
    private Button buttonCollaboration;

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationsLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsLoader);

    }
    @FXML
    private Button buttonStudents;
    
    @FXML
    private void goToStudents(ActionEvent event){
        FXMLLoader goToStudentsLoader = new FXMLLoader(getClass().getResource("/GUI/studentOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, goToStudentsLoader);

    }
    @FXML
    private Button buttonSettings;

    @FXML
    private void goToSettings(ActionEvent event){
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/GUI/professorSettings.fxml"));
        ChangeWindowManager.changeWindowTo(event, settingsLoader);

    }

    @FXML
    private Button buttonAddCollaboration;

    @FXML
    private void addCollaboration(ActionEvent event){
        FXMLLoader addCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/addCollaboration.fxml"));
        ChangeWindowManager.changeWindowTo(event, addCollaborationLoader);
    }

    @FXML
    private Button buttonPublishCollaboration;
    @FXML
    private void publishCollaboration(ActionEvent event){
        FXMLLoader publishCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/acceptedCollaborations.fxml"));
        ChangeWindowManager.changeWindowTo(event, publishCollaborationLoader);
    }

    @FXML
    private Button buttonEditCollaboration;
    @FXML
    private void editCollaboration(ActionEvent event){
        FXMLLoader editCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/DeclinedCollaborations.fxml"));
        ChangeWindowManager.changeWindowTo(event, editCollaborationLoader);
    }

    @FXML
    private Button buttonOpenCollaboration;
    @FXML
    private String professorUser;
    @FXML
    private void openCollaboration(ActionEvent event){
        
        FXMLLoader openCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/openCollaboration.fxml"));
         ChangeWindowManager.changeWindowTo(event, openCollaborationLoader);

    }

    @FXML
    private Button buttonCloseCollaboration;

    @FXML
    private void closeCollaboration(ActionEvent event){
        
        FXMLLoader closeCollaboraitionLoader = new FXMLLoader(getClass().getResource("/GUI/closeCollaboration.fxml"));
        ChangeWindowManager.changeWindowTo(event, closeCollaboraitionLoader);
    }

    @FXML 
    private Button buttonGradeCollaboration;

    @FXML
    private void gradeCollaboration(ActionEvent event){
        FXMLLoader gradeCollaborationLoader = new FXMLLoader(getClass().getResource("/GUI/professorFeedbackCollaboration.fxml"));
        ChangeWindowManager.changeWindowTo(event, gradeCollaborationLoader);

    }

    @FXML
    private Button buttonSendRequest;

    @FXML
    private void sendRequest(ActionEvent event){
        FXMLLoader sendRequestLoader = new FXMLLoader(getClass().getResource("/GUI/publishedCollaborations.fxml"));
        ChangeWindowManager.changeWindowTo(event, sendRequestLoader);
    }

    @FXML
    private Button buttonAnswerRequest;

    @FXML
    private void answerRequest(ActionEvent event){
        FXMLLoader answerRequestLoader = new FXMLLoader(getClass().getResource("/GUI/searchRequestToCollaborate.fxml"));
        ChangeWindowManager.changeWindowTo(event, answerRequestLoader);
    }

    @FXML
    private Button buttonLogout;

    @FXML
    private void logOut(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
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
        FXMLLoader searchActivityLoader = new FXMLLoader(getClass().getResource("/GUI/searchActivities.fxml"));
        ChangeWindowManager.changeWindowTo(event, searchActivityLoader);

    }

    @FXML
    private Button buttonAddActivity;
    @FXML
    private void addActivity(ActionEvent event){
        FXMLLoader addActivityLoader = new FXMLLoader(getClass().getResource("/GUI/addActivity.fxml"));
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
        }

        int validateDeclinedCollaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Rechazada", professorId).size();
        if(validateDeclinedCollaborations == 0){
            buttonEditCollaboration.setDisable(true);
        }

    }


    

}
