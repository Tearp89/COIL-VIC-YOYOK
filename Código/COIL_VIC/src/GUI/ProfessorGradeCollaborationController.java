package GUI;

import java.io.IOException;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import log.Log;
import logic.EmailControl;
import logic.DAO.CollaborationDAO;
import logic.DAO.FeedbackDAO;
import logic.DAO.ProfessorDAO;
import logic.DAO.UniversityDAO;
import logic.classes.Feedback;
import logic.classes.Professor;

public class ProfessorGradeCollaborationController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ProfessorGradeCollaborationController.class);
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
    private void logout(ActionEvent event){
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
    private Button buttonSendFeedback;
    @FXML
    private void sendFeedback(ActionEvent event){
        int grade = Integer.parseInt(comboBoxGrade.getSelectionModel().getSelectedItem().toString()) ;
        String comments = textAreaComments.getText();
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
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            Feedback feedback = new Feedback();
            Professor professorData = new Professor();
            professorData = UserSessionManager.getInstance().getProfessorUserData();
            String user = professorData.getUser();
            ProfessorDAO professorDAO = new ProfessorDAO();
            int professorId = professorDAO.getProfessorIdByUser(user);
            feedback.setGrade(grade);
            feedback.setComments(comments);
            feedback.setProfessorId(professorId);
            feedback.setCollaborationId(collaborationId);
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            int result = feedbackDAO.addProfessorReview(feedback);
            if(result > 0){
                Alert feedbackSentAlert = new Alert(AlertType.INFORMATION);
                feedbackSentAlert.setTitle("Retroalimentación enviada");
                feedbackSentAlert.setHeaderText("Retroalimentación enviada");
                feedbackSentAlert.setContentText("Retroalimentación enviada exitosamente");
                feedbackSentAlert.show();
                
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

    }

    @FXML
    private Button buttonCancelFeedback;
    @FXML
    private void cancelFeedback(ActionEvent event){
        Alert cancelFeedbackAlert = new Alert(AlertType.ERROR);
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
            FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
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
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        this.collaborationId = collaborationId;
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        String collaborationName = collaborationDAO.getCollaborationNameById(collaborationId);
        if(textAreaComments.getText().isEmpty() || comboBoxGrade.getSelectionModel().isEmpty()){
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
