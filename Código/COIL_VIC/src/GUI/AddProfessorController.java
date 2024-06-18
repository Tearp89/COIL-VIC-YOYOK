package GUI;

import java.io.IOException;

import javax.mail.MessagingException;

import dataAccess.DatabaseConnectionChecker;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import log.Log;
import logic.DAO.ProfessorDAO;
import logic.DAO.UniversityDAO;
import logic.classes.Professor;
import logic.classes.University;
import logic.Access;
import logic.CharLimitValidator;
import logic.EmailControl;
import logic.FieldValidator;
import logic.ProfessorValidator;

public class AddProfessorController {
private static final org.apache.log4j.Logger LOG = Log.getLogger(AddProfessorController.class);


    @FXML
    private TextField textFieldProfessorName;
    @FXML 
    private TextField textFieldProfessorLastName;
    @FXML
    private TextField textFieldProfessorPhoneNumber;
    @FXML 
    private TextField textFieldEmail;
    @FXML
    private ComboBox<String> comboBoxCountry;
    @FXML
    private ComboBox<String> comboBoxUniversity;
    @FXML
    private ComboBox<String> comboBoxLanguage;
    @FXML
    private ComboBox<String> comboBoxWorkShop;
    @FXML
    private ComboBox<String> comboBoxAcademicArea;
    @FXML
    private TextField textFieldPersonalNumber;
    @FXML
    private ComboBox<String> comboBoxRegion;
    @FXML
    private ComboBox<String> comboBoxContractType;
    @FXML
    private ComboBox<String> comboBoxContractCategory;
    @FXML
    private Button buttonConfirmation = new Button("Aceptar");
    @FXML
    private Button buttonCancel = new Button("Cancelar");
    @FXML 
    private Alert professorAdded = new Alert(AlertType.NONE);
    @FXML
    private Label labelAcademicArea;
    @FXML
    private Label labelPersonalNumber;
    @FXML
    private Label labelaRegion;
    @FXML
    private Label labelContractType;
    @FXML
    private Label labelcontractCategory;





    void addUniversity(String universityName, String universityCountry, String language){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        UniversityDAO universityDAO = new UniversityDAO();
        if (!universityDAO.isUniversityRegistered(universityName) && FieldValidator.onlyText(language) && FieldValidator.onlyText(universityCountry) && FieldValidator.onlyText(universityName)) {
            University university = new University();
            university.setUniversityName(universityName);
            university.setUniversityCountry(universityCountry);
            university.setUniversityLanguage(language);
            universityDAO.addUniversity(university);
        }
    }

    @FXML
    void addProfessor(ActionEvent event){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        Access access = new Access();
        String professorName = textFieldProfessorName != null ? textFieldProfessorName.getText() : "";
        String email = textFieldEmail != null ? textFieldEmail.getText() : "";
        Object selectedUniversity = comboBoxUniversity.getSelectionModel().getSelectedItem();
        String universityName = selectedUniversity != null ? selectedUniversity.toString() : null;
        String user = access.userGenerator(professorName);
        String password = access.passwordGenerator(8);  
        
        if (universityName == null){
            showAlert(AlertType.ERROR, "Campos inválidos", "No se ha ingresado una universidad");
            return;
        } 
        Professor professor;
        if(universityName.equals("Universidad Veracruzana")){
            validatePersonalNumber();
            professor = assignDataToUvProfessor(user, password);
            handleProfessorRegistration(event, professor, email, user, password, true);
        } else {
            professor = assignDataToForeignProfessor(user, password);
            handleProfessorRegistration(event, professor, email, user, password, false);
        }
            
    }
        
    private void loadAcademicAreaData(){
        comboBoxAcademicArea.getItems().clear();
        ProfessorDAO professorDAOInstance = new ProfessorDAO();
        comboBoxAcademicArea.setItems(professorDAOInstance.loadProfessorsAcademicArea());
    }

    private void loadRegionData(){
        comboBoxRegion.getItems().clear();
        ProfessorDAO professorDAOInstance = new ProfessorDAO();
        comboBoxRegion.setItems(professorDAOInstance.loadProfessorsRegion());
    }

    private void loadContractTypeData(){
        comboBoxContractType.getItems().clear();
        ProfessorDAO professorDAOInstance = new ProfessorDAO();
        comboBoxContractType.setItems(professorDAOInstance.loadProfessorsContractType());
    }

