package GUI;

import java.io.IOException;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log;

public class RequestsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(RequestsController.class);
    @FXML
    private ComboBox<String> comboBoxRequestType;
    @FXML
    private Button buttonAnswerRequest;
    @FXML
    private Button buttonCancel;

    @FXML
    private void answerRequest(){
        if(comboBoxRequestType.getSelectionModel().getSelectedItem() == "Académico"){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/searchPendingProfessor.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch (IOException answerRequestException){
                LOG.error("ERROR:", answerRequestException);
            }
            
            

        }else{
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/searchPendingCollaborations.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }catch (IOException answerRequestException){
                

        }
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
    private void cancelAnswer(){

    }






    @FXML
    private void initialize(){
        ObservableList<String> requestTypes = comboBoxRequestType.getItems();
        requestTypes.setAll("Académico", "Colaboración");
        comboBoxRequestType.setItems(requestTypes);
    }

}
