package GUI;

import java.util.Random;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.DAO.UniversityDAO;
import logic.classes.Professor;
import logic.classes.University;
import logic.Access;
import logic.FieldValidator;

public class AddProfessorController {


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
        if (!universityDAO.isUniversityRegistered(universityName)) {
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
                if (professorName.isEmpty() || professorPhoneNumber.isEmpty() || email.isEmpty() || country.isEmpty() || universityName.isEmpty() || language.isEmpty() || workShop.isEmpty() || academicArea.isEmpty() || textFieldPersonalNumber.getText().isEmpty() || region.isEmpty() || contractType.isEmpty() || contractCategory.isEmpty()) {
                    Alert emptyFieldsAlertUV = new Alert(AlertType.ERROR);
                    emptyFieldsAlertUV.setTitle("Campos vacíos");
                    emptyFieldsAlertUV.setHeaderText("Campos vacíos");
                    emptyFieldsAlertUV.setContentText("Por favor, complete todos los campos.");
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
                    professorAddedAlert.getButtonTypes().setAll(accept);
                    Button okButton = (Button) professorAddedAlert.getDialogPane().lookupButton(accept);
                            
                    okButton.setOnAction(eventAddProfessorUV -> {
                                
                    int professorRequestResult = professorDAO.addProfessorUV(professor);
                    if(professorRequestResult == 1) {
                        Alert registrationRequestAlert = new Alert(AlertType.INFORMATION);
                        registrationRequestAlert.setTitle("Solicitud de registro");
                        registrationRequestAlert.setHeaderText("Solicitud enviada");
                        registrationRequestAlert.setContentText("Solicitud de registro enviada exitosamente.");
                        registrationRequestAlert.show();
                    } else {
                        Alert registrationRequestFailedAlert = new Alert(AlertType.ERROR);
                        registrationRequestFailedAlert.setTitle("Error en la solicitud");
                        registrationRequestFailedAlert.setHeaderText("Error al enviar la solicitud");
                        registrationRequestFailedAlert.setContentText("Hubo un problema al enviar la solicitud de registro. Por favor, intente nuevamente.");
                        registrationRequestFailedAlert.show();
                    }
                });
                    
                professorAddedAlert.show();    
            } 
                    
            }else{
                if (FieldValidator.onlyTextAndNumbers(professorName) || FieldValidator.onlyTextAndNumbers(professorPhoneNumber) || FieldValidator.isEmail(email) || FieldValidator.onlyTextAndNumbers(country) || FieldValidator.onlyTextAndNumbers(universityName) || FieldValidator.onlyTextAndNumbers(language) || FieldValidator.onlyTextAndNumbers(workShop)) {
                    Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
                    emptyFieldsAlert.setTitle("Campos vacíos");
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
                    professorAddedAlert.getButtonTypes().setAll(accept);
                    Button okButton = (Button) professorAddedAlert.getDialogPane().lookupButton(accept);    
                            
                    okButton.setOnAction(eventAddProfessorForeign -> {                              
                        int professorRequestResult = professorDAO.addProfessorForeign(professor);
                        if(professorRequestResult == 1) {
                            Alert registrationRequestAlert = new Alert(AlertType.INFORMATION);
                            registrationRequestAlert.setTitle("Solicitud de registro");
                            registrationRequestAlert.setHeaderText("Solicitud enviada");
                            registrationRequestAlert.setContentText("Solicitud de registro enviada exitosamente.");
                            registrationRequestAlert.show();
                        } else {
                            Alert registrationRequestFailedAlert = new Alert(AlertType.ERROR);
                            registrationRequestFailedAlert.setTitle("Error en la solicitud");
                            registrationRequestFailedAlert.setHeaderText("Error al enviar la solicitud");
                            registrationRequestFailedAlert.setContentText("Hubo un problema al enviar la solicitud de registro. Por favor, intente nuevamente.");
                            registrationRequestFailedAlert.show();
                        }   
                    });
                    
                professorAddedAlert.show();                      
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
            FXMLLoader addProfessorLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
            ChangeWindowManager.changeWindowTo(event, addProfessorLoader);
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
        ProfessorDAO professorDAO = new ProfessorDAO();
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

    }
}

