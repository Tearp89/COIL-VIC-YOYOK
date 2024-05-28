
package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;

import dataAccess.DatabaseConnectionChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.DAO.AdminDAO;
import logic.DAO.LoginDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Admin;
import logic.classes.Professor;
import logic.classes.Student;
import log.Log;

public class LoginController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(LoginController.class);
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private TextField textFieldUser;
    
    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Button buttonAddProfessor;

    @FXML
    private Button buttonLogin;

    @FXML
    void login( ActionEvent e) {
        LoginDAO instance = new LoginDAO();
        String user = textFieldUser.getText();
        String password = textFieldPassword.getText();
        
        if(instance.validateAdmin(user, password)) {
            Admin adminData = new Admin();
            AdminDAO adminDAOInstance = new AdminDAO();
            adminData.setAdminUser(user);
            adminData.setPassword(password);
            adminData.setAdminName(adminDAOInstance.getAdminNameByUser(user));
            
            UserSessionManager.getInstance().loginAdmin(adminData);
            Node source = (Node) e.getSource();
            stage = (Stage) source.getScene().getWindow();
            stage.close();
            

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/adminHome.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                
                Stage newStage = new Stage();
                newStage.initStyle(StageStyle.TRANSPARENT);
                newStage.setScene(scene);
                newStage.show();

            } catch (IOException ex) {
                LOG.error(ex);
            }
        } else if(instance.validateProfessor(user, password)) {
            Professor professorData = new Professor();
            ProfessorDAO professorDAO = new ProfessorDAO();
            professorData.setUser(user);
            professorData.setPassword(password);
            professorData.setName(professorDAO.getProfessorNameByUser(user));
            String status = professorDAO.getProfessorStatusByUser(user);
            if(status.equals("Aceptado")){
                UserSessionManager.getInstance().loginProfessor(professorData);
                Node source = (Node) e.getSource();
                stage = (Stage) source.getScene().getWindow();
                stage.close();
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/professorHome.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage newStage = new Stage();
                    newStage.initStyle(StageStyle.TRANSPARENT);
                    newStage.setScene(scene);
                    newStage.show();
                } catch (IOException loaderException){
                    LOG.error("ERROR:", loaderException);
                }
            } else if(status.equals("Pendiente")){
                Alert professorNotAccepted = new Alert(AlertType.INFORMATION);
                professorNotAccepted.setHeaderText("Aún no ha sido aceptado");
                professorNotAccepted.setTitle("No disponible");
                professorNotAccepted.setContentText("Por el momento no ha sido aceptado en el sistema");
                professorNotAccepted.show();
            } 
            

        } else if (instance.validateStudent(user, password)){
            Student studentData = new Student();
            studentData.setEmail(user);
            studentData.setPassword(password);
            UserSessionManager.getInstance().loginStudent(studentData);
            Node source = (Node) e.getSource();
            stage = (Stage) source.getScene().getWindow();
            stage.close();
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/studentsHome.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);

                Stage newStage = new Stage();
                newStage.initStyle(StageStyle.TRANSPARENT);
                newStage.setScene(scene);
                newStage.show();
            } catch (IOException loaderException){
                LOG.error("ERROR:", loaderException);
            }


        } else {
            Alert userNotInDatabase = new Alert(AlertType.INFORMATION);
            userNotInDatabase.setHeaderText("No existe en el sistema");
            userNotInDatabase.setTitle("No hay existencia del usuario");
            userNotInDatabase.setContentText("No hay un registro de su cuenta en el sistema");
            userNotInDatabase.show();
        }
    }

    @FXML
    void addProfessor(ActionEvent event){
        FXMLLoader addProfessorLoader = new FXMLLoader(getClass().getResource("/GUI/addProfessor.fxml"));
        ChangeWindowManager.changeWindowTo(event, addProfessorLoader);
    }

    @FXML
    void initialize(){
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog(stage);
            buttonAddProfessor.setDisable(true);
            buttonLogin.setDisable(true);
        }
    }
}
