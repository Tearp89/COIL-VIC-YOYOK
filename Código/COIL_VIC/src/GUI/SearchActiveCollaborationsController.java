package GUI;


import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import logic.DAO.CollaborationDAO;
import logic.classes.Collaboration;

public class SearchActiveCollaborationsController {

    @FXML
    private TableView<Collaboration> tableViewActiveCollaborations;
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
    @FXML Label labelCollaborationNotFound = new Label("No se encontraron colaboraciones");


    public void loadActiveCollaborations(){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> activeCollaborations = new ArrayList<>();
        activeCollaborations = collaborationDAO.searchCollaborationByStatus("Activa");
        tableViewActiveCollaborations.getItems().addAll(activeCollaborations);
        if (activeCollaborations.size() == 0){
            tableViewActiveCollaborations.setPlaceholder(labelCollaborationNotFound);
        }

    }

    @FXML
    private TextField textFieldSearch;

    public void searchActiveCollaborations(ActionEvent event){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> activeCollaborations = new ArrayList<>();
        String collaborationName = "%" + textFieldSearch.getText() + "%";
        activeCollaborations = collaborationDAO.searchCollaborationByStatusAndName("Activa", collaborationName);
        tableViewActiveCollaborations.getItems().clear();
        tableViewActiveCollaborations.getItems().addAll(activeCollaborations);
        if(activeCollaborations.size() == 0){
            tableViewActiveCollaborations.setPlaceholder(labelCollaborationNotFound);
        }
        

    }

    @FXML
    private void initialize(){
        loadActiveCollaborations();
        

        
    }

}
