package GUI;

import java.io.IOException;
import java.sql.SQLDataException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class SearchDeclinedCollaborationsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AcceptedCollaborationDetailsController.class);

    @FXML
    private TableView<Collaboration> tableViewDeclinedCollaborations;
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
    private TextField textFieldSearch; 

  

    public void loadDeclinedCollaboration(){
        CollaborationDAO declinedCollaborations = new CollaborationDAO();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        try{
            Professor professorData = UserSessionManager.getInstance().getProfessorUserData();
            ProfessorDAO professorDAO = new ProfessorDAO();
            int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
            collaborations = declinedCollaborations.searchCollaborationByStatusAndProfessorId("Rechazada", professorId);
        }catch(Exception loadDeclinedCollaborationsException){
            loadDeclinedCollaborationsException.printStackTrace();
        }

        tableViewDeclinedCollaborations.getItems().addAll(collaborations);

    }

    public void searchDeclinedCollaborations(ActionEvent event){
        Professor professorData = UserSessionManager.getInstance().getProfessorUserData();
            ProfessorDAO professorDAO = new ProfessorDAO();
            int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String collaborationName = "%" + textFieldSearch.getText() + "%";
        collaborations = collaborationDAO.searchCollaborationByStatusNameandProfessorId("Rechazada", collaborationName, professorId);
        tableViewDeclinedCollaborations.getItems().clear();
        tableViewDeclinedCollaborations.getItems().addAll(collaborations);
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

            loadDeclinedCollaboration();
    tableViewDeclinedCollaborations.setOnMouseClicked(event -> {
        if(event.getClickCount() == 1){
            Collaboration declinedCollaboration = tableViewDeclinedCollaborations.getSelectionModel().getSelectedItem();
            if(declinedCollaboration != null){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DeclinedCollaborationDetails.fxml"));
                    Parent root = loader.load();
                    DeclinedCollaborationDetailsController controller = loader.getController();
                    controller.initialize(declinedCollaboration.getCollaborationId());
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(new Scene(root));
                    stage.show();
                    Node source = (Node) event.getSource();
                    Stage currenStage = (Stage) source.getScene().getWindow();
                    currenStage.close();
                } catch (IOException e) {
                    LOG.error("ERROR:", e);
                }
            }
        }
    });
    

    }
    
}
