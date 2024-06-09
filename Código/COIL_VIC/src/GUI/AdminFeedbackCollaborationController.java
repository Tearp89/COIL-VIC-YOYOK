package GUI;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log;
import logic.DAO.AdminDAO;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Admin;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class AdminFeedbackCollaborationController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AdminFeedbackCollaborationController.class);

    @FXML
    private TableView<Collaboration> tableViewClosedCollaborations;
    @FXML
    private TableColumn<Collaboration, String> tableColumnCollaborationId;
    @FXML
    private TableColumn<Collaboration, String> tableColumnCollaborationName;
    @FXML
    private TableColumn<Collaboration, String> tableColumnStartDate;
    @FXML
    private TableColumn<Collaboration, String> tableColumnFinishDate;
    @FXML
    private TableColumn<Collaboration, String> tableColumnStatus;
    @FXML 
    Label labelCollaborationNotFound = new Label("No se encontraron colaboraciones");

    public void loadClosedCollaborations(){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        String user = adminData.getAdminUser();
        AdminDAO adminDAO = new AdminDAO();
        int adminId = adminDAO.getAdminIdByUser(user);
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> closedCollaborations = new ArrayList<>();
        closedCollaborations = collaborationDAO.getUnreviewedCollaborationsByAdmin(adminId);
        tableViewClosedCollaborations.getItems().addAll(closedCollaborations);
        if(closedCollaborations.size() == 0){
            tableViewClosedCollaborations.setPlaceholder(labelCollaborationNotFound);
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
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminCollaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
    }

    @FXML
    private Button buttonProfessors;
    @FXML
    private void goToProfessors(ActionEvent event){
        FXMLLoader professorOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminProfessorOptions.fxml"));
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
        FXMLLoader goBackLoader = new FXMLLoader(getClass().getResource("/GUI/adminCollaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, goBackLoader);
    }

        @FXML
    private Label labelUser;
    @FXML
    private void initialize(){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        String user = adminData.getAdminUser();
        AdminDAO adminDAO = new AdminDAO();
        int adminId = adminDAO.getAdminIdByUser(user);
        labelUser.setText(adminData.getAdminName());
        loadClosedCollaborations();
        tableViewClosedCollaborations.setOnMouseClicked(event ->{
            if(event.getClickCount() == 1){
                Collaboration closedCollaboration = tableViewClosedCollaborations.getSelectionModel().getSelectedItem();
                if(closedCollaboration != null){
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/adminGradeCollaboration.fxml"));
                        Parent root = loader.load();
                        AdminGradeCollaborationController controller = loader.getController();
                        controller.initialize(closedCollaboration.getCollaborationId());
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(new Scene(root));
                        stage.show();
                        Node source = (Node) event.getSource();
                        Stage currenStage = (Stage) source.getScene().getWindow();
                        currenStage.close();
                    }catch (IOException e){
                        LOG.error("ERROR:", e);
                    }
                }
            }
        });
    }



}
