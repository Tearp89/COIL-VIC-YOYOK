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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class SearchAcceptedCollaborationsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(SearchAcceptedCollaborationsController.class);

    @FXML
    private TableView<Collaboration> tableViewAcceptedCollaborations;
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
    private String user;
    @FXML
    Label labelCollaborationNotFound = new Label("No se encontraron colaboraciones");
    


    public void loadAcceptedCollaborations(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        user = professorData.getUser();
        CollaborationDAO acceptedCollaborations = new CollaborationDAO();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        ProfessorDAO professorDAO = new ProfessorDAO();
        
        int idProfesor = professorDAO.getProfessorIdByUser(user);
        collaborations = acceptedCollaborations.searchCollaborationByStatusAndProfessorId("Aceptada", idProfesor);
        tableViewAcceptedCollaborations.getItems().addAll(collaborations);
        if(collaborations.size() == 0){
            tableViewAcceptedCollaborations.setPlaceholder(labelCollaborationNotFound);
        }
        
    }

    @FXML
    private TextField textFieldSearch;
        public void searchAcceptedCollaborations(ActionEvent event){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> activeCollaborations = new ArrayList<>();
        String collaborationName = "%" + textFieldSearch.getText() + "%";
        activeCollaborations = collaborationDAO.searchCollaborationByStatusAndName("Activa", collaborationName);
        tableViewAcceptedCollaborations.getItems().clear();
        tableViewAcceptedCollaborations.getItems().addAll(activeCollaborations);
        if(activeCollaborations.size() == 0){
            tableViewAcceptedCollaborations.setPlaceholder(labelCollaborationNotFound);
        }
        

    }

    @FXML
    private Button buttonHome;

    @FXML
    private void goToHomePage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonCollaborations;

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationsOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsOptionsLoader);


    }

    @FXML
    private Button buttonStudents;

    @FXML
    private void goToStudents(ActionEvent event){
        FXMLLoader studentsLoader = new FXMLLoader(getClass().getResource("/GUI/StudentOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, studentsLoader);

    }

    @FXML
    private Button buttonSettings;

    @FXML
    private void goToSettings(ActionEvent event){
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorSettingsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, settingsLoader);
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
    private Button buttonCancel;

    @FXML
    private void cancel(ActionEvent  event){
        FXMLLoader collaborationsSectionLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsSectionLoader);
    }

    
    @FXML
    private Label labelUser;
    @FXML
    private void initialize(){
        
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        loadAcceptedCollaborations();
        tableViewAcceptedCollaborations.setOnMouseClicked(event -> {
            if(!DatabaseConnectionChecker.isDatabaseConnected()){
                DatabaseConnectionChecker.showNoConnectionDialog();
                tableViewAcceptedCollaborations.setDisable(true);
            }
            if(event.getClickCount() == 1){
                Collaboration acceptedCollaboration = tableViewAcceptedCollaborations.getSelectionModel().getSelectedItem();
                if(acceptedCollaboration != null){
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AcceptedCollaborationDetailsWindow.fxml"));
                        Parent root = loader.load();
                        AcceptedCollaborationDetailsController controller = loader.getController();
                        controller.initialize(acceptedCollaboration.getCollaborationId());
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(new Scene(root));
                        stage.show();
                        Node source = (Node) event.getSource();
                        Stage currenStage = (Stage) source.getScene().getWindow();
                        currenStage.close();

                        
                    } catch (IOException openAcceptedCollaborationsDetailsException){
                        LOG.error("ERROR:", openAcceptedCollaborationsDetailsException);
                    }
                }
            }
        });
    }




}
