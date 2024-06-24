package GUI;

import java.io.IOException;
import java.time.LocalDate;

import dataAccess.DatabaseConnectionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import log.Log;
import javafx.scene.control.Alert.AlertType;
import logic.CharLimitValidator;
import logic.CollaborationValidator;
import logic.DAO.CollaborationDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class DeclinedCollaborationDetailsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(DeclinedCollaborationDetailsController.class);

    @FXML
    private TextField textFieldCollaborationName;
    @FXML
    private TextArea textAreaCollaborationDescription;
    @FXML
    private DatePicker datePickerStartDate;
    @FXML
    private DatePicker datePickerFinishDate;
    @FXML
    private TextField textFieldCollaborationGoal;
    @FXML
    private TextField textFieldNumberStudents;
    @FXML
    private ComboBox<String> comboBoxSubject;
    @FXML
    private TextArea textAreaStudentProfile;
    
    
    @FXML
    private int collaborationId;

    @FXML
    Button buttonEditCollaboration;
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
        comboBoxSubject.setEditable(true);


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
    private Button buttonCancel;
    @FXML
    private void cancel(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);

    }


    @FXML
    private Button buttonCancelEdition;
    @FXML
    private void cancelEdition(ActionEvent event){
        Alert confirmCancelAlert = new Alert(AlertType.CONFIRMATION);
        confirmCancelAlert.setTitle("Confirmar cancelación");
        confirmCancelAlert.setHeaderText("Confirmar cancelación");
        confirmCancelAlert.setContentText("¿Está seguro de que desea salir? Se perderán todos sus cambios");
        confirmCancelAlert.show();
        ButtonType acceptCancel = new ButtonType("Aceptar");
        ButtonType cancelCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        confirmCancelAlert.getButtonTypes().setAll(acceptCancel, cancelCancel);
        confirmCancelAlert.show();
        Button okButton = (Button) confirmCancelAlert.getDialogPane().lookupButton(acceptCancel);
        Button cancelButton = (Button) confirmCancelAlert.getDialogPane().lookupButton(cancelCancel);
        okButton.setOnAction(eventAcceptCancel -> {
            FXMLLoader collaborationOptionLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
            ChangeWindowManager.changeWindowTo(event, collaborationOptionLoader);
        });

        cancelButton.setOnAction(eventCancelCancel -> {
            confirmCancelAlert.close();
        });


    }


    @FXML
    Button buttonSendCollaboration;
    @FXML
    void sendCollaboration(ActionEvent event){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        String collaborationName = textFieldCollaborationName.getText();
        String collaborationDescription = textAreaCollaborationDescription.getText();
        LocalDate collaborationStartDate = datePickerStartDate.getValue();
        LocalDate collaborationFinishDate = datePickerFinishDate.getValue();
        String collaborationGoal = textFieldCollaborationGoal.getText();
        String collaborationSubject = comboBoxSubject.getEditor().getText();
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
        collaborationUpdated.setCollaborationStatus("Pendiente");
        CollaborationDAO updateCollaborationDAO = new CollaborationDAO();
        CollaborationValidator.validateCollaborationFields(collaborationUpdated, updateCollaborationDAO);
    }

    @FXML
    private Label labelUser;

    @FXML
    void initialize(int collaborationId){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
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


        datePickerStartDate.getEditor().addEventFilter(KeyEvent.KEY_TYPED, event -> event.consume());
        datePickerStartDate.getEditor().addEventFilter(KeyEvent.KEY_PRESSED, event -> event.consume());
        datePickerStartDate.getEditor().addEventFilter(KeyEvent.KEY_RELEASED, event -> event.consume());

        datePickerFinishDate.getEditor().addEventFilter(KeyEvent.KEY_TYPED, event -> event.consume());
        datePickerFinishDate.getEditor().addEventFilter(KeyEvent.KEY_PRESSED, event -> event.consume());
        datePickerFinishDate.getEditor().addEventFilter(KeyEvent.KEY_RELEASED, event -> event.consume());

        textFieldNumberStudents.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[0-9]*")) {
                return change;
            } else {
                return null;
            }
        }));

        CharLimitValidator.setCharLimitTextField(textFieldCollaborationName, 245);
        CharLimitValidator.setCharLimitTextField(textFieldNumberStudents, 3);
        CharLimitValidator.setCharLimitComboBox(comboBoxSubject, 245);
        
    }
}
