package GUI;

import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import log.Log;
import logic.FieldValidator;
import logic.DAO.ActivityDAO;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Activity;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class AddActivityController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AddActivityController.class);
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
    private Button buttonBack;
    @FXML
    private void goBack(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);

    }
    @FXML
    private TextField textFieldTitle;
    @FXML
    private ComboBox<String> comboBoxWeek;
    @FXML
    private ComboBox<String> comboBoxType;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private Button buttonSave;
    @FXML 
    private Button buttonAssign;
    @FXML
    private Button buttonCancel;
    @FXML
    private int activityId;
    @FXML
    private void saveActivity(ActionEvent event){
        String title = textFieldTitle.getText();
        String week = comboBoxWeek.getValue();
        String type = comboBoxType.getValue();
        String description = textAreaDescription.getText();
        if(!FieldValidator.onlyText(title) || !FieldValidator.onlyNumber(week) || !FieldValidator.onlyText(type) || !FieldValidator.onlyText(description)){
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos vacíos o incorrectos");
            emptyFieldsAlert.setHeaderText("Campos vacíos o incorrectos");
            emptyFieldsAlert.setContentText("No se puede agregar la actividad hay campos vacios o incorrectos");
            emptyFieldsAlert.show();
        } else{
            Activity activity = new Activity();
            activity.setDescription(description);
            activity.setTitle(title);
            activity.setType(type);
            activity.setWeek(week);
            ActivityDAO activityDAO = new ActivityDAO();
           int result =  activityDAO.addActivity(activity);
           if(result > 0){
            buttonAssign.setDisable(false);
            buttonCancel.setDisable(false);
            activityId = activity.getActivityId();
            buttonSave.setDisable(true);

           }
        }


    }

    @FXML
    private void assignActivity(ActionEvent event){
        ActivityDAO activityDAO = new ActivityDAO();
        int collaborationId = Integer.parseInt(labelCollaborationId.getText()); 
        String week = activityDAO.getActivityWeekById(activityId);
        String type = activityDAO.getActivityTypeById(activityId);
        if(activityDAO.isActivityAssignedInWeek(collaborationId, week) == true ){
            Alert duplicatedWeekAlert = new Alert(AlertType.ERROR);
            duplicatedWeekAlert.setTitle("Semana duplicada");
            duplicatedWeekAlert.setHeaderText("Semana duplicada");
            duplicatedWeekAlert.setContentText("No se puede asignar la actividad, ya existe una actividad para esa semana");
            duplicatedWeekAlert.show();
        } else{
            Alert confirmAssignAlert = new Alert(AlertType.CONFIRMATION);
            confirmAssignAlert.setTitle("Confirmar actividad");
            confirmAssignAlert.setHeaderText("Confirmar actividad");
            confirmAssignAlert.setContentText("¿Está seguro de que desea asignar esta actividad?");
            confirmAssignAlert.show();

            ButtonType confirm = new ButtonType("Aceptar" );
            ButtonType cancel = new ButtonType("Cancelar");
            confirmAssignAlert.getButtonTypes().setAll(confirm, cancel);

            Button okButton = (Button) confirmAssignAlert.getDialogPane().lookupButton(confirm);
            Button cancelButon = (Button) confirmAssignAlert.getDialogPane().lookupButton(cancel);

            okButton.setOnAction(eventAssignActivity -> {
                int result = activityDAO.assignActivityToCollaboration(collaborationId, activityId);
                if (result > 0){
                    Alert assignConfirmationAlert = new Alert(AlertType.INFORMATION);
                    assignConfirmationAlert.setTitle("Actividad asignada");
                    assignConfirmationAlert.setHeaderText("Actividad asignada");
                    assignConfirmationAlert.setContentText("Se asignó la actividad exitosamente");
                    assignConfirmationAlert.show();
                    buttonAssign.setDisable(true);
                } else{
                    Alert assignErrorAlert = new Alert(AlertType.ERROR);
                    assignErrorAlert.setTitle("Error conexión");
                    assignErrorAlert.setHeaderText("Error conexión");
                    assignErrorAlert.setContentText("Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
                    assignErrorAlert.show();
                }
            });

            cancelButon.setOnAction(eventCancelAssign -> {
                confirmAssignAlert.close();
            });
        }

    }


    @FXML 
    private void cancel(ActionEvent event){
        Alert confirmCancelationAlert = new Alert(AlertType.CONFIRMATION);
        confirmCancelationAlert.setTitle("Confirmar salida");
        confirmCancelationAlert.setHeaderText("Confirmar salida");
        confirmCancelationAlert.setContentText("¿Está seguro de que desea salir? Se perderán todos los cambios");
        confirmCancelationAlert.show();

        ButtonType confirm = new ButtonType("Aceptar" );
        ButtonType cancel = new ButtonType("Cancelar");
        confirmCancelationAlert.getButtonTypes().setAll(confirm, cancel);

        Button okButton = (Button) confirmCancelationAlert.getDialogPane().lookupButton(confirm);
        Button cancelButon = (Button) confirmCancelationAlert.getDialogPane().lookupButton(cancel);

        okButton.setOnAction(eventConfirmCanel ->{
            FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/collaborationOptions.fxml"));
            ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
        });
        cancelButon.setOnAction(eventCancelCancel ->{
            confirmCancelationAlert.close();
        });

    }


    @FXML
    private Label labelCollaborationName;
    @FXML
    private Label labelUser;
    @FXML
    private Label labelCollaborationId;
    @FXML
    private void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());

        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> publishedCollaboration = collaborationDAO.searchCollaborationByStatusAndProfessorId("Publicada", professorId);
        labelCollaborationId.setText(String.valueOf(publishedCollaboration.get(0).getCollaborationId()));
        labelCollaborationName.setText(publishedCollaboration.get(0).getCollaborationName());
        buttonCancel.setDisable(true);
        buttonAssign.setDisable(true);
        ObservableList<String> weeks  = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        comboBoxWeek.getItems().setAll(weeks);
        ObservableList<String> types = FXCollections.observableArrayList("Rompe hielo", "Reflexión", "Proyecto COIL", "Retroalimentación", "Introduccion", "Cultura" );
        comboBoxType.getItems().setAll(types);


    }

}
