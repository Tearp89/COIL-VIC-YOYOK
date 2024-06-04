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
import logic.classes.Admin;
import logic.classes.Collaboration;
import logic.classes.Student;

public class StudentsFeedbackCollaborationController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(StudentsFeedbackCollaborationController.class);

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
        Student studentData = new Student();
        studentData = UserSessionManager.getInstance().getStudentUserData();
        String email = studentData.getEmail();
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> closedCollaborations = new ArrayList<>();
        closedCollaborations = collaborationDAO.getUnreviewedCollaborationsByStudent(email);
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
    private Button buttonHome;
    @FXML
    private void goToHomepage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/studentsHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonBack;
    @FXML
    private void goBack(ActionEvent event){
        FXMLLoader goBackLoader = new FXMLLoader(getClass().getResource("/GUI/studentsHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, goBackLoader);
    }

        @FXML
    private void initialize(){
        Student studentData = new Student();
        studentData = UserSessionManager.getInstance().getStudentUserData();
        String email = studentData.getEmail();
        System.out.println(email);
        loadClosedCollaborations();
        tableViewClosedCollaborations.setOnMouseClicked(event ->{
            if(event.getClickCount() == 1){
                Collaboration closedCollaboration = tableViewClosedCollaborations.getSelectionModel().getSelectedItem();
                if(closedCollaboration != null){
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/studentGradeCollaboration.fxml"));
                        Parent root = loader.load();
                        StudentGradeCollaborationController controller = loader.getController();
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
