package GUI;

import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import log.Log;
import javafx.scene.control.Alert.AlertType;
import logic.FieldValidator;
import logic.DAO.CollaborationDAO;

public class OpenCollaborationController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(OpenCollaborationController.class);
    
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
    private Button buttonHome;

    @FXML
    private Button buttonCollaborations;

    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonMinimize;

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonOpenCollaboration;

    @FXML
    private int collaborationId;

    @FXML
    public void initialize(int collaborationIdEdit){
        this.collaborationId = collaborationIdEdit;
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        String name = collaborationDAO.getCollaborationNameById(collaborationId);
        String description = collaborationDAO.getCollaborationDescriptionById(collaborationId);
        String startDate = collaborationDAO.getCollaborationStartDateById(collaborationId);
        String finishDate = collaborationDAO.getCollaborationFinishDateById(collaborationId);
        String collaborationGoal = collaborationDAO.getCollaborationGoalById(collaborationId);
        String collaborationSubject = collaborationDAO.getCollaborationSubjectById(collaborationId);
        int noStudents = collaborationDAO.getNumberStudentsById(collaborationId);
        String studentProfile = collaborationDAO.getStudentProfileById(collaborationId);
        textFieldName.setText(name);
        textAreaDescription.setText(description);
        datePickerStartDate.setValue(LocalDate.parse(startDate));
        datePickerFinishDate.setValue(LocalDate.parse(finishDate));
        textFieldObjective.setText(collaborationGoal);
        comboBoxSubject.setValue(collaborationSubject);
        textFieldStudentCount.setText(String.valueOf(noStudents));
        textAreaStudentProfile.setText(studentProfile);
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
            //UserSessionManager.getInstance().logoutProfessor();
        } catch (IOException ioException){
            LOG.error(ioException);
        }
    }

    @FXML
    public void openCollaboration(ActionEvent event){
        String collaborationName = textFieldName.getText();
        String collaborationDescription = textAreaDescription.getText();
        String collaborationGoal = textFieldObjective.getText();
        String studentCount = textFieldStudentCount.getText();
        String studentProfile = textAreaStudentProfile.getText();
        String subject = comboBoxSubject.getValue();

        if (!collaborationName.isBlank() && !collaborationDescription.isBlank() && !collaborationGoal.isBlank() &&
        FieldValidator.onlyNumber(studentCount) && !studentProfile.isBlank() && !subject.isBlank()){
            int noStudents = Integer.parseInt(studentCount);
            if(noStudents > 0) {
                CollaborationDAO instance = new CollaborationDAO();
                int result = instance.changeCollaborationStatus("Activa",collaborationId);
                if (result == 1){
                    Alert collaborationUpdatedAlert = new Alert(AlertType.CONFIRMATION);
                    collaborationUpdatedAlert.setTitle("Confirmación registro");
                    collaborationUpdatedAlert.setHeaderText("Confirmación colaboración");
                    collaborationUpdatedAlert.setContentText("Se abrio de manera exitosa la colaboración");
                    collaborationUpdatedAlert.show();
                } else {
                    Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
                    emptyFieldsAlert.setTitle("Ocurrio un error");
                    emptyFieldsAlert.setContentText("Hubo un error al activar la colaboración");
                    emptyFieldsAlert.setHeaderText("Ocurrio un error");
                    emptyFieldsAlert.show();
                }
            } else {
                Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
                emptyFieldsAlert.setTitle("Cantidad de alumnos invalida");
                emptyFieldsAlert.setContentText("Se requieren más alumnos para activar una colaboración");
                emptyFieldsAlert.setHeaderText("Cantidad alumnos invalida");
                emptyFieldsAlert.show();
            }
        } else {
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos erroneos");
            emptyFieldsAlert.setContentText("No se pudo abrir la colaboración, revise los campos");
            emptyFieldsAlert.setHeaderText("Campos erroneos");
            emptyFieldsAlert.show();
        }
        
    }
}
