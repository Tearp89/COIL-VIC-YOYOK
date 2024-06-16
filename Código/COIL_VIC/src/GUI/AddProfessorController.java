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
import logic.EmailControl;
import logic.FieldValidator;

public class AddProfessorController {
private static final org.apache.log4j.Logger LOG = Log.getLogger(AddProfessorController.class);


    @FXML
    private TextField textFieldProfessorName;
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
        ProfessorDAO professorDAO = new ProfessorDAO();
        
        Access access = new Access();
        String professorName = textFieldProfessorName.getText();
        String professorPhoneNumber = textFieldProfessorPhoneNumber.getText();
        String email = textFieldEmail.getText();
        Object selectedCountry = comboBoxCountry.getSelectionModel().getSelectedItem();
        String country = selectedCountry != null ? selectedCountry.toString() : null;
        Object selectedUniversity = comboBoxUniversity.getSelectionModel().getSelectedItem();
        String universityName = selectedUniversity != null ? selectedUniversity.toString() : null;
        Object selectedLanguage = comboBoxLanguage.getSelectionModel().getSelectedItem();
        String language = selectedLanguage != null ? selectedLanguage.toString() : null;
        Object selectedWorkShop = comboBoxWorkShop.getSelectionModel().getSelectedItem();
        String workShop = selectedWorkShop != null ? selectedWorkShop.toString() : null;
        String user = access.userGenerator(professorName);
        String password = access.passwordGenerator(8);

        Object selectedAcademicArea = comboBoxAcademicArea.getSelectionModel().getSelectedItem();
        String academicArea = selectedAcademicArea != null ? selectedAcademicArea.toString() : null;
        
        Object selectedRegion = comboBoxRegion.getSelectionModel().getSelectedItem();
        String region = selectedRegion != null ? selectedRegion.toString() :null;
        Object selectedContractType = comboBoxContractType.getSelectionModel().getSelectedItem();
        String contractType = selectedContractType != null ? selectedContractType.toString() :null;
        Object selectedContractCategory = comboBoxContractCategory.getSelectionModel().getSelectedItem();
        String contractCategory = selectedContractCategory != null ? selectedContractCategory.toString() :null;

        University university = new University();
        Professor professor = new Professor();
        
