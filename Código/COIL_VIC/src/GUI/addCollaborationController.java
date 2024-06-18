    package GUI;


    import java.io.IOException;
    import java.time.LocalDate;
    import dataAccess.DatabaseConnectionChecker;
    import javafx.collections.ObservableList;
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
    import javafx.scene.control.TextFormatter;
    import javafx.scene.input.KeyEvent;
    import log.Log;
    import javafx.scene.control.Alert.AlertType;
    import logic.FieldValidator;
    import logic.DAO.CollaborationDAO;
    import logic.DAO.ProfessorDAO;
    import logic.classes.Collaboration;
    import logic.classes.Professor;

public class AddCollaborationController {    
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AddCollaborationController.class);
        
        @FXML
        private TextField textFieldCollaborationName;
        @FXML
        private DatePicker datePickerStartDate;
        @FXML
        private DatePicker datePickerFinishDate;
        @FXML
        private TextArea textAreaCollaborationDescription;
        @FXML
        private TextField textFieldCollaborationGoal;
        @FXML
        private ComboBox<String> comboBoxCollaborationSubject;
        @FXML 
        private TextArea textAreaStudentProfile;
        @FXML
        private TextField textFieldNoStudents;
        //TODO: IMPLEMENTAR EN BASE Y DAO
        @FXML 
        private ComboBox comboBoxCollaborationType;


    @FXML
    void addCollaboration(ActionEvent event) {
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        CollaborationDAO instance = new CollaborationDAO();
        Collaboration collaboration = new Collaboration();
        String collaborationName = textFieldCollaborationName.getText();
        String collaborationDescription = textAreaCollaborationDescription.getText();
        LocalDate startDate = datePickerStartDate.getValue();
        LocalDate finishDate = datePickerFinishDate.getValue();
        String collaborationGoal = textFieldCollaborationGoal.getText();
        String collaborationSubject = comboBoxCollaborationSubject.getEditor().getText();
        String studentProfile = textAreaStudentProfile.getText();
        String noStudentsText = textFieldNoStudents.getText();
            
        if(!FieldValidator.onlyText(collaborationName) ||
        !FieldValidator.onlyText(collaborationDescription) ||
        !FieldValidator.isValidDateRange(startDate, finishDate) ||
        !FieldValidator.onlyText(collaborationGoal) ||
        !FieldValidator.onlyText(collaborationSubject) ||
        !FieldValidator.onlyText(studentProfile) ||
        !FieldValidator.onlyNumber(noStudentsText)){
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos vacíos o incorrectos");
            emptyFieldsAlert.setHeaderText("Campos vacíos o incorrectos");
            emptyFieldsAlert.setContentText("No se puede agregar la colaboración, hay campos vacíos o incorrectos");
            emptyFieldsAlert.show();
        } else if(!instance.validateCollaborationName(collaborationName)){
            int noStudents = Integer.parseInt(noStudentsText);
            
            collaboration.setCollaborationName(collaborationName);
            collaboration.setDescription(collaborationDescription);
            collaboration.setStartDate(startDate);
            collaboration.setFinishDate(finishDate);
            collaboration.setCollaborationStatus("Pendiente");
            collaboration.setCollaborationGoal(collaborationGoal);
            collaboration.setSubject(collaborationSubject);
            collaboration.setNoStudents(noStudents);
            collaboration.setStudentProfile(studentProfile);
            int result = instance.addCollaboration(collaboration);
            if(result == 1){
                Alert collaborationAddedAlert = new Alert(AlertType.INFORMATION);
                collaborationAddedAlert.setTitle("Colaboración enviada");
                collaborationAddedAlert.setHeaderText("colaboración enviada");
                collaborationAddedAlert.setContentText("Colaboración enviada exitosamente");
                collaborationAddedAlert.show();
                ButtonType accept = new ButtonType("Aceptar");
                collaborationAddedAlert.getButtonTypes().setAll(accept);
                Button okButton = (Button) collaborationAddedAlert.getDialogPane().lookupButton(accept);
                okButton.setOnAction(eventAssignProfessor -> {
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    Professor professorData = UserSessionManager.getInstance().getProfessorUserData();
                    int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
                    int collaborationId = instance.getCollaborationIdbyName(collaborationName);
                        instance.assignProfessorToCollaboration(professorId, collaborationId);
                    
                });
                

                
            } else{
                Alert sendCollaborationErrorAlert = new Alert(AlertType.ERROR);
                sendCollaborationErrorAlert.setTitle("Error conexión");
                sendCollaborationErrorAlert.setHeaderText("Error conexión");
                sendCollaborationErrorAlert.setContentText("No se pudo conectar a la base de datos, inténtelo de nuevo más tarde");
                sendCollaborationErrorAlert.show();

            }
        } else {
                Alert duplicateNameAlert = new Alert(AlertType.ERROR);
                duplicateNameAlert.setTitle("Nombre duplicado");
                duplicateNameAlert.setHeaderText("Nombre duplicado");
                duplicateNameAlert.setContentText("No se puede agregar la colaboración el nombre no se encuentra disponible");
                duplicateNameAlert.show();
        }
            
            
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
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorSettingsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, settingsLoader);


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
    private Button buttonCancel;

    @FXML
    private void cancel(ActionEvent  event){
        FXMLLoader collaborationsSectionLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsSectionLoader);
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
    void initialize() {
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ObservableList<String> subjects = collaborationDAO.loadSubjects();
        comboBoxCollaborationSubject.getItems().setAll(subjects);

        textFieldNoStudents.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("[0-9]*")) {
                return change;
            } else {
                return null;
            }
        }));

        datePickerStartDate.getEditor().addEventFilter(KeyEvent.KEY_TYPED, event -> event.consume());
        datePickerStartDate.getEditor().addEventFilter(KeyEvent.KEY_PRESSED, event -> event.consume());
        datePickerStartDate.getEditor().addEventFilter(KeyEvent.KEY_RELEASED, event -> event.consume());

        datePickerFinishDate.getEditor().addEventFilter(KeyEvent.KEY_TYPED, event -> event.consume());
        datePickerFinishDate.getEditor().addEventFilter(KeyEvent.KEY_PRESSED, event -> event.consume());
        datePickerFinishDate.getEditor().addEventFilter(KeyEvent.KEY_RELEASED, event -> event.consume());

        

    }

    
}

