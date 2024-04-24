/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAO.LoginDAO;

public class loginController {
    

   
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

            stage.close();
            

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/addCollaboration.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root);
                

                Stage newStage = new Stage();
                newStage.setScene(scene);
                

                newStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
