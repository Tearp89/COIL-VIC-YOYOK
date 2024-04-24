/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author marla
 */


public class MainAddCollaboration extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
     
       
       Parent root = FXMLLoader.load(getClass().getResource("/GUI/addCollaboration.fxml"));


        
    
        Scene scene = new Scene(root);
        

        primaryStage.setScene(scene);
        

        primaryStage.show();
        primaryStage.setOnShown(event -> primaryStage.centerOnScreen());
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
