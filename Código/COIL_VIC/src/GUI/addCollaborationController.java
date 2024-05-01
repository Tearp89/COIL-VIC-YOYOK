package GUI;

import java.awt.event.MouseEvent;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.DAO.CollaborationDAO;
import logic.classes.Collaboration;

public class addCollaborationController {

    
    
    
    @FXML
    private TextField textFieldCollaborationName;
    @FXML
    private DatePicker datePickerStartDate;
    @FXML
    private DatePicker datePickerFinishDate;

    @FXML
    private TextArea textAreaCollaborationDescription;

    @FXML
    void addCollaboration(ActionEvent event) {
        CollaborationDAO instance = new CollaborationDAO();
        Collaboration collaboration = new Collaboration();
        String collaborationName = textFieldCollaborationName.getText();
        String collaborationDescription = textAreaCollaborationDescription.getText();
        LocalDate startDate = datePickerStartDate.getValue();
        LocalDate finishDate = datePickerFinishDate.getValue();

        
        collaboration.setCollaborationName(collaborationName);
        collaboration.setDescription(collaborationDescription);
        collaboration.setStartDate(startDate);
        collaboration.setFinishDate(finishDate);
        collaboration.setCollaborationStatus("En espera");
        
        instance.addCollaboration(collaboration);
        
    }

    @FXML
    void initialize() {
        

    }
}