    private void loadContractCategoryData(){
        comboBoxContractCategory.getItems().clear();
        ProfessorDAO professorDAOInstance = new ProfessorDAO();
        comboBoxContractCategory.setItems(professorDAOInstance.loadProfessorsContractCategory());
    }
    private void loadCountry(){
        comboBoxCountry.getItems().clear();
        ProfessorDAO professorDAOInstance = new ProfessorDAO();
        comboBoxCountry.setItems(professorDAOInstance.loadProfessorsCountry());
    }
    private void loadLanguage(){
        comboBoxLanguage.getItems().clear();
        ProfessorDAO professorDAOInstance = new ProfessorDAO();
        comboBoxLanguage.setItems(professorDAOInstance.loadProfessorsLanguage());
    }

    EventHandler<ActionEvent> confirmationAlert = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e){
            professorAdded.setAlertType(AlertType.CONFIRMATION);
            professorAdded.show();
        }
    };
    
    @FXML
    private void cancel(ActionEvent event){
        Alert confirmCancelationAlert = new Alert(AlertType.CONFIRMATION);
        confirmCancelationAlert.setTitle("Confirmar salida");
        confirmCancelationAlert.setHeaderText("Confirmar salida");
        confirmCancelationAlert.setContentText("¿Está seguro de que desea salir? Se perderán los datos guardados");
        confirmCancelationAlert.show();

        ButtonType confirm = new ButtonType("Aceptar" );
        ButtonType cancel = new ButtonType("Cancelar");
        confirmCancelationAlert.getButtonTypes().setAll(confirm, cancel);

        Button okButton = (Button) confirmCancelationAlert.getDialogPane().lookupButton(confirm);
        Button cancelButon = (Button) confirmCancelationAlert.getDialogPane().lookupButton(cancel);

        okButton.setOnAction(eventConfirmCanel ->{
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
            try {
            Parent root = loginLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Node source = (Node) event.getSource();
            Stage currenStage = (Stage) source.getScene().getWindow();
            currenStage.close();
        } catch (IOException goToHomeException){
            LOG.error("ERROR:", goToHomeException);
        }
        });
        cancelButon.setOnAction(eventCancelCancel ->{
            confirmCancelationAlert.close();
        });

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
    void initialize(){
        
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            buttonConfirmation.setDisable(true);
        } else {
            UniversityDAO universityDAO = new UniversityDAO();
            ObservableList<String> universities = universityDAO.loadUniversities();
            loadAcademicAreaData();
            loadRegionData();
            loadContractTypeData();
            loadContractCategoryData();
            loadCountry();
            loadLanguage();

            ObservableList<String> workShop = comboBoxWorkShop.getItems();
            workShop.setAll("Sí", "No");
            comboBoxWorkShop.setItems(workShop);

            comboBoxUniversity.setItems(universities);
            

            comboBoxUniversity.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                
                if ("Universidad Veracruzana".equals(newValue)) {
                    
                    comboBoxAcademicArea.setVisible(true);
                    loadAcademicAreaData();
                    textFieldPersonalNumber.setVisible(true);
                    comboBoxRegion.setVisible(true);
                    loadRegionData();
                    comboBoxContractType.setVisible(true);
                    loadContractTypeData();
                    comboBoxContractCategory.setVisible(true);
                    loadContractCategoryData();
                    labelAcademicArea.setVisible(true);
                    labelPersonalNumber.setVisible(true);
                    labelaRegion.setVisible(true);
                    labelContractType.setVisible(true);
                    labelcontractCategory.setVisible(true);

                } else {
                    comboBoxAcademicArea.setVisible(false);
                    textFieldPersonalNumber.setVisible(false);
                    comboBoxRegion.setVisible(false);
                    comboBoxContractType.setVisible(false);
                    comboBoxContractCategory.setVisible(false);
                    labelAcademicArea.setVisible(false);
                    labelPersonalNumber.setVisible(false);
                    labelaRegion.setVisible(false);
                    labelContractType.setVisible(false);
                    labelcontractCategory.setVisible(false);
                }
            });

            
            buttonConfirmation.setOnAction(confirmationAlert);
            buttonConfirmation.setOnAction(this::addProfessor); 
            buttonCancel.setOnAction(this::cancel);

            textFieldPersonalNumber.setTextFormatter(new TextFormatter<>(change -> {
                String newText = change.getControlNewText();
                if (newText.matches("[0-9]*")) {
                    return change;
                } else {
                    return null;
                }
            }));
        }

        CharLimitValidator.setCharLimitTextField(textFieldPersonalNumber, 9);
        CharLimitValidator.setCharLimitTextField(textFieldEmail, 255);
        CharLimitValidator.setCharLimitTextField(textFieldProfessorLastName, 125);
        CharLimitValidator.setCharLimitTextField(textFieldProfessorName, 125);
        CharLimitValidator.setCharLimitTextField(textFieldProfessorPhoneNumber, 13);
        CharLimitValidator.setCharLimitComboBox(comboBoxLanguage, 45);
        CharLimitValidator.setCharLimitComboBox(comboBoxUniversity, 245);
    }

    public Professor assignDataToForeignProfessor(String user, String password){
        Professor foreignProfessor = new Professor();
        String professorName = textFieldProfessorName != null ? textFieldProfessorName.getText() : "";
        String professorLastName = textFieldProfessorLastName != null ? textFieldProfessorLastName.getText() : "";
        String professorFullName = "" + professorName + " " + professorLastName;
        String professorPhoneNumber = textFieldProfessorPhoneNumber != null ? textFieldProfessorPhoneNumber.getText() : "";
        String email = textFieldEmail != null ? textFieldEmail.getText() : "";

        Object selectedCountry = comboBoxCountry.getSelectionModel().getSelectedItem();
        String country = selectedCountry != null ? selectedCountry.toString() : null;
        Object selectedUniversity = comboBoxUniversity.getSelectionModel().getSelectedItem();
        String universityName = selectedUniversity != null ? selectedUniversity.toString() : null;
        Object selectedLanguage = comboBoxLanguage.getSelectionModel().getSelectedItem();
        String language = selectedLanguage != null ? selectedLanguage.toString() : null;
        Object selectedWorkShop = comboBoxWorkShop.getSelectionModel().getSelectedItem();
        String workShop = selectedWorkShop != null ? selectedWorkShop.toString() : null;
        UniversityDAO universityDAO = new UniversityDAO();
        addUniversity(universityName, country, language);
        Integer universityIdInteger = universityDAO.getUniversityId(universityName);
        foreignProfessor.setName(professorFullName);
        foreignProfessor.setPhoneNumber(professorPhoneNumber);
        foreignProfessor.setEmail(email);
        foreignProfessor.setCountry(country);
        foreignProfessor.setUniversityId(universityIdInteger);
        foreignProfessor.setUniversityName(universityName);
        foreignProfessor.setWorkShop(workShop);
        foreignProfessor.setUser(user);
        foreignProfessor.setPassword(password);
        foreignProfessor.setStatus("Pendiente");
        foreignProfessor.setType("Externo");
        foreignProfessor.setLanguage(language);
        return foreignProfessor;
    }
    
    public Professor assignDataToUvProfessor(String user, String password){
        Professor uvProfessor = new Professor();
        
        String professorName = textFieldProfessorName != null ? textFieldProfessorName.getText() : "";
        String professorLastName = textFieldProfessorLastName != null ? textFieldProfessorLastName.getText() : "";
        String professorFullName = "" + professorName + " " + professorLastName;
        String professorPhoneNumber = textFieldProfessorPhoneNumber != null ? textFieldProfessorPhoneNumber.getText() : "";
        String email = textFieldEmail != null ? textFieldEmail.getText() : "";

        Object selectedCountry = comboBoxCountry.getSelectionModel().getSelectedItem();
        String country = selectedCountry != null ? selectedCountry.toString() : null;
        Object selectedUniversity = comboBoxUniversity.getSelectionModel().getSelectedItem();
        String universityName = selectedUniversity != null ? selectedUniversity.toString() : null;
        Object selectedLanguage = comboBoxLanguage.getSelectionModel().getSelectedItem();
        String language = selectedLanguage != null ? selectedLanguage.toString() : null;
        Object selectedWorkShop = comboBoxWorkShop.getSelectionModel().getSelectedItem();
        String workShop = selectedWorkShop != null ? selectedWorkShop.toString() : null;

        Object selectedAcademicArea = comboBoxAcademicArea.getSelectionModel().getSelectedItem();
        String academicArea = selectedAcademicArea != null ? selectedAcademicArea.toString() : null;

        Object selectedRegion = comboBoxRegion.getSelectionModel().getSelectedItem();
        String region = selectedRegion != null ? selectedRegion.toString() : null;
        Object selectedContractType = comboBoxContractType.getSelectionModel().getSelectedItem();
        String contractType = selectedContractType != null ? selectedContractType.toString() : null;
        Object selectedContractCategory = comboBoxContractCategory.getSelectionModel().getSelectedItem();
        String contractCategory = selectedContractCategory != null ? selectedContractCategory.toString() : null;
        UniversityDAO universityDAO = new UniversityDAO();
        int personalNumber = Integer.parseInt(textFieldPersonalNumber.getText());
        University university = new University();
        addUniversity(universityName, country, language); 
        int universityId = universityDAO.getUniversityId(universityName);
        uvProfessor.setName(professorFullName);
        uvProfessor.setPhoneNumber(professorPhoneNumber);
        uvProfessor.setEmail(email);
        uvProfessor.setCountry(country);
        uvProfessor.setUniversityName(universityName);
        uvProfessor.setUniversityId(universityId);
        university.setUniversityLanguage(language);
        uvProfessor.setWorkShop(workShop);
        uvProfessor.setUser(user);
        uvProfessor.setPassword(password);
        uvProfessor.setStatus("Pendiente");
        uvProfessor.setType("UV");
        uvProfessor.setAcademicArea(academicArea);
        uvProfessor.setPersonalNumber(personalNumber);
        uvProfessor.setRegion(region);
        uvProfessor.setContractType(contractType);
        uvProfessor.setContractCategory(contractCategory);
        uvProfessor.setLanguage(language);
        return uvProfessor;
    }

    private void validatePersonalNumber(){
        if(textFieldPersonalNumber.getText() == null || !FieldValidator.onlyNumber(textFieldPersonalNumber.getText())){
            Alert invalidPersonalNumberAlert = new Alert(AlertType.ERROR);
            invalidPersonalNumberAlert.setTitle("Campos inválidos");
            invalidPersonalNumberAlert.setHeaderText("Campos inválidos");
            invalidPersonalNumberAlert.setContentText("No se puede agregar al profesor hay campos vacíos o inválidos");
            invalidPersonalNumberAlert.showAndWait();
            return;
        }
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void sendEmailAndShowAlert(String email, String user, String password) {
        EmailControl emailControl = new EmailControl();
        try {
            emailControl.sendEmail(email, "Datos de entrada al sistema", "Sus credenciales para el sistema son: \n-Usuario: " + user + "\n-Contraseña: " + password + "\n\nUna vez sea aceptado en el sistema podrá ingresar.");
            showAlert(AlertType.INFORMATION, "Solicitud de registro", "Solicitud de registro enviada exitosamente. Sus credenciales se han enviado por correo.");
            LOG.info("Se envió un correo con las credenciales a: " + email);
        } catch (MessagingException e) {
            showAlert(AlertType.ERROR, "Error en el envío del correo", "Sus credenciales son las siguientes: \n-Usuario: " + user + "\n-Contraseña: " + password);
            LOG.error(e);
        }
    }

    private void registerProfessor(ActionEvent event, Professor professor, String email, String user, String password, boolean isUV) {
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorRequestResult = isUV ? professorDAO.addProfessorUV(professor) : professorDAO.addProfessorForeign(professor);

        if (professorRequestResult == 1) {
            sendEmailAndShowAlert(email, user, password);
            redirectToLogin(event);
        } else {
            showAlert(AlertType.ERROR, "Error en la solicitud", "Hubo un problema al enviar la solicitud de registro. Por favor, intente nuevamente.");
        }
    }

    private boolean isValidProfessor(Professor professor, boolean isUV) {
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return false;
        }
        if (isUV) {
            return ProfessorValidator.validateUvProfessorFields(professor);
        } else {
            return ProfessorValidator.validateForeignProfessorFields(professor);
        }
    }

    private void handleProfessorRegistration(ActionEvent event, Professor professor, String email, String user, String password, boolean isUV) {
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        if (!isValidProfessor(professor, isUV)) {
            return;
        }

        Alert professorAddedAlert = createConfirmationAlert("Confirmación de registro", "¿Deseas enviar la solicitud de registro?");
        professorAddedAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                registerProfessor(event, professor, email, user, password, isUV);
            }
        });
    }

    private Alert createConfirmationAlert(String title, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        return alert;
    }

    private void redirectToLogin(ActionEvent event) {
        try {
            FXMLLoader addProfessorLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
            Parent root = addProfessorLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            LOG.error("ERROR:", e);
        }
    }
}

