package GUI;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import log.Log;
import javafx.scene.control.Alert.AlertType;
import logic.FieldValidator;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

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
    private Button buttonOpenCollaboration;

    @FXML
    private int collaborationId;
    @FXML
    private Label labelCollaborationId;

    private void setValues(Collaboration collaboration){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelCollaborationId.setText(String.valueOf(collaboration.getCollaborationId()));
        textFieldName.setText(collaboration.getCollaborationName());
        datePickerStartDate.setValue(collaboration.getStartDate());
        datePickerFinishDate.setValue(collaboration.getFinishDate());
        textAreaStudentProfile.setText(collaboration.getStudentProfile());
        textFieldStudentCount.setText(String.valueOf(collaboration.getNoStudents()));
        textFieldObjective.setText(collaboration.getCollaborationGoal());

      

        

    }
    
    @FXML
    private Label labelUser;
    @FXML
    public void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());

        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> publishedCollaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Publicada", professorId);
       
        
        labelCollaborationId.setText(String.valueOf(publishedCollaborations.get(0).getCollaborationId()));
        textFieldName.setText(publishedCollaborations.get(0).getCollaborationName());
        datePickerStartDate.setValue(publishedCollaborations.get(0).getStartDate());
        datePickerFinishDate.setValue(publishedCollaborations.get(0).getFinishDate());
        textAreaStudentProfile.setText(publishedCollaborations.get(0).getStudentProfile());
        textFieldStudentCount.setText(String.valueOf(publishedCollaborations.get(0).getNoStudents()));
        textFieldObjective.setText(publishedCollaborations.get(0).getCollaborationGoal());
        
        
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
    private void logout(ActionEvent event){
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
    private void cancel(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);

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
