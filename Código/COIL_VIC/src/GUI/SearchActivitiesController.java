package GUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import log.Log;
import logic.FieldValidator;
import logic.DAO.ActivityDAO;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Activity;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class SearchActivitiesController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AddCollaborationController.class);

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
    private void goBack(ActionEvent  event){
        FXMLLoader collaborationsSectionLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationsSectionLoader);
    }

    @FXML
    private Button buttonLogout;

    @FXML
    private void logOut(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
        try{
            ChangeWindowManager.logout(event, loginLoader);
        } catch (IOException logoutException){
            LOG.error("ERROR:", logoutException);
        }
    }

    @FXML
    private Button buttonEdit;
    @FXML
    private void edit(ActionEvent event){
        comboBoxType.setDisable(false);
        textAreaDescription.setDisable(false);
        textFieldTitle.setDisable(false);
        textFieldTitle.setDisable(false);
        textAreaDescription.setEditable(true);
        textFieldTitle.setEditable(true);
        buttonSave.setVisible(true);
        buttonCancel.setDisable(false);
        comboBoxWeek.setDisable(true);

    }

    @FXML
    private Button buttonSave;
    @FXML
    private void save(ActionEvent event){
        String title  = textFieldTitle.getText();
        String type = comboBoxType.getValue();
        String description = textAreaDescription.getText();
        String week = comboBoxWeek.getValue();
        Activity activity = new Activity();
        activity.setDescription(description);
        activity.setTitle(title);
        activity.setType(type);
        activity.setActivityId(Integer.parseInt(labelActivityId.getText()));
        activity.setWeek(week);
        Professor professorData = UserSessionManager.getInstance().getProfessorUserData();
        ProfessorDAO professorDAO = new ProfessorDAO();
        String user = professorData.getUser();
        int professorId = professorDAO.getProfessorIdByUser(user);
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> publishedCollaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Publicada", professorId);
        int collaborationId = publishedCollaborations.get(0).getCollaborationId();
        ActivityDAO activityDAO = new ActivityDAO();
        if(!FieldValidator.onlyTextAndNumbers(title) || !FieldValidator.onlyText(type) || !FieldValidator.onlyNumber(week)){
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos vacíos o inválidos");
            emptyFieldsAlert.setHeaderText("Campos vacíos o inválidos");
            emptyFieldsAlert.setContentText("No se modificar la actividad, hay campos vacíos o inválidos");
            emptyFieldsAlert.show();
        } else{
            Alert confirmAssignAlert = new Alert(AlertType.CONFIRMATION);
            confirmAssignAlert.setTitle("Confirmar actividad");
            confirmAssignAlert.setHeaderText("Confirmar actividad");
            confirmAssignAlert.setContentText("¿Está seguro de que desea editar esta actividad?");
            confirmAssignAlert.show();

            ButtonType confirm = new ButtonType("Aceptar" );
            ButtonType cancel = new ButtonType("Cancelar");
            confirmAssignAlert.getButtonTypes().setAll(confirm, cancel);

            Button okButton = (Button) confirmAssignAlert.getDialogPane().lookupButton(confirm);
            Button cancelButon = (Button) confirmAssignAlert.getDialogPane().lookupButton(cancel);

            okButton.setOnAction(eventAssignActivity -> {
                
                int result = activityDAO.updateActivity(activity);
                if (result > 0){
                    Alert editionSuccessAlert = new Alert(AlertType.INFORMATION);
                    editionSuccessAlert.setTitle("Actividad editada");
                    editionSuccessAlert.setHeaderText("Actividad editada");
                    editionSuccessAlert.setContentText("Se editó la actividad exitosamente");
                    editionSuccessAlert.show();
                } else{
                    Alert editionErrorAlert = new Alert(AlertType.ERROR);
                    editionErrorAlert.setTitle("Error conexión");
                    editionErrorAlert.setHeaderText("Error conexión");
                    editionErrorAlert.setContentText("Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
                    editionErrorAlert.show();
                }
            });

            cancelButon.setOnAction(eventCancelAssign -> {
                confirmAssignAlert.close();
            });
            

        }
        comboBoxWeek.setDisable(false);


    }

    @FXML
    private Button buttonCancel;
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
            FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
            ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
        });
        cancelButon.setOnAction(eventCancelCancel ->{
            confirmCancelationAlert.close();
        });
        
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
    private Label labelUser;
    @FXML
    private Label labelActivityId;
    
    private void loadActivitiesForSelectedWeek(String week) {
        Professor professorData = UserSessionManager.getInstance().getProfessorUserData();
        ProfessorDAO professorDAO = new ProfessorDAO();
        String user = professorData.getUser();
        int professorId = professorDAO.getProfessorIdByUser(user);
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> publishedCollaborations = collaborationDAO.searchCollaborationByStatusAndProfessorId("Publicada", professorId);

        if (!publishedCollaborations.isEmpty()) {
            int collaborationId = publishedCollaborations.get(0).getCollaborationId();
            ActivityDAO activityDAO = new ActivityDAO();
            List<Activity> activities = activityDAO.getActivitiesByCollaborationAndWeek(collaborationId, week);

            if (!activities.isEmpty()) {
                Activity activity = activities.get(0);
                textFieldTitle.setText(activity.getTitle());
                textAreaDescription.setText(activity.getDescription());
                comboBoxType.setValue(activity.getType());
                labelActivityId.setText(String.valueOf(activity.getActivityId()));
            } else {
                
                textFieldTitle.clear();
                textAreaDescription.clear();
                comboBoxType.setValue(null);
                labelActivityId.setText("");
            }
        }
    }

    @FXML
    private void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
       ObservableList<String> weeks = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        comboBoxWeek.setItems(weeks);

        ObservableList<String> types = FXCollections.observableArrayList("Rompe hielo", "Reflexión", "Proyecto COIL", "Retroalimentación", "Introducción", "Cultura");
        comboBoxType.setItems(types);

        comboBoxWeek.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadActivitiesForSelectedWeek(newValue);
            }
        });

        buttonSave.setVisible(false);

    }


}
