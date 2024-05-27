package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import log.Log;
import javafx.scene.control.Alert.AlertType;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Professor;

public class PublishedCollaborationsDetailsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(PublishedCollaborationsDetailsController.class);

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
    int collaborationId;
    @FXML 
    int professorId;
    @FXML
    Label user;

    @FXML
    Button buttonSendRequest;
    @FXML
    void sendRequestToCollaborate(ActionEvent event){
        ProfessorDAO professorDAO = new ProfessorDAO();
        Professor professorData = UserSessionManager.getInstance().getProfessorUserData();
        
        professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
        Alert confirmRequestAlert = new Alert(AlertType.CONFIRMATION);
        confirmRequestAlert.setHeaderText("Confirma aplicar a colaboración");
        confirmRequestAlert.setTitle("Confirmar aplicar a colaboración");
        confirmRequestAlert.setContentText("¿Está seguro de que desea aplicar a esta colaboración?");
        ButtonType confirmRequestToCollaborate = new ButtonType("Aceptar");
        ButtonType cancelRequest = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        confirmRequestAlert.getButtonTypes().setAll(confirmRequestToCollaborate, cancelRequest);

        Button okButton = (Button) confirmRequestAlert.getDialogPane().lookupButton(confirmRequestToCollaborate);
        Button cancelButton = (Button) confirmRequestAlert.getDialogPane().lookupButton(cancelRequest);

        okButton.setOnAction(eventSendRequest -> {
            //TODO: Agregar estado
            int result = professorDAO.professorRequestCollaboration(collaborationId, professorId);
            if (result == 1) {
                Alert requestSuccessfulAlert = new Alert(AlertType.INFORMATION);
                requestSuccessfulAlert.setHeaderText("Solicitud exitosa");
                requestSuccessfulAlert.setTitle("Solicitud exitosa");
                requestSuccessfulAlert.setContentText("Solicitud enviada exitosamente");
                //ButtonType acceptAlert = new ButtonType("Aceptar");
                
                requestSuccessfulAlert.showAndWait();
            } 
        });

        cancelButton.setOnAction(eventConfirmCancel -> {
            confirmRequestAlert.close();
            /*Alert confirmCancelAlert = new Alert(AlertType.CONFIRMATION);
            confirmCancelAlert.setTitle("Confirmar cancelación");
            confirmCancelAlert.setHeaderText("Confirmar cancelación");
            confirmCancelAlert.setContentText("¿Está seguro de que desea salir?");
            confirmCancelAlert.show();*/
            //TODO: Agregar interacción entre ventanas para cancelar
        });

        
    
        confirmRequestAlert.showAndWait();
       
       
        
    }

     @FXML
    private Button buttonHome;

    @FXML
    private void goToHomePage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/professorHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonCollaborations;

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationsOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsOptionsLoader);


    }

    @FXML
    private Button buttonStudents;

    @FXML
    private void goToStudents(ActionEvent event){
        FXMLLoader studentsLoader = new FXMLLoader(getClass().getResource("/GUI/studentOptions.fxml"));
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
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try{
            ChangeWindowManager.logout(event, loginLoader);
        } catch (IOException logoutException){
            LOG.error("ERROR:", logoutException);
        }
    }



    
    @FXML
    private Button buttonCancel;
    @FXML
    void cancelRequest(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }
    @FXML 
    private Label labelUser;
    public void initialize(int collaborationId){
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
    }

}
