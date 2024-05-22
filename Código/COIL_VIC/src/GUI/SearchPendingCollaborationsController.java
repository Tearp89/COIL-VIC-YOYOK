package GUI;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.classes.Admin;
import logic.classes.Collaboration;

public class SearchPendingCollaborationsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(SearchPendingCollaborationsController.class);
    @FXML
    private TableView<Collaboration> tableViewPendingCollaborations;
    @FXML
    private TableColumn<Collaboration, String> tableColumnCollaborationId;
    @FXML
    private TableColumn<Collaboration, String> tableColumnCollaborationName;
    @FXML
    private TableColumn<Collaboration, String> tableColumnStartDate;
    @FXML
    private TableColumn<Collaboration, String> tableColumnFinishDate;
    @FXML
    private Label labelCollaborationNotFound = new Label("No se encontraron colaboraciones pendientes");

    public void loadPendingCollaborations(){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> pendingCollaborations = new ArrayList<>();
        pendingCollaborations = collaborationDAO.searchCollaborationByStatus("Pendiente");
        tableViewPendingCollaborations.getItems().addAll(pendingCollaborations);
        if (pendingCollaborations.size() == 0){
            tableViewPendingCollaborations.setPlaceholder(labelCollaborationNotFound);
        }

    }

    private void addAcceptButtonToTable() {
    TableColumn<Collaboration, Void> tableColumnAcceptButton = new TableColumn("Aceptar");

    Callback<TableColumn<Collaboration, Void>, TableCell<Collaboration, Void>> cellFactory = new Callback<TableColumn<Collaboration, Void>, TableCell<Collaboration, Void>>() {
        @Override
        public TableCell<Collaboration, Void> call(final TableColumn<Collaboration, Void> param) {
            final TableCell<Collaboration, Void> cell = new TableCell<Collaboration, Void>() {
                private final Button acceptButton = new Button("Aceptar");

                {
                    acceptButton.setOnAction((ActionEvent event) -> {
                        Collaboration collaboration = getTableView().getItems().get(getIndex());
                        int collaborationId = collaboration.getCollaborationId();
                        CollaborationDAO collaborationDAO = new CollaborationDAO();
                        int result = collaborationDAO.changeCollaborationStatus("Aceptada", collaborationId);
                        if(result == 1){
                            Alert acceptanceSuccessfulAlert = new Alert(AlertType.INFORMATION);
                            acceptanceSuccessfulAlert.setTitle("Colaboración aceptada");
                            acceptanceSuccessfulAlert.setHeaderText("Colaboración aceptada");
                            acceptanceSuccessfulAlert.setContentText("Propuesta de colaboración aceptada exitosamente");
                            acceptanceSuccessfulAlert.show();
                        } else{
                            Alert acceptanceErrorAlert = new Alert(AlertType.ERROR);
                            acceptanceErrorAlert.setTitle("Error conexión");
                            acceptanceErrorAlert.setHeaderText("Error conexión");
                            acceptanceErrorAlert.setContentText("No se pudo conectar a la base de datos, por favor inténtelo de nuevo más tarde");
                            acceptanceErrorAlert.show();
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

    tableViewPendingCollaborations.getColumns().add(tableColumnAcceptButton);
}

    private void addDeclineButtonToTable() {
        TableColumn<Collaboration, Void> tableColumnDeclineButtom = new TableColumn("Rechazar");

        Callback<TableColumn<Collaboration, Void>, TableCell<Collaboration, Void>> cellFactory = new Callback<TableColumn<Collaboration, Void>, TableCell<Collaboration, Void>>() {
            @Override
            public TableCell<Collaboration, Void> call(final TableColumn<Collaboration, Void> param) {
                final TableCell<Collaboration, Void> cell = new TableCell<Collaboration, Void>() {
                    private final Button declineButton = new Button("Rechazar");

                    {
                        declineButton.setOnAction((ActionEvent event) -> {
                            Collaboration collaboration = getTableView().getItems().get(getIndex());
                            int collaborationId = collaboration.getCollaborationId();
                            CollaborationDAO collaborationDAO = new CollaborationDAO();
                            int result = collaborationDAO.changeCollaborationStatus("Rechazada", collaborationId);
                            if(result == 1){
                                Alert acceptanceSuccessfulAlert = new Alert(AlertType.INFORMATION);
                                acceptanceSuccessfulAlert.setTitle("Solicitud rechazada");
                                acceptanceSuccessfulAlert.setHeaderText("Solicitud rechazada");
                                acceptanceSuccessfulAlert.setContentText("Solicitud rechazada exitosamente");
                                acceptanceSuccessfulAlert.show();
                            } else{
                                Alert acceptanceErrorAlert = new Alert(AlertType.ERROR);
                                acceptanceErrorAlert.setTitle("Error conexión");
                                acceptanceErrorAlert.setHeaderText("Error conexión");
                                acceptanceErrorAlert.setContentText("No se pudo conectar a la base de datos, por favor inténtelo de nuevo más tarde");
                                acceptanceErrorAlert.show();
                            }
                            
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(declineButton);
                        }
                    }
                };
                return cell;
            }
        };

        tableColumnDeclineButtom.setCellFactory(cellFactory);

        tableViewPendingCollaborations.getColumns().add(tableColumnDeclineButtom);
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
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminCollaborartionOptions.fxml"));
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
        FXMLLoader goBackLoader = new FXMLLoader();
        ChangeWindowManager.changeWindowTo(event, goBackLoader);
    }


    @FXML
    private Label labelUser;
    @FXML
    private void initialize(){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        labelUser.setText(adminData.getAdminUser());
        loadPendingCollaborations();
        addAcceptButtonToTable();
        addDeclineButtonToTable();
        }

    }
