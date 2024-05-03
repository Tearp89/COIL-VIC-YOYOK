package GUI;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.DAO.WorkshopDAO;
import logic.classes.Workshop;

public class AddWorkshopController {

    @FXML
    TextField textFieldWorkshopName;
    @FXML
    TextArea textAreaWorkshopRequierements;
    @FXML
    DatePicker datePickerStartDate;
    @FXML
    DatePicker datePickerFinishDate;
    
    @FXML
    void addWorkshop(ActionEvent event){
        WorkshopDAO workshopDAO = new WorkshopDAO();
        Workshop workshop = new Workshop();
        String workshopName = textFieldWorkshopName.getText();
        String workshopRequierements = textAreaWorkshopRequierements.getText();
        LocalDate startDate = datePickerStartDate.getValue();
        LocalDate finishDate = datePickerFinishDate.getValue();

        workshop.setWorkshopName(workshopName);
        workshop.setRequirements(workshopRequierements);
        workshop.setStartDate(startDate);
        workshop.setFinishDate(finishDate);

        workshopDAO.addWorkshop(workshop);

    }

    @FXML
    void cancel(ActionEvent event){

    }
}
