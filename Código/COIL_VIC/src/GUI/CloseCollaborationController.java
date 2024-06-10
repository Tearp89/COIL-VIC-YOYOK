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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class CloseCollaborationController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(CloseCollaborationController.class);
    @FXML
    private Label labelCollaborationName;
    @FXML
    private DatePicker datePickerStartDate;
    @FXML
    private DatePicker datePickerFinishDate;
    @FXML
    private Label labelCollaboratorName;
    @FXML
    private Label labelCollaborationId;

   /*  Professor professorData = new Professor();
    professorData = UserSessionManager.getInstance().getProfessorUserData();*/
    private void setValues(Collaboration collaboration){
        Professor professorData = new Professor();
        String professorUser = professorData.getUser();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelCollaborationId.setText(String.valueOf(collaboration.getCollaborationId()));
        labelCollaborationName.setText(collaboration.getCollaborationName());
        datePickerStartDate.setValue(collaboration.getStartDate());
        datePickerFinishDate.setValue(collaboration.getFinishDate());
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        labelCollaboratorName.setText(collaborationDAO.getCollaboratorNameById(collaboration.getCollaborationId(), professorDAO.getProfessorIdByUser(professorUser)));

    }

    @FXML
    private Label labelCollaborationStatus;
    @FXML
    private Circle circleCollaborationStatus;
    
    @FXML
    private void closeCollaboration(ActionEvent event){
        Alert confirmClose = new Alert(AlertType.CONFIRMATION);
        confirmClose.setHeaderText("Confirmar cierre");
        confirmClose.setTitle("Confirmar cierre");
        confirmClose.setContentText("¿Está seguro de que desea cerrar esta colaboración?");
        confirmClose.show();
        ButtonType acceptClose = new ButtonType("Aceptar");
        ButtonType cancelClose = new ButtonType("Cancelar");
        confirmClose.getButtonTypes().setAll(acceptClose, cancelClose);
        Button okButton = (Button) confirmClose.getDialogPane().lookupButton(acceptClose);
        Button cancelButton = (Button) confirmClose.getDialogPane().lookupButton(cancelClose);
        okButton.setOnAction(eventCloseCollaboration -> {
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            int collaborationId = Integer.parseInt(labelCollaborationId.getText());
            int result = collaborationDAO.changeCollaborationStatus("Cerrada", collaborationId);
            labelCollaborationStatus.setText("Cerrada");
            circleCollaborationStatus.setFill(Color.RED);
            if(result == 1){
                Alert closeSuccessfulAlert = new Alert(AlertType.INFORMATION);
                closeSuccessfulAlert.setTitle("Cierre exitoso");
                closeSuccessfulAlert.setHeaderText("Cierre exitoso");
                closeSuccessfulAlert.setContentText("La colaboración se ha cerrado exitosamente");
                closeSuccessfulAlert.show();
            }else{
                Alert closeErrorAlert = new Alert(AlertType.ERROR);
                closeErrorAlert.setTitle("Error conexión");
                closeErrorAlert.setHeaderText("Error conexión");
                closeErrorAlert.setContentText("No se pudo conectar a la base de datos, por favor inténtelo de nuevo más tarde");
            }
        });

        cancelButton.setOnAction(eventCancelCloseCollaboration -> {
            confirmClose.close();
        });
        




    }

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
    private Label labelUser;
    @FXML
    private void initialize(){
        Professor professorData = new Professor();
        professorData = UserSessionManager.getInstance().getProfessorUserData();
        labelUser.setText(professorData.getName());
        
        ProfessorDAO professorDAO = new ProfessorDAO();
        int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());

        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> activeCollaboration = collaborationDAO.searchCollaborationByStatusAndProfessorId("Activa", professorId);
        setValues(activeCollaboration.get(0));
        int collaborationId = activeCollaboration.get(0).getCollaborationId();
        
        labelCollaboratorName.setText(collaborationDAO.getCollaboratorNameById(collaborationId, professorId));
    }



}
