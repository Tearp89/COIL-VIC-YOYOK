package GUI;

import java.io.IOException;

import dataAccess.DatabaseConnectionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import log.Log;
import javafx.scene.control.Alert.AlertType;
import logic.DAO.CollaborationDAO;
import logic.classes.Professor;

public class AcceptedCollaborationDetailsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AcceptedCollaborationDetailsController.class);
    
    @FXML
    Label labelCollaborationName;
    @FXML
    Label labelCollaborationDescription;
    @FXML
    Label labelStartDate;
    @FXML
    Label labelFinishDate;
    @FXML
    Label labelCollaborationGoal;
    @FXML
    Label labelNumberStudents;
    @FXML
    Label labelSubject;
    @FXML
    Label labelStudentProfile;
    @FXML
    Button buttonCancel;
    @FXML
    Button buttonUploadCollaboration;
    @FXML
    int collaborationId;

    @FXML
    void uploadCollaboration(ActionEvent event){
        checkDatabaseConnection();
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        
        int result = collaborationDAO.changeCollaborationStatus("Publicada", collaborationId);
        if(result == 1){
            Alert publishSucessAlert = new Alert(AlertType.INFORMATION);
            publishSucessAlert.setTitle("Oferta publicada");
            publishSucessAlert.setHeaderText("Oferta publicada");
            publishSucessAlert.setContentText("Colaboración ofertada con éxito");
            publishSucessAlert.show();
        } else{
            Alert publishErrorAlert = new Alert(AlertType.ERROR);
                    publishErrorAlert.setTitle("Error conexión");
                    publishErrorAlert.setHeaderText("Error conexión");
                    publishErrorAlert.setContentText("No se pudo conectar a la base de datos, inténtelo de nuevo más tarde");
                    publishErrorAlert.show();

        }

    }

    @FXML
    private void cancelUpload(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);

    }

    @FXML
    private Button buttonHome;

    @FXML
    private void goToHomePage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonCollaborations;

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationsOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsOptionsLoader);


    }

    @FXML
    private Button buttonStudents;

    @FXML
    private void goToStudents(ActionEvent event){
        FXMLLoader studentsLoader = new FXMLLoader(getClass().getResource("/GUI/StudentOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, studentsLoader);

    }

    @FXML
    private Button buttonSettings;

    @FXML
    private void goToSettings(ActionEvent event){

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
    private void logOut(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
        try{
            ChangeWindowManager.logout(event, loginLoader);
        } catch (IOException logoutException){
            LOG.error("ERROR:", logoutException);
        }
    }



    @FXML
    private Label labelUser;

    @FXML
    public void initialize(int collaborationId) {
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        this.collaborationId = collaborationId;
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        String name = collaborationDAO.getCollaborationNameById(collaborationId);
        String description = collaborationDAO.getCollaborationDescriptionById(collaborationId);
        String startDate = collaborationDAO.getCollaborationStartDateById(collaborationId);
        String finishDate = collaborationDAO.getCollaborationFinishDateById(collaborationId);
        String collaborationGoal = collaborationDAO.getCollaborationGoalById(collaborationId);
        String collaborationSubject = collaborationDAO.getCollaborationSubjectById(collaborationId);
        int noStudents = collaborationDAO.getNumberStudentsById(collaborationId);
        String studentProfile = collaborationDAO.getStudentProfileById(collaborationId);
        labelCollaborationName.setText(name);
        labelCollaborationDescription.setText(description);
        labelStartDate.setText(startDate);
        labelFinishDate.setText(finishDate);
        labelCollaborationGoal.setText(collaborationGoal);
        labelSubject.setText(collaborationSubject);
        labelNumberStudents.setText(String.valueOf(noStudents));
        labelStudentProfile.setText(studentProfile);
        checkDatabaseConnection();
        

        
    }
    private void checkDatabaseConnection(){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            buttonUploadCollaboration.setDisable(true);
        }
    }

}
