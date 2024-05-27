package GUI;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class ProfessorFeedbackCollaborationController {

    private static final org.apache.log4j.Logger LOG = Log.getLogger(PublishedCollaborationsController.class);

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
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        String user = professorData.getUser();
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(user);
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> closedCollaborations = new ArrayList<>();
        closedCollaborations = collaborationDAO.getUnreviewedCollaborationsByProfessor(professorId);
        tableViewClosedCollaborations.getItems().addAll(closedCollaborations);
        if(closedCollaborations.size() == 0){
            tableViewClosedCollaborations.setPlaceholder(labelCollaborationNotFound);
        }
    }



    @FXML
    private Button buttonHome;

    @FXML
    private void goToHomePage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/professorHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonCollaborations;

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationsOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsOptionsLoader);


    }

    @FXML
    private Button buttonStudents;

    @FXML
    private void goToStudents(ActionEvent event){
        FXMLLoader studentsLoader = new FXMLLoader(getClass().getResource("/GUI/studentOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, studentsLoader);

    }

    @FXML
    private Button buttonSettings;

    @FXML
    private void goToSettings(ActionEvent event){
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/GUI/professorSettings.fxml"));
        ChangeWindowManager.changeWindowTo(event, settingsLoader);

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
    private Button buttonLogout;

    @FXML
    private void logOut(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try{
            ChangeWindowManager.logout(event, loginLoader);
        } catch (IOException logoutException){
            LOG.error("ERROR:", logoutException);
        }
    }

    @FXML
    private Button buttonCancel;
    @FXML
    private void cancel(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);

    }

    @FXML
    private Label labelUser;
    @FXML
    private void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        loadClosedCollaborations();
        tableViewClosedCollaborations.setOnMouseClicked(event ->{
            if(event.getClickCount() == 1){
                Collaboration closedCollaboration = tableViewClosedCollaborations.getSelectionModel().getSelectedItem();
                if(closedCollaboration != null){
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/professorGradeCollaboration.fxml"));
                        Parent root = loader.load();
                        ProfessorGradeCollaborationController controller = loader.getController();
                        controller.initialize(closedCollaboration.getCollaborationId());
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                    }catch (IOException e){
                        LOG.error("ERROR:", e);
                    }
                }
            }
        });
    }

}
