package GUI;

import java.io.IOException;
import java.util.ArrayList;

import dataAccess.DatabaseConnectionChecker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import log.Log;
import logic.DAO.ProfessorDAO;
import logic.DAO.UniversityDAO;
import logic.classes.Admin;
import logic.classes.Professor;

public class ProfessorConsulterController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ProfessorConsulterController.class);


    @FXML
    private Button buttonConsult;


    @FXML
    private Button buttonClearFilters;

    @FXML
    private Label labelName;

    @FXML
    private TableView<Professor> tableViewProfessor;

    @FXML
    private ComboBox<String> comboBoxFilterBy;

    @FXML
    private ComboBox<String> comboBoxConsultFilter;

    private ObservableList<Professor> professorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("Universidades","Paises");
        comboBoxFilterBy.setItems(options);
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        labelName.setText(adminData.getAdminName());
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        showProfessorList();
        comboBoxConsultFilter.setEditable(true);
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
    private Button buttonNumeralia;
    @FXML
    private void goToNumeralia(ActionEvent event){
        FXMLLoader numeraliaLoader = new FXMLLoader(getClass().getResource("/GUI/NumeraliaWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, numeraliaLoader);
    }
    @FXML
    private Button buttonLogout;
    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
        try {
            ChangeWindowManager.logout(event, loginLoader);
            UserSessionManager.getInstance().logoutAdmin();
        } catch (IOException ioException){
            LOG.error(ioException);
        }
    }

    @FXML
    private Button buttonCollaborations;
    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminUniversityOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
    }

    @FXML
    private Button buttonProfessors;
    @FXML
    private void goToProfessors(ActionEvent event){
        FXMLLoader professorOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminProfessorsOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, professorOptionsLoader);
    }

    @FXML
    private Button buttonUniversities;
    @FXML
    private void goToUniversities(ActionEvent event){
        FXMLLoader universitiesOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminUniversityOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, universitiesOptionsLoader);
    }

    @FXML
    private Button buttonHome;
    @FXML
    private void goToHomePage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/AdminHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonBack;
    @FXML
    private void goBack(ActionEvent event){
        FXMLLoader goBackLoader = new FXMLLoader(getClass().getResource("/GUI/AdminProfessorOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, goBackLoader);
    }

    @FXML
    public void showProfessorList(){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        professorList.clear();
        ArrayList<Professor> professorsData = new ArrayList<>();
        ProfessorDAO professorDAOInstance = new ProfessorDAO();
        professorsData = professorDAOInstance.searchProfessor();
        for (Professor professor : professorsData){
            professor.setUniversityName(getUniversityName(professor.getUniversityId()));
            professorList.add(professor);
        }
        tableViewProfessor.setItems(professorList);
        tableViewProfessor.requestLayout();
        Label labelProfessorsNotFound = new Label("No se encontraron profesores");
        tableViewProfessor.setPlaceholder(labelProfessorsNotFound);
    }

    @FXML
    public void clearFilter(ActionEvent event){
        showProfessorList();
        comboBoxConsultFilter.setValue(null);
        comboBoxFilterBy.setValue(null);
    }

    @FXML
    public void selectFilter(ActionEvent event){
        String selectedOption = comboBoxFilterBy.getValue();
        if(selectedOption != null && selectedOption.equals("Paises")){
            loadCountryData();
        } else if (selectedOption != null && selectedOption.equals("Universidades")){
            loadUniversityData();
        }
    }

    @FXML
    public void loadFilter(ActionEvent event){
        filterTableView(comboBoxConsultFilter.getValue());
    }

    private String getUniversityName(int universityId) {
        UniversityDAO professorDAO = new UniversityDAO();
        return professorDAO.getUniversityNameById(universityId);
    }
    
    private void loadCountryData(){
        comboBoxConsultFilter.getItems().clear();
        ProfessorDAO professorDAOInstance = new ProfessorDAO();
        comboBoxConsultFilter.setItems(professorDAOInstance.loadProfessorsCountries());
    }

    private void loadUniversityData(){
        comboBoxConsultFilter.getItems().clear();
        UniversityDAO universityDAOInstance = new UniversityDAO();
        comboBoxConsultFilter.setItems(universityDAOInstance.loadUniversities());
    }

    private void filterTableView(String filterValue) {
        ObservableList<Professor> filteredData = FXCollections.observableArrayList();
        String selectedOption = comboBoxFilterBy.getValue();
        if(selectedOption != null && selectedOption.equals("Paises")){
            for (Professor professor : professorList) {
                if (professor.getCountry().equals(filterValue)) {
                    filteredData.add(professor);
                }
            }
        } else if (selectedOption != null && selectedOption.equals("Universidades")){
            for (Professor professor : professorList) {
                if (professor.getUniversityName().equals(filterValue)) {
                    filteredData.add(professor);
                }
            }
        }
            
        tableViewProfessor.setItems(filteredData);
    }
}
