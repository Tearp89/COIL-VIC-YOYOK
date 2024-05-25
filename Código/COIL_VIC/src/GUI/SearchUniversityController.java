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
        FXMLLoader numeraliaLoader = new FXMLLoader(getClass().getResource("/GUI/numeralia.fxml"));
        ChangeWindowManager.changeWindowTo(event, numeraliaLoader);
    }
    @FXML
    private Button buttonLogout;
    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
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
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminUniversityOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
    }

    @FXML
    private Button buttonProfessors;
    @FXML
    private void goToProfessors(ActionEvent event){
        FXMLLoader professorOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminProfessorsOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, professorOptionsLoader);
    }

    @FXML
    private Button buttonUniversities;
    @FXML
    private void goToUniversities(ActionEvent event){
        FXMLLoader universitiesOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminUniversityOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, universitiesOptionsLoader);
    }

    @FXML
    private Button buttonHome;
    @FXML
    private void goToHomepage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/adminHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonBack;
    @FXML
    private void goBack(ActionEvent event){
        FXMLLoader goBackLoader = new FXMLLoader(getClass().getResource("/GUI/adminUniversityOptions.fxml"));
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
