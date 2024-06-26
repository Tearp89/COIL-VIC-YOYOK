package GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.mail.MessagingException;

import dataAccess.DatabaseConnectionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.util.Callback;
import log.Log;
import logic.EmailControl;
import logic.DAO.ProfessorDAO;
import logic.classes.Admin;
import logic.classes.Professor;

public class PendingProfessorsSearcherController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(PendingProfessorsSearcherController.class);
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
    TableColumn<Professor, Void> tableColumnAcceptButton = new TableColumn<>("Aceptar");

    Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>> cellFactory = new Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>>() {
        @Override
        public TableCell<Professor, Void> call(final TableColumn<Professor, Void> param) {
            final TableCell<Professor, Void> cell = new TableCell<Professor, Void>() {
                private final Button acceptButton = new Button("Aceptar");{
                    acceptButton.setOnAction((ActionEvent event) -> {
                        if(!DatabaseConnectionChecker.isDatabaseConnected()){
                            DatabaseConnectionChecker.showNoConnectionDialog();
                            return;
                        }
                        Professor professor = getTableView().getItems().get(getIndex());
                        int professorId = professor.getProfessorId();
                        ProfessorDAO professorDAO = new ProfessorDAO();
                        if(professorDAO.getWorkShopByProfessorId(professorId).equals("No")){
                            Alert noWorkShopAlert = new Alert(AlertType.ERROR);
                            noWorkShopAlert.setHeaderText("Curso-Taller no cursado");
                            noWorkShopAlert.setTitle("No Curso-Taller");
                            noWorkShopAlert.setContentText("No se puede aceptar el académico, Curso-Taller no cursado");
                            noWorkShopAlert.show();
                        }else{
                            Alert confirmDeclineAlert = new Alert(AlertType.CONFIRMATION);
                            confirmDeclineAlert.setTitle("Confirmar aceptación");
                            confirmDeclineAlert.setHeaderText("Confirmar aceptación");
                            confirmDeclineAlert.setContentText("¿Está seguro de que desea aceptar al académico?");
                            confirmDeclineAlert.show();
                            ButtonType accept = new ButtonType("Aceptar");
                            ButtonType cancel = new ButtonType("Cancelar");
                            confirmDeclineAlert.getButtonTypes().setAll(accept, cancel);
                            Button okButton = (Button) confirmDeclineAlert.getDialogPane().lookupButton(accept); 
                            Button cancelButton = (Button) confirmDeclineAlert.getDialogPane().lookupButton(cancel);
                            okButton.setOnAction(eventAddProfessorForeign -> {
                                if(!DatabaseConnectionChecker.isDatabaseConnected()){
                                    DatabaseConnectionChecker.showNoConnectionDialog();
                                    return;
                                }
                                int result = professorDAO.changeProfessorStatusById("Aceptado", professorId);
                                if(result == 1){
                                    EmailControl emailControl = new EmailControl();
                                    try {
                                        emailControl.sendEmail(professorDAO.getEmailById(professorId), "Aceptado en COIL-VIC", "No ha sido aceptado en el sistema COIL-VIC, para más información acudir a secretaria de la entidad más cercana");
                                        Alert acceptanceSuccessfulAlert = new Alert(AlertType.INFORMATION);
                                        acceptanceSuccessfulAlert.setTitle("Académico aceptado");
                                        acceptanceSuccessfulAlert.setHeaderText("Académico aceptado");
                                        acceptanceSuccessfulAlert.setContentText("Académico aceptado exitosamente, se ha enviado por correo su confirmación");
                                        acceptanceSuccessfulAlert.show();
                                    } catch (MessagingException e) {
                                        Alert acceptanceSuccessfulNoEmailAlert = new Alert(AlertType.INFORMATION);
                                        acceptanceSuccessfulNoEmailAlert.setTitle("Académico aceptado");
                                        acceptanceSuccessfulNoEmailAlert.setHeaderText("Académico aceptado, no se envió correo");
                                        acceptanceSuccessfulNoEmailAlert.setContentText("Académico aceptado exitosamente, pero no se pudo enviar el correo de confirmación");
                                        acceptanceSuccessfulNoEmailAlert.show();
                                    }
                                }else{
                                    Alert acceptanceErrorAlert = new Alert(AlertType.ERROR);
                                    acceptanceErrorAlert.setTitle("Error conexión");
                                    acceptanceErrorAlert.setHeaderText("Error conexión");
                                    acceptanceErrorAlert.setContentText("No se pudo conectar a la base de datos, por favor inténtelo de nuevo más tarde");
                                    acceptanceErrorAlert.show();
                                }
                                tableViewPendingProfessors.getItems().clear();
                                loadPendingProfessors();
                            
                            });
                            
                            cancelButton.setOnAction(eventCancelAddProfessor -> {
                                confirmDeclineAlert.close();
                            });
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
        TableColumn<Professor, Void> tableColumnDeclineButtom = new TableColumn<>("Rechazar");

        Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>> cellFactory = new Callback<TableColumn<Professor, Void>, TableCell<Professor, Void>>() {
            @Override
            public TableCell<Professor, Void> call(final TableColumn<Professor, Void> param) {
                final TableCell<Professor, Void> cell = new TableCell<Professor, Void>() {
                    private final Button declineButton = new Button("Rechazar");

                    {
                        declineButton.setOnAction((ActionEvent event) -> {
                            if(!DatabaseConnectionChecker.isDatabaseConnected()){
                                DatabaseConnectionChecker.showNoConnectionDialog();
                                return;
                            }
                            Professor professor = getTableView().getItems().get(getIndex());
                            int professorId = professor.getProfessorId();
                            ProfessorDAO professorDAO = new ProfessorDAO();
                            
                            Alert confirmDeclineAlert = new Alert(AlertType.CONFIRMATION);
                            confirmDeclineAlert.setTitle("Confirmar rechazo");
                            confirmDeclineAlert.setHeaderText("Confirmar rechazo");
                            confirmDeclineAlert.setContentText("¿Está seguro de que desea rechazar al académico?");
                            confirmDeclineAlert.show();
                            ButtonType accept = new ButtonType("Aceptar");
                            ButtonType cancel = new ButtonType("Cancelar");
                            confirmDeclineAlert.getButtonTypes().setAll(accept, cancel);
                            Button okButton = (Button) confirmDeclineAlert.getDialogPane().lookupButton(accept); 
                            Button cancelButton = (Button) confirmDeclineAlert.getDialogPane().lookupButton(cancel);
                            okButton.setOnAction(eventAddProfessorForeign -> {
                                TextInputDialog dialog = new TextInputDialog();
                                dialog.setTitle("Motivo de Rechazo");
                                dialog.setHeaderText("Ingrese el motivo del rechazo del profesor:");
                                dialog.setContentText("Motivo:");

                                Optional<String> dialogReason = dialog.showAndWait();
                                String reason = dialogReason.orElse("No se proporcionó un motivo específico.");

                                if(!DatabaseConnectionChecker.isDatabaseConnected()){
                                    DatabaseConnectionChecker.showNoConnectionDialog();
                                    return;
                                }
                                int result = professorDAO.changeProfessorStatusById("Rechazado", professorId);
                                if(result == 1){
                                    EmailControl emailControl = new EmailControl();
                                    try {
                                        emailControl.sendEmail(professorDAO.getEmailById(professorId), "Rechazo en COIL-VIC", "Su solicitud ha sido rechazada. " + (reason.isEmpty() ? "No se proporcionó un motivo específico." : "Motivo: " + reason));
                                        Alert rejectedSuccessfulAlert = new Alert(AlertType.INFORMATION);
                                        rejectedSuccessfulAlert.setTitle("Solicitud rechazada");
                                        rejectedSuccessfulAlert.setHeaderText("Solicitud rechazada");
                                        rejectedSuccessfulAlert.setContentText("Solicitud rechazada exitosamente");
                                        rejectedSuccessfulAlert.show();
                                        rejectedSuccessfulAlert.show();
                                    } catch (MessagingException e) {
                                        Alert rejectedSuccessfulNoEmailAlert = new Alert(AlertType.INFORMATION);
                                        rejectedSuccessfulNoEmailAlert.setTitle("Solicitud rechazada");
                                        rejectedSuccessfulNoEmailAlert.setHeaderText("Solicitud rechazada, error al enviar correo");
                                        rejectedSuccessfulNoEmailAlert.setContentText("Solicitud rechazada exitosamente, pero no se pudo enviar un correo al profesor, favor de avisarle");
                                        rejectedSuccessfulNoEmailAlert.show();
                                        rejectedSuccessfulNoEmailAlert.show();
                                    }
                                } else{
                                    Alert rejectedErrorAlert = new Alert(AlertType.ERROR);
                                    rejectedErrorAlert.setTitle("Error conexión");
                                    rejectedErrorAlert.setHeaderText("Error conexión");
                                    rejectedErrorAlert.setContentText("No se pudo conectar a la base de datos, por favor inténtelo de nuevo más tarde");
                                    rejectedErrorAlert.show();
                                }
                                tableViewPendingProfessors.getItems().clear();
                                loadPendingProfessors();
                                
                            });

                            cancelButton.setOnAction(eventCancelDeclineProfessor -> {
                                confirmDeclineAlert.close();
                            });
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
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminCollaborationsOptionsWindow.fxml"));
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
        FXMLLoader goBackLoader = new FXMLLoader(getClass().getResource("/GUI/AdminProfessorOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, goBackLoader);
    }

    @FXML
    private Label labelUser;

    @FXML
    private void initialize(){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        labelUser.setText(adminData.getAdminName());
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        loadPendingProfessors();
        addAcceptButtonToTable();
        addDeclineButtonToTable();
    }


}
