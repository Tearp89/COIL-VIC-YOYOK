package logic.classes;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ProfessorAcceptedAlert extends Alert{
    public ProfessorAcceptedAlert (AlertType alertType){
        super(alertType);
        Stage stage = (Stage) this.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("@../../../gui/descarga-removebg-preview.png"));
        this.setHeaderText("Académico aceptado");
        this.setContentText("Académico aceptado exitosamente");
        this.getButtonTypes().add(new ButtonType("Aceptar"));

    }

}
