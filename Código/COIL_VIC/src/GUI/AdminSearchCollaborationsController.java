package GUI;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.classes.Admin;
import logic.classes.Collaboration;

public class AdminSearchCollaborationsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AdminSearchCollaborationsController.class);
    @FXML
    private TableView<Collaboration> tableViewCollaborations;
    @FXML
    private TableColumn<Collaboration, String> tableColumnCollaborationId;
    @FXML
    private TableColumn<Collaboration, String> tableColumnCollaborationName;
    @FXML
    private TableColumn<Collaboration, String> tableColumnStartDate;
    @FXML
    private TableColumn<Collaboration, String> tableColumnFinishDate;
    @FXML
    private Label labelCollaborationNotFound = new Label("No se encontraron colaboraciones");

    public void loadCollaborations(){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        collaborations = collaborationDAO.searchCollaborationByStatus("Activa");
        tableViewCollaborations.getItems().addAll(collaborations);
        if (collaborations.size() == 0){
            tableViewCollaborations.setPlaceholder(labelCollaborationNotFound);
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
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminCollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
    }

    @FXML
    private Button buttonProfessors;
    @FXML
    private void goToProfessors(ActionEvent event){
        FXMLLoader professorOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminProfessorOptionsWindow.fxml"));
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
        FXMLLoader goBackLoader = new FXMLLoader(getClass().getResource("/GUI/AdminCollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, goBackLoader);
    }


    @FXML
    private Label labelUser;
    @FXML
    private void initialize(){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        loadCollaborations();
        labelUser.setText(adminData.getAdminName());
    }

}