        if(universityName.equals("Universidad Veracruzana")){
            if (!FieldValidator.isValidName(professorName)|| !FieldValidator.onlyTextAndNumbers(professorPhoneNumber) || !FieldValidator.isEmail(email) || !FieldValidator.onlyText(country) || !FieldValidator.onlyText(universityName) || !FieldValidator.onlyText(language) || !FieldValidator.onlyText(workShop) || academicArea.trim().isBlank() || !FieldValidator.onlyNumber(textFieldPersonalNumber.getText()) || region.trim().isBlank() || !FieldValidator.onlyText(contractType) || !FieldValidator.onlyText(contractCategory) ) {
                Alert emptyFieldsAlertUV = new Alert(AlertType.ERROR);
                emptyFieldsAlertUV.setTitle("Campos incorrectos o vacíos");
                emptyFieldsAlertUV.setHeaderText("Campos incorrectos o vacíos");
                emptyFieldsAlertUV.setContentText("Hay campos vacíos y/o incorrectos.");
                emptyFieldsAlertUV.showAndWait();
                return;
            } else if (professorDAO.isProfessorRegistered(email) == true){
                Alert professorDuplicatedAlert = new Alert(AlertType.INFORMATION);
                professorDuplicatedAlert.setTitle("Profesor duplicado");
                professorDuplicatedAlert.setHeaderText("Profesor duplicado");
                professorDuplicatedAlert.setContentText("Este correo ya está asociado a una cuenta");
                professorDuplicatedAlert.show();
            
            }else{
                UniversityDAO universityDAO = new UniversityDAO();
                addUniversity(universityName, country, language);
                int universityId = universityDAO.getUniversityId(universityName);
                int personalNumber = Integer.parseInt(textFieldPersonalNumber.getText());
                professor.setName(professorName);
                professor.setPhoneNumber(professorPhoneNumber);
                professor.setEmail(email);
                professor.setCountry(country);
                professor.setUniversityId(universityId);
                university.setUniversityLanguage(language);
                professor.setWorkShop(workShop);
                professor.setUser(user);
                professor.setPassword(password);
                professor.setStatus("Pendiente");
                professor.setType("UV");
                professor.setAcademicArea(academicArea);
                professor.setPersonalNumber(personalNumber);
                professor.setRegion(region);
                professor.setContractType(contractType);
                professor.setContractCategory(contractCategory);

                Alert professorAddedAlert = new Alert(AlertType.CONFIRMATION);
                professorAddedAlert.setTitle("Confirmación de registro");
                professorAddedAlert.setHeaderText("Confirmación de registro");
                professorAddedAlert.setContentText("¿Deseas enviar la solicitud de registro?");
                ButtonType accept = new ButtonType("Aceptar");
                ButtonType cancel = new ButtonType("Cancelar");
                professorAddedAlert.getButtonTypes().setAll(cancel, accept);
                Button okButton = (Button) professorAddedAlert.getDialogPane().lookupButton(accept);
                Button cancelButton = (Button) professorAddedAlert.getDialogPane().lookupButton(cancel);
                            
                okButton.setOnAction(eventAddProfessorUV -> {            
                    int professorRequestResult = professorDAO.addProfessorUV(professor);
                    if(professorRequestResult == 1) {
                        EmailControl emailControl = new EmailControl();
                        try {
                            emailControl.sendEmail(email, "Datos de entrada al sistema", "Sus credenciales para el sistema son: \n-Usuario: " + user + "\n-Contraseña: " + password + "\n\nUna vez sea aceptado en el sistema podra ingresar.");
                            Alert registrationRequestAlert = new Alert(AlertType.INFORMATION);
                            registrationRequestAlert.setTitle("Solicitud de registro");
                            registrationRequestAlert.setHeaderText("Solicitud enviada");
                            registrationRequestAlert.setContentText("Solicitud de registro enviada exitosamente. \nSus credenciales se han enviado por correo");
                            registrationRequestAlert.showAndWait();
                            LOG.info("Se envió un correo con las credenciales a: " + email);

                            FXMLLoader addProfessorLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
                            try {
                                Parent root = addProfessorLoader.load();
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
                    
                        } catch (MessagingException e) {
                            Alert errorSendingOfEmail = new Alert(AlertType.ERROR);
                            errorSendingOfEmail.setTitle("Error en el envío del correo");
                            errorSendingOfEmail.setHeaderText(null);
                            errorSendingOfEmail.setContentText("Sus credenciales son las siguientes: \n-Usuario:" + user + "\n-Contraseña: " + password);
                            errorSendingOfEmail.show();
                            LOG.error(e);
                        }
                    } else {
                        Alert registrationRequestFailedAlert = new Alert(AlertType.ERROR);
                        registrationRequestFailedAlert.setTitle("Error en la solicitud");
                        registrationRequestFailedAlert.setHeaderText("Error al enviar la solicitud");
                        registrationRequestFailedAlert.setContentText("Hubo un problema al enviar la solicitud de registro. Por favor, intente nuevamente.");
                        registrationRequestFailedAlert.show();
                    }
                    
                });
                cancelButton.setOnAction(eventCancelAdding -> {
                    professorAddedAlert.close();
                });
                    
                professorAddedAlert.show();    
            } 
                    
        }else{
            if (!FieldValidator.isValidName(professorName) || !FieldValidator.onlyTextAndNumbers(professorPhoneNumber) || !FieldValidator.isEmail(email) || !FieldValidator.onlyText(country) || !FieldValidator.onlyText(universityName) || !FieldValidator.onlyText(language) || !FieldValidator.onlyText(workShop)) {
                Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
                emptyFieldsAlert.setTitle("Campos incorrectos o vacíos");
                emptyFieldsAlert.setHeaderText("Campos incorectos o vacíos");
                emptyFieldsAlert.setContentText("Hay campos vacíos y/o incorrectos.");
                emptyFieldsAlert.showAndWait();
                return;
            }else if (professorDAO.isProfessorRegistered(email) == true){
                Alert professorDuplicatedAlert = new Alert(AlertType.INFORMATION);
                professorDuplicatedAlert.setTitle("Profesor duplicado");
                professorDuplicatedAlert.setHeaderText("Profesor duplicado");
                professorDuplicatedAlert.setContentText("Este correo ya está asociado a una cuenta");
                professorDuplicatedAlert.show();
            }else{
                UniversityDAO universityDAO = new UniversityDAO();
                addUniversity(universityName, country, language);
                Integer universityIdInteger = universityDAO.getUniversityId(universityName);
                professor.setName(professorName);
                professor.setPhoneNumber(professorPhoneNumber);
                professor.setEmail(email);
                professor.setCountry(country);
                professor.setUniversityId(universityIdInteger);
                professor.setWorkShop(workShop);
                professor.setUser(user);
                professor.setPassword(password);
                professor.setStatus("Pendiente");
                professor.setType("Externo");
                    
                Alert professorAddedAlert = new Alert(AlertType.CONFIRMATION);
                professorAddedAlert.setTitle("Confirmación de registro");
                professorAddedAlert.setHeaderText("Confirmación de registro");
                professorAddedAlert.setContentText("¿Deseas enviar la solicitud de registro?");
                ButtonType accept = new ButtonType("Aceptar");
                ButtonType cancel = new ButtonType("Cancelar");
                professorAddedAlert.getButtonTypes().setAll(cancel, accept);
                professorAddedAlert.show();
                Button okButton = (Button) professorAddedAlert.getDialogPane().lookupButton(accept); 
                Button cancelButton = (Button) professorAddedAlert.getDialogPane().lookupButton(cancel);   
                            
                okButton.setOnAction(eventAddProfessorForeign -> {                              
                    int professorRequestResult = professorDAO.addProfessorForeign(professor);
                    if(professorRequestResult == 1) {
                        EmailControl emailControl = new EmailControl();
                        try {
                            emailControl.sendEmail(email, "Datos de entrada al sistema", "Sus credenciales para el sistema son: \n-Usuario: " + user + "\n-Contraseña: " + password + "\n\nUna vez sea aceptado en el sistema podra ingresar.");
                            Alert registrationRequestAlert = new Alert(AlertType.INFORMATION);
                            registrationRequestAlert.setTitle("Solicitud de registro");
                            registrationRequestAlert.setHeaderText("Solicitud enviada");
                            registrationRequestAlert.setContentText("Solicitud de registro enviada exitosamente. \nSus credenciales se han enviado por correo. \nEl correo puede tardar unos minutos en llegar.");
                            registrationRequestAlert.showAndWait();
                            LOG.info("Se envió un correo con las credenciales a: " + email);
                            FXMLLoader addProfessorLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
                            try {
                                Parent root = addProfessorLoader.load();
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

                        } catch (MessagingException e) {
                            Alert errorSendingOfEmail = new Alert(AlertType.ERROR);
                            errorSendingOfEmail.setTitle("Error en el envío del correo");
                            errorSendingOfEmail.setHeaderText(null);
                            errorSendingOfEmail.setContentText("Sus credenciales son las siguientes: \n-Usuario: " + user + "\n-Contraseña: " + password + "\n\nAsegurese de no perderlas");
                            errorSendingOfEmail.show();
                            LOG.error(e);
                        }
                    } else {
                        Alert registrationRequestFailedAlert = new Alert(AlertType.ERROR);
                        registrationRequestFailedAlert.setTitle("Error en la solicitud");
                        registrationRequestFailedAlert.setHeaderText("Error al enviar la solicitud");
                        registrationRequestFailedAlert.setContentText("Hubo un problema al enviar la solicitud de registro. Por favor, intente nuevamente.");
                        registrationRequestFailedAlert.show();
                    }   
                });
                
                cancelButton.setOnAction(eventCancelAdding -> {
                    professorAddedAlert.close();
                });
            }
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
            FXMLLoader addProfessorLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
            try {
            Parent root = addProfessorLoader.load();
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
        UniversityDAO universityDAO = new UniversityDAO();
        ObservableList<String> universities = universityDAO.loadUniversities();
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            buttonConfirmation.setDisable(true);
        } else {
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
    }
        
}

