package GUI;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import logic.DAO.CollaborationDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;
import logic.classes.ProfessorAcceptedAlert;

public class DeclinedCollaborationDetailsController {
    @FXML
    TextField textFieldCollaborationName;
    @FXML
    TextArea textAreaCollaborationDescription;
    @FXML
    DatePicker datePickerStartDate;
    @FXML
    DatePicker datePickerFinishDate;
    @FXML
    TextField textFieldCollaborationGoal;
    @FXML
    TextField textFieldNumberStudents;
    @FXML
    ComboBox comboBoxSubject;
    @FXML
    TextArea textAreaStudentProfile;
    @FXML
    Button buttonSendCollaboration;
    @FXML
    Button buttonCancel;
    @FXML
    Button buttonEditCollaboration;
    @FXML
    int collaborationId;

    @FXML
    void editCollaboration(ActionEvent event){
        textFieldCollaborationName.setEditable(true);
        textAreaCollaborationDescription.setEditable(true);
        datePickerStartDate.setEditable(true);
        datePickerFinishDate.setEditable(true);
        textFieldCollaborationGoal.setEditable(true);
        textFieldNumberStudents.setEditable(true);
        textAreaStudentProfile.setEditable(true);
        buttonEditCollaboration.setVisible(false);
        buttonCancel.setVisible(true);
        buttonSendCollaboration.setVisible(true);


    }
    @FXML
    void cancelEdition(ActionEvent event){

    }

    @FXML
    void sendCollaboration(ActionEvent event){
        String collaborationName = textFieldCollaborationName.getText();
        String collaborationDescription = textAreaCollaborationDescription.getText();
        LocalDate collaborationStartDate = datePickerStartDate.getValue();
        LocalDate collaborationFinishDate = datePickerFinishDate.getValue();
        String collaborationGoal = textFieldCollaborationGoal.getText();
        String collaborationSubject = comboBoxSubject.getValue().toString();
        int numberStudents = Integer.parseInt(textFieldNumberStudents.getText());
        String studentProfile = textAreaStudentProfile.getText();
        Collaboration collaborationUpdated = new Collaboration();
        collaborationUpdated.setCollaborationName(collaborationName);
        collaborationUpdated.setDescription(collaborationDescription);
        collaborationUpdated.setStartDate(collaborationStartDate);
        collaborationUpdated.setFinishDate(collaborationFinishDate);
        collaborationUpdated.setCollaborationGoal(collaborationGoal);
        collaborationUpdated.setSubject(collaborationSubject);
        collaborationUpdated.setNoStudents(numberStudents);
        collaborationUpdated.setStudentProfile(studentProfile);
        collaborationUpdated.setCollaborationId(collaborationId);
        collaborationUpdated.setCollaborationStatus("En revisión");
        CollaborationDAO updateCollaborationDAO = new CollaborationDAO();
        if(collaborationName.isEmpty() || collaborationDescription.isEmpty() || collaborationStartDate.toString().isEmpty() || 
        collaborationFinishDate.toString().isEmpty() || collaborationGoal.isEmpty() || collaborationSubject.isEmpty() || 
        textFieldNumberStudents.getText().isEmpty() || studentProfile.isEmpty()){
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos vacíos");
            emptyFieldsAlert.setContentText("No se pudo actualizar la colaboración hay campos vacíos");
            emptyFieldsAlert.setHeaderText("Campos vacíos");
            emptyFieldsAlert.show();


        }else{
            int result = updateCollaborationDAO.updateCollaboration(collaborationUpdated);
            if (result == 1){
                Alert collaborationUpdatedAlert = new Alert(AlertType.CONFIRMATION);
                collaborationUpdatedAlert.setTitle("Confirmación registro");
                collaborationUpdatedAlert.setHeaderText("Confirmación actualización");
                collaborationUpdatedAlert.setContentText("Se actualizó la información de la convocatoria de manera exitosa");
                collaborationUpdatedAlert.show();
            }

        }
      
        

    }

    @FXML
    void initialize(int collaborationId){
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
        textFieldCollaborationName.setText(name);
        textAreaCollaborationDescription.setText(description);
        datePickerStartDate.setEditable(false);
        datePickerStartDate.setValue(LocalDate.parse(startDate));
        datePickerFinishDate.setEditable(false);
        datePickerFinishDate.setValue(LocalDate.parse(finishDate));
        textFieldCollaborationGoal.setText(collaborationGoal);
        comboBoxSubject.setValue(collaborationSubject);
        textFieldNumberStudents.setText(String.valueOf(noStudents));
        textAreaStudentProfile.setText(studentProfile);





    }


}
