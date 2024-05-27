
package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.DAO.AdminDAO;
import logic.DAO.LoginDAO;
import logic.DAO.ProfessorDAO;
import logic.DAO.StudentDAO;
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

        } else if (instance.validateStudent(user, password)){
            Student studentData = new Student();
            StudentDAO studentDAO = new StudentDAO();
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


        }
    }
}
