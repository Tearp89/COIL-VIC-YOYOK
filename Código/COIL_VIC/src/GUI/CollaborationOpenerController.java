package GUI;

import java.io.IOException;
import java.util.ArrayList;

import dataAccess.DatabaseConnectionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import log.Log;
import javafx.scene.control.Alert.AlertType;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class CollaborationOpenerController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(CollaborationOpenerController.class);
    
    @FXML
    private TextField textFieldName;

    @FXML
    private TextArea textAreaDescription;

    @FXML
    private TextField textFieldObjective;

    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private DatePicker datePickerFinishDate;

    @FXML
    private TextField textFieldStudentCount;

    @FXML
    private ComboBox<String> comboBoxSubject;

    @FXML
    private TextArea textAreaStudentProfile;

    @FXML
    private Button buttonOpenCollaboration;

    @FXML
    private int collaborationId;
    @FXML
    private Label labelCollaborationId;
    
    @FXML
    private Label labelUser;
    @FXML
    public void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        ProfessorDAO professorDAO = new ProfessorDAO();
        String user = professorData.getUser();
        int professorId = professorDAO.getProfessorIdByUser(user);

        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> publishedCollaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Publicada", professorId);
        collaborationId = publishedCollaborations.get(0).getCollaborationId();
        
        labelCollaborationId.setText(String.valueOf(publishedCollaborations.get(0).getCollaborationId()));
        textFieldName.setText(publishedCollaborations.get(0).getCollaborationName());
        datePickerStartDate.setValue(publishedCollaborations.get(0).getStartDate());
        datePickerFinishDate.setValue(publishedCollaborations.get(0).getFinishDate());
        
        
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
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
        try{
            ChangeWindowManager.logout(event, loginLoader);
        } catch (IOException logoutException){
            LOG.error("ERROR:", logoutException);
        }
    }

    @FXML
    private Button buttonCancel;
    @FXML
    private void cancel(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);

    }

    @FXML
    public void openCollaboration(ActionEvent event){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        CollaborationDAO instance = new CollaborationDAO();
        int numberStudents = instance.getNumberStudentsById(collaborationId);
        if (!instance.validateCollaborationProfessorsLimit(collaborationId) && !instance.validateCollaborationStudentLimit(collaborationId, numberStudents)){
            Alert collaborationOpenAlert = new Alert(AlertType.CONFIRMATION);
            collaborationOpenAlert.setTitle("Confirmación inicio colaboración");
            collaborationOpenAlert.setHeaderText("Confirmación inicio colaboración");
            collaborationOpenAlert.setContentText("¿Está seguro de que desea iniciar la colaboración?");
            collaborationOpenAlert.show();
            ButtonType accept = new ButtonType("Aceptar");
            ButtonType cancel = new ButtonType("Cancelar");
            collaborationOpenAlert.getButtonTypes().setAll(cancel, accept);
            Button okButton = (Button) collaborationOpenAlert.getDialogPane().lookupButton(accept);
            Button cancelButton = (Button) collaborationOpenAlert.getDialogPane().lookupButton(cancel);
            
            okButton.setOnAction(eventAddProfessorUV -> {
                int result = instance.changeCollaborationStatus("Activa",collaborationId);
                if (result == 1){
                    Alert collaborationUpdatedAlert = new Alert(AlertType.INFORMATION);
                    collaborationUpdatedAlert.setTitle("Confirmación registro");
                    collaborationUpdatedAlert.setHeaderText("Confirmación colaboración");
                    collaborationUpdatedAlert.setContentText("Se abrio de manera exitosa la colaboración");
                    collaborationUpdatedAlert.show();
                } else {
                    Alert errorCollaborationAlert = new Alert(AlertType.ERROR);
                    errorCollaborationAlert.setTitle("Ocurrio un error");
                    errorCollaborationAlert.setContentText("Hubo un error al activar la colaboración");
                    errorCollaborationAlert.setHeaderText("Ocurrio un error");
                    errorCollaborationAlert.show();
                } 
            });
            cancelButton.setOnAction(eventCancelAdding -> {
                collaborationOpenAlert.close();
            });
        }else{
            Alert noCollaboratorAlert = new Alert(AlertType.ERROR);
            noCollaboratorAlert.setTitle("No hay colaborador o faltan estudiantes");
            noCollaboratorAlert.setHeaderText("No hay colaborador o estudiantes");
            noCollaboratorAlert.setContentText("No se puede abrir la colaboración, no hay un colaborador asignado o no se han asignado los estudiante establecidos");
            noCollaboratorAlert.show();
        }
    }
}
