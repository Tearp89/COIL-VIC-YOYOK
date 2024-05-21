package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import logic.DAO.UniversityDAO;
import logic.classes.University;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log;

public class UniversityDetailsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(UniversityDetailsController.class);
    @FXML
    TextField textFieldUniversityName;
    @FXML
    TextField textFieldUniversityCountry;
    @FXML
    TextField textFieldUniversityLanguage;
    @FXML
    Button buttonSaveUniversity;
    @FXML 
    Button buttonCancel;
    @FXML
    Button buttonEditUniversity;



    @FXML
    void editUniversity(ActionEvent event){
        textFieldUniversityName.setEditable(true);
        textFieldUniversityCountry.setEditable(true);
        textFieldUniversityLanguage.setEditable(true);
        buttonSaveUniversity.setVisible(true);
        buttonCancel.setVisible(true);
        buttonEditUniversity.setVisible(false);

    }

    @FXML
    void cancelEdition(ActionEvent event){
        Alert confirmCancel = new Alert(AlertType.CONFIRMATION);
        //TODO: Agregar interacción entre ventanas para salir.

    }

    @FXML
    void buttonCancel(ActionEvent event){

    }

    @FXML
    void saveChanges(ActionEvent event){
        String universityName = textFieldUniversityName.getText();
        String universityLanguage = textFieldUniversityLanguage.getText();
        String universityCountry = textFieldUniversityCountry.getText();
        University universityUpdated = new University();
        UniversityDAO universityDAO = new UniversityDAO();
        universityUpdated.setUniversityName(universityName);
        universityUpdated.setUniversityLanguage(universityLanguage);
        universityUpdated.setUniversityCountry(universityCountry);
        universityUpdated.setUniversityId(universityId);
        UniversityDAO updateUniversity = new UniversityDAO();
        if(universityName.isEmpty() || universityLanguage.isEmpty() 
        || universityCountry.isEmpty()){
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos vacíos");
            emptyFieldsAlert.setHeaderText("Error edición");
            emptyFieldsAlert.setContentText("No se puede editar la universidad hay campos vacíos");
            emptyFieldsAlert.show();
            
        } if (universityDAO.isUniversityRegistered(universityName) == true){
            Alert duplicateUniversityAlert = new Alert(AlertType.ERROR);
            duplicateUniversityAlert.setHeaderText("Error edición");
            duplicateUniversityAlert.setTitle("Universidad duplicada");
            duplicateUniversityAlert.setContentText("No se puede editar la universidad, ya está registrada");
            duplicateUniversityAlert.show();
        }else{
            Alert confirmEditionAlert = new Alert(AlertType.CONFIRMATION);
            confirmEditionAlert.setHeaderText("Confirmación edición");
            confirmEditionAlert.setTitle("Confirmar edición");
            confirmEditionAlert.setContentText("¿Está seguro de que desea editar la universidad?");
            ButtonType acceptEdition = new ButtonType("Confirmar");
            ButtonType cancelEdition = new ButtonType("Cancelar");
            confirmEditionAlert.getButtonTypes().setAll(acceptEdition, cancelEdition);
            confirmEditionAlert.show();
            Button okButton = (Button) confirmEditionAlert.getDialogPane().lookupButton(acceptEdition);
            Button cancelButton = (Button) confirmEditionAlert.getDialogPane().lookupButton(cancelEdition);
            okButton.setOnAction(eventSaveEdition -> {
                int result = universityDAO.updateUniversity(universityUpdated);
                if(result == 1){
                    Alert universityUpdatedAlert = new Alert(AlertType.INFORMATION);
                    universityUpdatedAlert.setHeaderText("Confirmación edición");
                    universityUpdatedAlert.setTitle("Edición exitosa");
                    universityUpdatedAlert.setContentText("Se ha actualizado la universidad exitosamente");
                    universityUpdatedAlert.show();
    
                } else{
                    Alert editionErrorAlert = new Alert(AlertType.ERROR);
                    editionErrorAlert.setTitle("Error edición");
                    editionErrorAlert.setHeaderText("Error edición");
                    editionErrorAlert.setContentText("Ocurrió un error intentelo nuevamente");
                    editionErrorAlert.show();
                }
            });
           cancelButton.setOnAction(eventCancelEdition -> {
            Alert confirmCancel = new Alert(AlertType.CONFIRMATION);
            confirmCancel.setTitle("Confirmar cancelación");
            confirmCancel.setHeaderText("Confirmar cancelación");
            confirmCancel.setContentText("¿Está seguro de que desea salir?");
            confirmCancel.show();
            //TODO: Agregar interacción entre ventanas para cancelar y agregar los botones aceptar y cancelar.
           });
        }

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
    private void goToHomePage(ActionEvent event){
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
    int universityId;
    public void initialize(int universityId){
        this.universityId = universityId;
        UniversityDAO universityDAO = new UniversityDAO();
        String universityName = universityDAO.getUniversityNameById(universityId);
        String universityLanguage = universityDAO.getUniversityLanguageById(universityId);
        String universityCountry = universityDAO.getUniversityCountryById(universityId);
        textFieldUniversityName.setText(universityName);
        textFieldUniversityCountry.setText(universityCountry);
        textFieldUniversityLanguage.setText(universityLanguage);

    }


}
