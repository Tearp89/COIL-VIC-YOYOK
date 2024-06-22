package GUI;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataAccess.DatabaseConnectionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import log.Log;
import logic.DAO.ProfessorDAO;
import logic.classes.Professor;

public class ProfessorSettingsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ProfessorSettingsController.class);

    @FXML
    private Label labelName;

    @FXML
    private Label labelPhone;

    @FXML
    private Label labelAcademicArea;

    @FXML
    private Label labelEmail;

    @FXML
    private TextField textFieldOldPassword;

    @FXML
    private TextField textFieldNewPassword;

    @FXML
    private Button buttonChangePassword;

    
    @FXML
    public void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        String user = professorData.getUser();
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            buttonChangePassword.setDisable(true);
        }
        ProfessorDAO professorDAO = new ProfessorDAO();
        String email = professorDAO.getProfessorEmailByUser(user);
        String phoneNumber = professorDAO.getProfessorPhoneByUser(user);
        
        labelName.setText(professorData.getName());
        labelPhone.setText(phoneNumber);
        labelEmail.setText(email);
    }

    @FXML
    private Button buttonHome;

    @FXML
    private void goToHomepage(ActionEvent event){
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
    private Button buttonLogout;

    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
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
        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/GUI/CollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homePageLoader);

    }

    @FXML
    private void changePassword(ActionEvent event){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            return;
        }
        ProfessorDAO professorDAO = new ProfessorDAO();
        String user = UserSessionManager.getInstance().getProfessorUserData().getUser();
        int profesirId = professorDAO.getProfessorIdByUser(user);
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            buttonChangePassword.setDisable(true);
        } else {
            String oldPassword = textFieldOldPassword.getText();
            String newPassword = textFieldNewPassword.getText();
            boolean passwordExist = professorDAO.compareProfessorPassword(oldPassword, profesirId);
            if (!validatePassword(newPassword)) {
                showAlertErrorValidation("La contraseña debe tener al menos 8 caracteres, incluir una letra mayúscula, una letra minúscula, un número y un carácter especial.");
                return;
            }
            if(passwordExist){
                if (oldPassword == newPassword){
                    Alert samePasswordAlert = new Alert(AlertType.ERROR);
                    samePasswordAlert.setHeaderText("La contraseña antigua y la nueva son la misma");
                    samePasswordAlert.setTitle("Contraseña duplicada");
                    samePasswordAlert.setContentText("Favor de ingresar una nueva constraseña");
                    samePasswordAlert.show();
                } else {
                    Alert confirmEditionAlert = new Alert(AlertType.CONFIRMATION);
                    confirmEditionAlert.setHeaderText("Confirmación cambio");
                    confirmEditionAlert.setTitle("Confirmar edición");
                    confirmEditionAlert.setContentText("¿Está seguro de que desea cambiar la contraseña?");
                    ButtonType acceptEdition = new ButtonType("Confirmar");
                    confirmEditionAlert.getButtonTypes().setAll(acceptEdition);
                    confirmEditionAlert.show();
                    Button okButton = (Button) confirmEditionAlert.getDialogPane().lookupButton(acceptEdition);
                    okButton.setOnAction(eventSaveEdition -> {
                        if(!DatabaseConnectionChecker.isDatabaseConnected()){
                            DatabaseConnectionChecker.showNoConnectionDialog();
                            return;
                        }
                        int result = professorDAO.changeProfessorPassword(newPassword, profesirId);
                        if(result == 1){
                            Alert universityUpdatedAlert = new Alert(AlertType.INFORMATION);
                            universityUpdatedAlert.setHeaderText("Confirmación edición");
                            universityUpdatedAlert.setTitle("Edición exitosa");
                            universityUpdatedAlert.setContentText("Se ha actualizado la contraseña");
                            universityUpdatedAlert.show();
                            textFieldNewPassword.setText(null);
                            textFieldOldPassword.setText(null);
                        } else{
                            Alert editionErrorAlert = new Alert(AlertType.ERROR);
                            editionErrorAlert.setTitle("Error edición");
                            editionErrorAlert.setHeaderText("Error edición");
                            editionErrorAlert.setContentText("Ocurrió un error intentelo nuevamente");
                            editionErrorAlert.show();
                        }
                    });
                }
            } else {
                Alert professorOldPasswordAlert = new Alert(AlertType.ERROR);
                professorOldPasswordAlert.setHeaderText("Contraseña antigua incorrecta");
                professorOldPasswordAlert.setTitle("Contraseña incorrecta");
                professorOldPasswordAlert.setContentText("La contraseña antigua que ingreso es incorrecta");
                professorOldPasswordAlert.show();
            }
        }
        
    }

    private boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Pattern digitPattern = Pattern.compile("[0-9]");
        Pattern specialCharacterPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher upperCaseMatcher = upperCasePattern.matcher(password);
        Matcher lowerCaseMatcher = lowerCasePattern.matcher(password);
        Matcher digitMatcher = digitPattern.matcher(password);
        Matcher specialCharacterMatcher = specialCharacterPattern.matcher(password);

        return upperCaseMatcher.find() && lowerCaseMatcher.find() && digitMatcher.find() && specialCharacterMatcher.find();
    }

    private void showAlertErrorValidation(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error de validación");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
