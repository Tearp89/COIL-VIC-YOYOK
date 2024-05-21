package GUI;

import java.io.IOException;
import java.util.ArrayList;

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

public class ConsultProfessorsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ConsultProfessorsController.class);

    @FXML
    private Button buttonHome;

    @FXML
    private Button buttonCollaborations;

    @FXML
    private Button buttonProfessors;

    @FXML
    private Button buttonNumeralia;

    @FXML
    private Button buttonUniversities;
    
    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonMinimize;

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonConsult;

    @FXML
    private Button buttonBack;

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
        //Admin adminData = new Admin();
        //adminData = UserSessionManager.getInstance().getAdminUserData();
        //labelName.setText(adminData.getAdminName());
        showProfessorList();
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
    public void goToHome(ActionEvent event){
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/GUI/adminHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homeLoader);
    }
    
    @FXML
    public void goToNumeralia(ActionEvent event){
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/GUI/numeralia.fxml"));
        ChangeWindowManager.changeWindowTo(event, homeLoader);
    }

    @FXML
    public void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try {
            ChangeWindowManager.logout(event, loginLoader);
            UserSessionManager.getInstance().logoutAdmin();
        } catch (IOException ioException){
            LOG.error(ioException);
        }
    }

    @FXML
    public void showProfessorList(){
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
