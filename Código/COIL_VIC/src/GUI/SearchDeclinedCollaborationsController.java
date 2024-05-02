package GUI;

import java.sql.SQLDataException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import logic.DAO.CollaborationDAO;
import logic.classes.Collaboration;

public class SearchDeclinedCollaborationsController {

    @FXML
    private TableView<Collaboration> tableViewDeclinedCollaborations;
    @FXML
    private TableColumn<Collaboration, String> tableColumnCollaborationId;
    @FXML
    private TableColumn<Collaboration, String> tableColumnCollaborationName;
    @FXML
    private TableColumn<Collaboration, String> tableColumnStartDate;
    @FXML
    private TableColumn<Collaboration, String> tableColumnFinishDate;
    @FXML
    private TableColumn<Collaboration, String> tableColumnStatus;
    @FXML
    private TextField textFieldSearch; 

  

    public void loadDeclinedCollaboration(){
        CollaborationDAO declinedCollaborations = new CollaborationDAO();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        try{
            collaborations = declinedCollaborations.searchCollaborationByStatus("Rechazada");
        }catch(Exception loadDeclinedCollaborationsException){
            loadDeclinedCollaborationsException.printStackTrace();
        }

        tableViewDeclinedCollaborations.getItems().addAll(collaborations);

    }

    public void searchDeclinedCollaborations(ActionEvent event){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String collaborationName = "%" + textFieldSearch.getText() + "%";
        collaborations = collaborationDAO.searchCollaborationByStatusAndName("Rechazada", collaborationName);
        tableViewDeclinedCollaborations.getItems().clear();
        tableViewDeclinedCollaborations.getItems().addAll(collaborations);
    }

    @FXML
    private void initialize(){
        loadDeclinedCollaboration();

    }

}
