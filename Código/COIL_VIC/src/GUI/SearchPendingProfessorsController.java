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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class SearchPendingProfessorsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(SearchPendingProfessorsController.class);
    @FXML 
    private TableView<Professor> tableViewPendingProfessors;
    @FXML
    private TableColumn<Professor, String> tableColumnProfessorId;
    @FXML
    private TableColumn<Professor, String> tableColumnProfessorName;
    @FXML
    private TableColumn<Professor, String> tableColumnProfessorPhone;
    @FXML
    private TableColumn<Professor, String> tableColumnProfessorCountry;
    @FXML
    private TableColumn<Professor, String> tableColumnProfessorUniversity;

    public void loadPendingProfessors(){
        ProfessorDAO professorDAO = new ProfessorDAO();
        ArrayList<Professor> pendingProfessors = new ArrayList<>();
        pendingProfessors = professorDAO.searchProfessorByStatus("Pendiente");
        tableViewPendingProfessors.getItems().addAll(pendingProfessors);
        if(pendingProfessors.size() == 0){
            Label labelCollaborationNotFound = new Label("No se encontraron profesores pendientes");
            tableViewPendingProfessors.setPlaceholder(labelCollaborationNotFound);
        }
    }


    private void addAcceptButtonToTable() {
    TableColumn<Professor, Void> tableColumnAcceptButton = new TableColumn("Aceptar");

    Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>> cellFactory = new Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>>() {
        @Override
        public TableCell<Professor, Void> call(final TableColumn<Professor, Void> param) {
            final TableCell<Professor, Void> cell = new TableCell<Professor, Void>() {
                private final Button acceptButton = new Button("Aceptar");

                {
                    acceptButton.setOnAction((ActionEvent event) -> {
                        Professor professor = getTableView().getItems().get(getIndex());
                        int professorId = professor.getProfessorId();
                        ProfessorDAO professorDAO = new ProfessorDAO();
                        if(professor.getWorkShop() == "No"){
                            Alert noWorkShopAlert = new Alert(AlertType.ERROR);
                            noWorkShopAlert.setHeaderText("Curso-Taller no cursado");
                            noWorkShopAlert.setTitle("No Curso-Taller");
                            noWorkShopAlert.setContentText("No se puede aceptar el académico, Curso-Taller no cursado");
                            noWorkShopAlert.show();
                        }else{
                            int result = professorDAO.changeProfessorStatusById("Aceptado", professorId);
                        if(result == 1){
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

    tableViewPendingProfessors.getColumns().add(tableColumnAcceptButton);
}

private void addDeclineButtonToTable() {
    TableColumn<Professor, Void> tableColumnDeclineButtom = new TableColumn("Rechazar");

    Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>> cellFactory = new Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>>() {
        @Override
        public TableCell<Professor, Void> call(final TableColumn<Professor, Void> param) {
            final TableCell<Professor, Void> cell = new TableCell<Professor, Void>() {
                private final Button declineButton = new Button("Rechazar");

                {
                    declineButton.setOnAction((ActionEvent event) -> {
                        Professor professor = getTableView().getItems().get(getIndex());
                        int professorId = professor.getProfessorId();
                        ProfessorDAO professorDAO = new ProfessorDAO();
                        int result = professorDAO.changeProfessorStatusById("Rechazado", professorId);
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

    tableViewPendingProfessors.getColumns().add(tableColumnDeclineButtom);
}



    @FXML
    private Button buttonCollaborations;
    @FXML
    private void goToCollaborations(ActionEvent event){

    }

    @FXML
    private Button buttonProfessors;
    @FXML
    private void goToProfessors(ActionEvent event){

    }
    @FXML
    private Button buttonLogout;
    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/login.fxml"));
        try {
            UserSessionManager.getInstance().logoutAdmin();
            Parent root = loginLoader.load();
            Scene loginScene = new Scene(root);
            Stage loginStage = new Stage();
            loginStage.setScene(loginScene);
            loginStage.show();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException goToLoginException){
            LOG.error(goToLoginException);
        }
    }

    @FXML
    private Button buttonMinimize;
    @FXML
    private void minimizeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private Button buttonClose;
    @FXML
    private void closeWindow(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button buttonNumeralia;

    @FXML
    public void goToNumeralia(ActionEvent event){
        

        FXMLLoader numeraliaLoader = new FXMLLoader(getClass().getResource("numeralia.fxml"));
        try {
            Parent root = numeraliaLoader.load();
            Scene numeraliaScene = new Scene(root);
            Stage numeraliaStage = new Stage();
            numeraliaStage.initStyle(StageStyle.TRANSPARENT);
            numeraliaStage.setScene(numeraliaScene);
            numeraliaStage.show();

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException goToNumeraliaException){
            LOG.error("ERROR:", goToNumeraliaException);
        }
    }

    @FXML
    private Button buttonConfiguration;
    @FXML
    private void goToSettings(ActionEvent event){

    }
    @FXML
    Button buttonHome;
    @FXML
    private void goToHomepage(ActionEvent event){
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/GUI/adminHome.fxml"));
        try {
            Parent root = homeLoader.load();
            Scene homeScene = new Scene(root);
            Stage homeStage = new Stage();
            homeStage.initStyle(StageStyle.TRANSPARENT);
            homeStage.setScene(homeScene);
            homeStage.show();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } catch (IOException goToHomeException){
            LOG.error("ERROR", goToHomeException);
        }

    }

    @FXML
    private void initialize(){
        loadPendingProfessors();
        addAcceptButtonToTable();
        addDeclineButtonToTable();
    }


}
