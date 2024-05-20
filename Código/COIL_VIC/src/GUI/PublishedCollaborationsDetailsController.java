package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Professor;

public class PublishedCollaborationsDetailsController {
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
    Button buttonSendRequest;
    @FXML
    int collaborationId;
    @FXML 
    int professorId;
    @FXML
    Label user;

    @FXML
    void sendRequestToCollaborate(ActionEvent event){
        ProfessorDAO professorDAO = new ProfessorDAO();
        // professorId = professorDAO.getProfessorIdByUser(user.getText());
        professorId = 25;
        Alert confirmRequest = new Alert(AlertType.CONFIRMATION);
        confirmRequest.setHeaderText("Confirma aplicar a colaboración");
        confirmRequest.setTitle("Confirmar aplicar a colaboración");
        confirmRequest.setContentText("¿Está seguro de que desea aplicar a esta colaboración?");
        ButtonType confirmRequestToCollaborate = new ButtonType("Aceptar");
        ButtonType cancelRequest = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
        confirmRequest.getButtonTypes().setAll(confirmRequestToCollaborate, cancelRequest);

        Button okButton = (Button) confirmRequest.getDialogPane().lookupButton(confirmRequestToCollaborate);
        Button cancelButton = (Button) confirmRequest.getDialogPane().lookupButton(cancelRequest);

        okButton.setOnAction(eventSendRequest -> {
            //TODO: Agregar estado
            int result = professorDAO.professorRequestCollaboration(collaborationId, professorId);
            if (result == 1) {
                Alert requestSuccessful = new Alert(AlertType.INFORMATION);
                requestSuccessful.setHeaderText("Solicitud exitosa");
                requestSuccessful.setTitle("Solicitud exitosa");
                requestSuccessful.setContentText("Solicitud enviada exitosamente");
                //ButtonType acceptAlert = new ButtonType("Aceptar");
                
                requestSuccessful.showAndWait();
            } else {
                
            }
        });

        cancelButton.setOnAction(eventConfirmCancel -> {
            Alert confirmCancel = new Alert(AlertType.CONFIRMATION);
            confirmCancel.setTitle("Confirmar cancelación");
            confirmCancel.setHeaderText("Confirmar cancelación");
            confirmCancel.setContentText("¿Está seguro de que desea salir?");
            confirmCancel.show();
            //TODO: Agregar interacción entre ventanas para cancelar
        });

        
    
        confirmRequest.showAndWait();
       
       
        
    }

    @FXML
    void cancelRequest(ActionEvent event){
        //TODO: Agregar cancelar
    }

    public void initialize(int collaborationId){
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
