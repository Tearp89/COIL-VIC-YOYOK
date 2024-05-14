
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
import logic.classes.Admin;
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
        }
    }
}
