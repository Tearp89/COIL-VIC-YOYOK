package GUI;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.xmlbeans.StringEnumAbstractBase.Table;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log;
import logic.DAO.UniversityDAO;
import logic.classes.Admin;
import logic.classes.University;

public class SearchUniversityController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(SearchUniversityController.class);
    @FXML
    private TableView<University> tableViewUniversities;
    private TableColumn<University, String> tableColumnUniversityId;
    private TableColumn<University, String> tableColumnUniversityName;
    private TableColumn<University, String> tableColumnUniversityCountry;
    private TableColumn<University, String> tableColumnUnivesityLanguage;
    @FXML
    Label labelCollaborationNotFound = new Label("No se encontraron universidades");

    public void loadUniversities(){
        UniversityDAO universitiyDAO = new UniversityDAO();
        ArrayList<University> universities = new ArrayList<>();
        universities = universitiyDAO.searchUniversity();
        tableViewUniversities.getItems().addAll(universities);
        if(universities.size() == 0){
            tableViewUniversities.setPlaceholder(labelCollaborationNotFound);
        }
    }

    @FXML
    private TextField textFieldSearch;
    public void searchUniversities(ActionEvent e){
        UniversityDAO universityDAO = new UniversityDAO();
        ArrayList<University> universities = new ArrayList<>();
        String universityName = "%" + textFieldSearch.getText() + "%";
        universities = universityDAO.searchUniversityByName(universityName);
        tableViewUniversities.getItems().clear();
        tableViewUniversities.getItems().addAll(universities);
        if(universities.size() == 0){
            tableViewUniversities.setPlaceholder(labelCollaborationNotFound);
        }
    }
    @FXML
    private Button buttonCollaborations;
    @FXML
    private void goToCollaborations(ActionEvent event){

    }

    @FXML
    private Button buttonProfessors;
    @FXML
    private void goToProfessors(ActionEvent event){

    }
    @FXML
    private Button buttonLogout;
    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try {
            UserSessionManager.getInstance().logoutAdmin();
            Parent root = loginLoader.load();
            Scene loginScene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setScene(loginScene);
            loginStage.show();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException goToLoginException){
            LOG.error(goToLoginException);
        }
    }

    @FXML
    private Button buttonMinimize;
    @FXML
    private void minimizeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private Button buttonClose;
    @FXML
    private void closeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button buttonNumeralia;

    @FXML
    public void goToNumeralia(ActionEvent event){
        

        FXMLLoader numeraliaLoader = new FXMLLoader(getClass().getResource("numeralia.fxml"));
        try {
            Parent root = numeraliaLoader.load();
            Scene numeraliaScene = new Scene(root);
            Stage numeraliaStage = new Stage();
            numeraliaStage.initStyle(StageStyle.TRANSPARENT);
            numeraliaStage.setScene(numeraliaScene);
            numeraliaStage.show();

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException goToNumeraliaException){
            LOG.error("ERROR:", goToNumeraliaException);
        }
    }

    @FXML
    private Button buttonConfiguration;
    @FXML
    private void goToSettings(ActionEvent event){

    }
    @FXML
    Button buttonHome;
    @FXML
    private void goToHomepage(ActionEvent event){
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/GUI/adminHome.fxml"));
        try {
            Parent root = homeLoader.load();
            Scene homeScene = new Scene(root);
            Stage homeStage = new Stage();
            homeStage.initStyle(StageStyle.TRANSPARENT);
            homeStage.setScene(homeScene);
            homeStage.show();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException goToHomeException){
            LOG.error("ERROR", goToHomeException);
        }

    }

    @FXML
    private Label labelAdminName;
    @FXML
    public void initialize(){
        Admin adminData = new Admin();
        //adminData = UserSessionManager.getInstance().getAdminUserData();
        //labelAdminName.setText(adminData.getAdminName());
        loadUniversities();
        tableViewUniversities.setOnMouseClicked(event -> {
            University university = tableViewUniversities.getSelectionModel().getSelectedItem();
            if(event.getClickCount() == 1){
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/universityDetails.fxml"));
                    Parent root = loader.load();
                    UniversityDetailsController controller = loader.getController();
                    controller.initialize(university.getUniversityId());
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException universityDetailsException){
                    LOG.error("ERROR:", universityDetailsException);
                }
            }
        });

    }
     

}
