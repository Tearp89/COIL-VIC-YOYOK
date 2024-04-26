package GUI;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import logic.DAO.ProfessorDAO;
import logic.classes.Professor;
import logic.Access;

public class AddProfessorController {

    @FXML
    private TextField textFieldProfessorName;
    @FXML
    private ComboBox comboBoxUniversity;
    @FXML
    private ComboBox comboBoxAcademicArea;
    @FXML 
    private TextField textFieldEmail;


    @FXML
    void addProfessor(ActionEvent event){
        ProfessorDAO professorDAO = new ProfessorDAO();
        Professor professor = new Professor();
        String professorName = textFieldProfessorName.getText();
        Object selectedUniversity = comboBoxUniversity.getSelectionModel().getSelectedItem();
        String university = selectedUniversity != null ? selectedUniversity.toString() : null;
        String user = userGenerator(professorName);
        String password = passwordGenerator(8);
    }

    @FXML
    void cancel(ActionEvent event){

    }

    @FXML
    void initialize(){

    }
}
