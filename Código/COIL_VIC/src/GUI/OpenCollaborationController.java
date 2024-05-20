package GUI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
import logic.classes.Collaboration;

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
    private void initialize(){
        List<String> comboBoxOptions = Arrays.asList("Opcion 1", "Opción 2");
        comboBoxSubject.getItems().addAll(comboBoxOptions);
    }

    @FXML
    private void minimizeWindow(ActionEvent event){
        ChangeWindowManager.minimizeWindow(event);
    }

    @FXML
    private void closeWindow(ActionEvent event){
        ChangeWindowManager.closeWindow(event);
    }

    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try {
            ChangeWindowManager.logout(event, loginLoader);
            //UserSessionManager.getInstance().logoutProfessor();
        } catch (IOException ioException){
            LOG.error(ioException);
        }
    }

    @FXML
    private void openCollaboration(ActionEvent event){
        String collaborationName = textFieldName.getText();
        String collaborationDescription = textAreaDescription.getText();
        String collaborationGoal = textFieldObjective.getText();
        String studentCount = textFieldStudentCount.getText();
        String studentProfile = textAreaStudentProfile.getText();
        LocalDate starDate = datePickerStartDate.getValue();
        LocalDate finishDate = datePickerFinishDate.getValue();
        String subject = comboBoxSubject.getValue();

        if (FieldValidator.onlyTextAndNumbers(collaborationName) && FieldValidator.onlyTextAndNumbers(collaborationDescription) && FieldValidator.onlyTextAndNumbers(collaborationGoal) &&
        FieldValidator.onlyNumber(studentCount) && FieldValidator.onlyText(studentProfile) && FieldValidator.onlyTextAndNumbers(subject)){
            int noStudents = Integer.parseInt(studentCount);
            Collaboration collaboration = new Collaboration();
            collaboration.setCollaborationName(collaborationName);
            collaboration.setCollaborationGoal(collaborationGoal);
            collaboration.setDescription(collaborationDescription);
            collaboration.setNoStudents(noStudents);
            collaboration.setStudentProfile(studentProfile);
            collaboration.setStartDate(starDate);
            collaboration.setFinishDate(finishDate);
            collaboration.setSubject(subject);

            CollaborationDAO instance = new CollaborationDAO();
            int result = instance.addCollaboration(collaboration);
            if (result == 1){
                Alert collaborationUpdatedAlert = new Alert(AlertType.CONFIRMATION);
                collaborationUpdatedAlert.setTitle("Confirmación registro");
                collaborationUpdatedAlert.setHeaderText("Confirmación colaboración");
                collaborationUpdatedAlert.setContentText("Se abrio de manera exitosa la colaboración");
                collaborationUpdatedAlert.show();

                textFieldName.clear();
                textAreaDescription.clear();
                textFieldObjective.clear();
                textFieldStudentCount.clear();
                textAreaStudentProfile.clear();
                datePickerStartDate.setValue(null);
                datePickerFinishDate.setValue(null);
                comboBoxSubject.setValue(null);
            }
        } else {
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos erroneos");
            emptyFieldsAlert.setContentText("No se pudo abrir la colaboración hay campos incorrectos");
            emptyFieldsAlert.setHeaderText("Campos erroneos");
            emptyFieldsAlert.show();
        }
        
    }
}
