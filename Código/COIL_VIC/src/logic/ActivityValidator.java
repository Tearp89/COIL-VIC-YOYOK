package logic;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import logic.classes.Activity;

public class ActivityValidator {

    public static boolean validateActivityFields(Activity activity){
        String title = activity.getTitle();
        String week = activity.getWeek();
        String type = activity.getType();
        String description = activity.getDescription();
        if(!FieldValidator.onlyText(title) || !FieldValidator.onlyNumber(week) || !FieldValidator.onlyText(type) || !FieldValidator.onlyTextAndNumbers(description)){
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos vacíos o incorrectos");
            emptyFieldsAlert.setHeaderText("Campos vacíos o incorrectos");
            emptyFieldsAlert.setContentText("No se puede agregar la actividad hay campos vacios o incorrectos");
            emptyFieldsAlert.show();
            return false;
        }
        
        return true;
    }

    public static void showAlerts(int result, Button buttonAssign){
        if (result > 0){
            Alert assignConfirmationAlert = new Alert(AlertType.INFORMATION);
            assignConfirmationAlert.setTitle("Actividad asignada");
            assignConfirmationAlert.setHeaderText("Actividad asignada");
            assignConfirmationAlert.setContentText("Se asignó la actividad exitosamente");
            assignConfirmationAlert.show();
            buttonAssign.setDisable(true);
        } else{
            Alert assignErrorAlert = new Alert(AlertType.ERROR);
            assignErrorAlert.setTitle("Error conexión");
            assignErrorAlert.setHeaderText("Error conexión");
            assignErrorAlert.setContentText("Se perdió la conexión a la base de datos, inténtelo de nuevo más tarde");
            assignErrorAlert.show();
        }
    }

    
    

    
    

}
