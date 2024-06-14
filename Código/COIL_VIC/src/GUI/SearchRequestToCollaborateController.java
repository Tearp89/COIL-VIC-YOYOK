package GUI;

import java.io.IOException;
import java.util.ArrayList;

import dataAccess.DatabaseConnectionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class SearchRequestToCollaborateController {

    private static final org.apache.log4j.Logger LOG = Log.getLogger(SearchRequestToCollaborateController.class);

    @FXML 
    private TableView<Professor> tableViewPendingCollaborators;
    @FXML
    private TableColumn<Professor, String> tableColumnProfessorId;
    @FXML
    private TableColumn<Professor, String> tableColumnProfessorName;
    @FXML
    private TableColumn<Professor, String> tableColumnProfessorPhone;
    @FXML
    private TableColumn<Professor, String> tableColumnProfessorCountry;


    public void loadCollaborators(){
        ProfessorDAO professorDAO = new ProfessorDAO();
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        String user = professorData.getUser();
        int professorId = professorDAO.getProfessorIdByUser(user);
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Publicada", professorId);
        int collaborationId = collaborations.get(0).getCollaborationId();
        ArrayList<Professor> pendingCollaborators = new ArrayList<>();
        pendingCollaborators = collaborationDAO.getProfessorsWithCollaborationRequests(collaborationId);
        tableViewPendingCollaborators.getItems().addAll(pendingCollaborators);
        if(pendingCollaborators.size() == 0){
            Label labelCollaborationNotFound = new Label("No se encontraron profesores pendientes");
            tableViewPendingCollaborators.setPlaceholder(labelCollaborationNotFound);
        }
    }


    Professor selectedProfessor = new Professor();
    private void addAcceptButtonToTable() {
    TableColumn<Professor, Void> tableColumnAcceptButton = new TableColumn("Aceptar");

    Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>> cellFactory = new Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>>() {
        @Override
        public TableCell<Professor, Void> call(final TableColumn<Professor, Void> param) {
            final TableCell<Professor, Void> cell = new TableCell<Professor, Void>() {
                private final Button acceptButton = new Button("Aceptar"); {
                    acceptButton.setOnAction((ActionEvent event) -> {
                        ProfessorDAO professorDAO = new ProfessorDAO();
                        Professor professorData = new Professor();
                        professorData = UserSessionManager.getInstance().getProfessorUserData();
                        String user = professorData.getUser();
                        int professorId = professorDAO.getProfessorIdByUser(user);
                        CollaborationDAO collaborationDAO = new CollaborationDAO();
                        ArrayList<Collaboration> collaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Publicada", professorId);
                        int collaborationId = collaborations.get(0).getCollaborationId();
                        Professor requester = getTableView().getItems().get(getIndex());
                        int requesterId = requester.getProfessorId();
                        if(!collaborationDAO.validateCollaborationProfessorsLimit(collaborationId)){
                            Alert collaboratorAlreadyAlert = new Alert(AlertType.ERROR);
                            collaboratorAlreadyAlert.setHeaderText("Colaborador ya asignado");
                            collaboratorAlreadyAlert.setTitle("Colaborador ya asignado");
                            collaboratorAlreadyAlert.setContentText("No se puede aceptar al académico, ya hay un colaborador aceptado");
                            collaboratorAlreadyAlert.show();
                        }else if(collaborationDAO.isProfessorInCollaboration(requesterId)){
                            Alert invalidCollaboratorAlert = new Alert(AlertType.ERROR);
                            invalidCollaboratorAlert.setHeaderText("Colaborador ya asignado");
                            invalidCollaboratorAlert.setTitle("Colaborador ya asignado");
                            invalidCollaboratorAlert.setContentText("No se puede aceptar al académico, ya es parte de otra colaboración");
                            invalidCollaboratorAlert.show();

                        } else {

                            
                            int result = collaborationDAO.changeRequestStatus(requesterId, collaborationId, "Aceptado");
                            int assignResult = collaborationDAO.assignProfessorToCollaboration(requesterId, collaborationId);
                        if(result == 1 && assignResult == 1){
                            Alert acceptanceSuccessfulAlert = new Alert(AlertType.INFORMATION);
                            acceptanceSuccessfulAlert.setTitle("Académico aceptado");
                            acceptanceSuccessfulAlert.setHeaderText("Académico aceptado");
                            acceptanceSuccessfulAlert.setContentText("Académico aceptado exitosamente");
                            acceptanceSuccessfulAlert.show();

                        }else{
                            Alert acceptanceErrorAlert = new Alert(AlertType.ERROR);
                            acceptanceErrorAlert.setTitle("Error conexión");
                            acceptanceErrorAlert.setHeaderText("Error conexión");
                            acceptanceErrorAlert.setContentText("No se pudo conectar a la base de datos, por favor inténtelo de nuevo más tarde");
                            acceptanceErrorAlert.show();
                        }
                    }
                        
                        
                    });
                }

                @Override
                public void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(acceptButton);
                    }
                }
            };
            return cell;
        }
    };

    tableColumnAcceptButton.setCellFactory(cellFactory);

    tableViewPendingCollaborators.getColumns().add(tableColumnAcceptButton);
    }   

    @FXML
    private Button buttonHome;

    @FXML
    private void goToHomepage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML 
    private Button buttonCollaboration;

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationsLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsLoader);

    }
    @FXML
    private Button buttonStudents;
    
    @FXML
    private void goToStudents(ActionEvent event){
        FXMLLoader goToStudentsLoader = new FXMLLoader(getClass().getResource("/GUI/StudentOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, goToStudentsLoader);

    }
    @FXML
    private Button buttonSettings;

    @FXML
    private void goToSettings(ActionEvent event){
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/GUI/ProfessorSettingsWindow.fxml"));
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
    private Button buttonBack;
    @FXML
    private void goBack(ActionEvent event){
        FXMLLoader studentOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, studentOptionsLoader);
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
    private Label labelUser;
    @FXML
    private void initialize(){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
        }
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        loadCollaborators();
        addAcceptButtonToTable();
    }

}
