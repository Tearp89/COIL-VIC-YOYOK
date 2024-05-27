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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import logic.DAO.UniversityDAO;
import logic.classes.Admin;
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
        Alert confirmCancelAlert = new Alert(AlertType.CONFIRMATION);
        confirmCancelAlert.setTitle("Confirmar cancelación");
        confirmCancelAlert.setHeaderText("Confirmar cancelación");
        confirmCancelAlert.setContentText("¿Está seguro de que desea salir? Se perderán todos sus cambios");
        confirmCancelAlert.show();
        ButtonType acceptCancel = new ButtonType("Aceptar");
        ButtonType cancelCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        confirmCancelAlert.getButtonTypes().setAll(acceptCancel, cancelCancel);
        confirmCancelAlert.show();
        Button okButton = (Button) confirmCancelAlert.getDialogPane().lookupButton(acceptCancel);
        Button cancelButton = (Button) confirmCancelAlert.getDialogPane().lookupButton(cancelCancel);
        okButton.setOnAction(eventAcceptCancel -> {
            FXMLLoader universityOptionLoader = new FXMLLoader(getClass().getResource("/GUI/AdminUniversityOptions.fxml"));
            ChangeWindowManager.changeWindowTo(event, universityOptionLoader);
        });

        cancelButton.setOnAction(eventCancelCancel -> {
            confirmCancelAlert.close();
        });

    }
    @FXML
    private Button buttonBack;
    @FXML
    private void cancel(ActionEvent event){
        FXMLLoader universityOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminUniversityOptions.fxml"));
        ChangeWindowManager.changeWindowTo(event, universityOptionsLoader);

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
            Alert confirmCancelAlert = new Alert(AlertType.CONFIRMATION);
            confirmCancelAlert.setTitle("Confirmar cancelación");
            confirmCancelAlert.setHeaderText("Confirmar cancelación");
            confirmCancelAlert.setContentText("¿Está seguro de que desea salir?");
            ButtonType accept = new ButtonType("Confirmar");
            ButtonType cancel = new ButtonType("Cancelar");
            confirmCancelAlert.getButtonTypes().setAll(accept, cancel);
            confirmEditionAlert.show();
            Button acceptButton = (Button) confirmEditionAlert.getDialogPane().lookupButton(accept);
            Button cancelCancelButton = (Button) confirmEditionAlert.getDialogPane().lookupButton(cancel);
            acceptButton.setOnAction(eventGoBack -> {
                FXMLLoader universitiesLoader = new FXMLLoader(getClass().getResource("/GUI/adminCollaborationOptions.fxml"));
                ChangeWindowManager.changeWindowTo(event, universitiesLoader);
            });
            

            cancelCancelButton.setOnAction(cancelEvent -> {
                confirmCancelAlert.close();
            });
            
            confirmCancelAlert.show();
           });
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
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/adminUniversityOptions.fxml"));
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
    private void goToHomePage(ActionEvent event){
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/adminHome.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);
    }
    @FXML
    private Label labelUser;
    @FXML
    int universityId;
    public void initialize(int universityId){
        Admin adminData = new Admin();
        adminData = UserSessionManager.getInstance().getAdminUserData();
        labelUser.setText(adminData.getAdminName());
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
