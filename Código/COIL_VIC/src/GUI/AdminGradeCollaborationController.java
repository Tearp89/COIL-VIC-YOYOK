package GUI;

import java.io.IOException;

import dataAccess.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import log.Log;
import logic.FieldValidator;
import logic.DAO.AdminDAO;
import logic.DAO.CollaborationDAO;
import logic.DAO.FeedbackDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Admin;
import logic.classes.Feedback;
import logic.classes.Professor;

public class AdminGradeCollaborationController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AdminGradeCollaborationController.class);

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
    private void goToHomePage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/adminHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }

    @FXML
    private Button buttonSendFeedback;
    @FXML
    private void sendFeedback(ActionEvent event){
        int grade = Integer.parseInt(comboBoxGrade.getSelectionModel().getSelectedItem().toString()) ;
        String comments = textAreaComments.getText();
        if(FieldValidator.onlyTextAndNumbers(comments.trim())){
            Alert confirmFeedbackAlert = new Alert(AlertType.CONFIRMATION);
            confirmFeedbackAlert.setTitle("Confirmar retroalimentación");
            confirmFeedbackAlert.setHeaderText("Confirmar retroalimentación");
            confirmFeedbackAlert.setContentText("¿Está seguro de que desea enviar la retroalimentación?");
            ButtonType acceptFeedback = new ButtonType("Aceptar");
            ButtonType cancelFeedback = new ButtonType("Cancelar");
            confirmFeedbackAlert.getButtonTypes().setAll(acceptFeedback, cancelFeedback);
            confirmFeedbackAlert.show();
            Button okButton = (Button) confirmFeedbackAlert.getDialogPane().lookupButton(acceptFeedback);
            Button cancelButton = (Button) confirmFeedbackAlert.getDialogPane().lookupButton(cancelFeedback);
            okButton.setOnAction( eventSendFeedback -> {
                Feedback feedback = new Feedback();
                Admin adminData = new Admin();
                adminData = UserSessionManager.getInstance().getAdminUserData();
                String user = adminData.getAdminUser();
                AdminDAO adminDAO = new AdminDAO();
                int adminId = adminDAO.getAdminIdByUser(user);
                feedback.setGrade(grade);
                feedback.setComments(comments);
                feedback.setAdminId(adminId);
                feedback.setCollaborationId(collaborationId);
                FeedbackDAO feedbackDAO = new FeedbackDAO();
                int result = feedbackDAO.addAdminReview(feedback);
                if(result > 0){
                    Alert feedbackSentAlert = new Alert(AlertType.INFORMATION);
                    feedbackSentAlert.setTitle("Retroalimentación enviada");
                    feedbackSentAlert.setHeaderText("Retroalimentación enviada");
                    feedbackSentAlert.setContentText("Retroalimentación enviada exitosamente");
                    feedbackSentAlert.show();
                    buttonCancelFeedback.setDisable(true);
                    buttonSendFeedback.setDisable(true);
                } else {
                    Alert sendErrorAlert = new Alert(AlertType.ERROR);
                    sendErrorAlert.setTitle("Error conexión");
                    sendErrorAlert.setHeaderText("Error conexión");
                    sendErrorAlert.setContentText("Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
                    sendErrorAlert.show();
                }
            });

            cancelButton.setOnAction(eventCancelFeedBack -> {
                confirmFeedbackAlert.close();
            });
        } else {
            Alert sendErrorAlert = new Alert(AlertType.ERROR);
            sendErrorAlert.setTitle("Datos no validos");
            sendErrorAlert.setHeaderText(null);
            sendErrorAlert.setContentText("Existen datos vacios o no validos para la retroalimentación");
            sendErrorAlert.show();
        }
    }

    @FXML
    private Button buttonCancelFeedback;
    @FXML
    private void cancelFeedback(ActionEvent event){
        Alert cancelFeedbackAlert = new Alert(AlertType.WARNING);
        cancelFeedbackAlert.setTitle("Confirmar cancelación");
        cancelFeedbackAlert.setHeaderText("Confirmar cancelación");
        cancelFeedbackAlert.setContentText("¿Está seguro de que desea salir? se perderán todos sus cambios");
        cancelFeedbackAlert.show();

        ButtonType acceptCancel = new ButtonType("Aceptar");
        ButtonType cancelCancel = new ButtonType("Cancelar");
        cancelFeedbackAlert.getButtonTypes().setAll(acceptCancel, cancelCancel);
        cancelFeedbackAlert.show();
        Button okButton = (Button) cancelFeedbackAlert.getDialogPane().lookupButton(acceptCancel);
        Button cancelButton = (Button) cancelFeedbackAlert.getDialogPane().lookupButton(cancelCancel);
        okButton.setOnAction(acceptCancelEvent -> {
            FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminCollaborationOptions.fxml"));
            ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
        });

        cancelButton.setOnAction(cancelCancelEvent -> {
            cancelFeedbackAlert.close();
        });


    }
    
    private void updateButtonState() {
        boolean disableButton = textAreaComments.getText().isEmpty() || comboBoxGrade.getSelectionModel().isEmpty();
        buttonSendFeedback.setDisable(disableButton);
    }
    
    @FXML
    private ComboBox<String> comboBoxGrade;
    @FXML
    private TextArea textAreaComments;
    @FXML
    private Label labelUser;
    @FXML
    private int collaborationId;
    
    public void initialize(int collaborationId){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        labelUser.setText(adminData.getAdminName());
        this.collaborationId = collaborationId;
        String comments = textAreaComments.getText();
        String grade = comboBoxGrade.getSelectionModel().getSelectedItem();
        if(!FieldValidator.onlyText(comments) || !FieldValidator.onlyNumber(grade)){
            buttonSendFeedback.setDisable(true);
        } else {
            buttonSendFeedback.setDisable(false);
        }

        ObservableList<String> grades = FXCollections.observableArrayList();

        for (int i = 1; i <= 10; i++) {
            grades.add(String.valueOf(i));
        }

        comboBoxGrade.getItems().setAll(grades);

        textAreaComments.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButtonState();
        });
        
        comboBoxGrade.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updateButtonState();
        });
        
        updateButtonState();

        

    }

    


}
