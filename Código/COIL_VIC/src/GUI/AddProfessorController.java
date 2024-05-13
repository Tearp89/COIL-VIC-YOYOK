package GUI;

import java.util.Random;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import logic.DAO.ProfessorDAO;
import logic.DAO.UniversityDAO;
import logic.classes.Professor;
import logic.classes.University;
import logic.Access;

public class AddProfessorController {

    @FXML
    private TextField textFieldProfessorName;
    @FXML
    private TextField textFieldProfessorPhoneNumber;
    @FXML 
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldCountry;
    @FXML
    private ComboBox<String> comboBoxUniversity;
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
    private ComboBox<String> comboBoxDiscipline;
    @FXML
    private Button buttonConfirmation = new Button("Aceptar");
    @FXML
    private Button buttonCancelation = new Button("Cancelar");
    @FXML 
    private Alert professorAdded = new Alert(AlertType.NONE);



    @FXML
    void addUniversity(ActionEvent event){
        Object selectedUniversity = comboBoxUniversity.getSelectionModel().getSelectedItem();
        String universityName = selectedUniversity != null ? selectedUniversity.toString() : null;
        String country = textFieldCountry.getText();
        UniversityDAO universityDAO = new UniversityDAO();

        if (!universityDAO.isUniversityRegistered(universityName)) {
            University university = new University();
            university.setUniversityName(universityName);
            university.setUniversityCountry(country);
            universityDAO.addUniversity(university);
            }


    }
    @FXML
    void addProfessor(ActionEvent event){
        ProfessorDAO professorDAO = new ProfessorDAO();
        
        Access access = new Access();
        String professorName = textFieldProfessorName.getText();
        Object selectedUniversity = comboBoxUniversity.getSelectionModel().getSelectedItem();
        String universityName = selectedUniversity != null ? selectedUniversity.toString() : null;
        Object selectedAcademicArea = comboBoxAcademicArea.getSelectionModel().getSelectedItem();
        String academicArea = selectedAcademicArea != null ? selectedAcademicArea.toString() : null;
        String country = textFieldCountry.getText();
        String email = textFieldEmail.getText();
        String user = access.userGenerator(professorName);
        String password = access.passwordGenerator(8);

        
        UniversityDAO universityDAO = new UniversityDAO();
        int universityId = universityDAO.getUniversityId(universityName);

        Professor professor = new Professor();
        professor.setName(professorName);
        professor.setUniversityId(universityId);
        professor.setAcademicArea(academicArea);
        professor.setCountry(country);
        professor.setEmail(email);
        professor.setUser(user);
        professor.setPassword(password);
        professor.setStatus("En espera");
        if(universityName == "Universidad Veracruzana"){
            professor.setType("UV");
        }else{
            professor.setType("Externo");
        }
        int result = professorDAO.addProfessorForeign(professor);
        if(result == 1){
            professorAdded.setAlertType(AlertType.CONFIRMATION);
            professorAdded.setContentText("Profesor agregado correctamente.");
            professorAdded.show();
        }
        
    }

    EventHandler<ActionEvent> confirmationAlert = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e){
            professorAdded.setAlertType(AlertType.CONFIRMATION);
            professorAdded.show();
        }
    };
    

    @FXML
    void cancel(ActionEvent event){

    }


    @FXML
    void initialize(){
        UniversityDAO universityDAO = new UniversityDAO();
        ObservableList<String> universities = universityDAO.loadUniversities();
        comboBoxUniversity.setItems(universities);

        ObservableList<String> academicAreas = comboBoxAcademicArea.getItems();
        academicAreas.setAll("Técnica", "Humanidades", "Económico-Administrativo", "Ciencias de la salud", "Biológico-Agropecuarias", "AFGB", "DGRI");
        comboBoxAcademicArea.setItems(academicAreas);
        
        buttonConfirmation.setOnAction(confirmationAlert);

    }
}
