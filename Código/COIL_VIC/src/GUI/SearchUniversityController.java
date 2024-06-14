package GUI;

import java.io.IOException;
import java.util.ArrayList;

import dataAccess.DatabaseConnectionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    Label labelCollaborationNotFound = new Label("No se encontraron universidades");

    public void loadUniversities(){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
        } else {
            UniversityDAO universitiyDAO = new UniversityDAO();
            ArrayList<University> universities = new ArrayList<>();
            universities = universitiyDAO.searchUniversity();
            tableViewUniversities.getItems().addAll(universities);
            if(universities.size() == 0){
                tableViewUniversities.setPlaceholder(labelCollaborationNotFound);
            }
        }
        
    }

    @FXML
    private TextField textFieldSearch;
    public void searchUniversities(ActionEvent e){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
        } else {
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
    private void goToHomepage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/AdminHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonBack;
    @FXML
    private void goBack(ActionEvent event){
        FXMLLoader goBackLoader = new FXMLLoader(getClass().getResource("/GUI/AdminUniversityOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, goBackLoader);
    }

    

    @FXML
    private Label labelUser;
    @FXML
    public void initialize(){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        labelUser.setText(adminData.getAdminName());
        loadUniversities();
        tableViewUniversities.setOnMouseClicked(event -> {
            University university = tableViewUniversities.getSelectionModel().getSelectedItem();
            if(event.getClickCount() == 1){
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/UniversityDetailsWindow.fxml"));
                    Parent root = loader.load();
                    UniversityDetailsController controller = loader.getController();
                    controller.initialize(university.getUniversityId());
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(new Scene(root));
                    stage.show();
                    Node source = (Node) event.getSource();
                    Stage currenStage = (Stage) source.getScene().getWindow();
                    currenStage.close();
                } catch (IOException universityDetailsException){
                    LOG.error("ERROR:", universityDetailsException);
                }
            }
        });

    }

}
