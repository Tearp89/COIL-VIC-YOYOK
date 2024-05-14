package GUI;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;

public class SearchAcceptedCollaborationsController {

    @FXML
    private TableView<Collaboration> tableViewAcceptedCollaborations;
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
    private String user;
    @FXML
    Label labelCollaborationNotFound = new Label("No se encontraron colaboraciones");


    public void loadAcceptedCollaborations(){
        CollaborationDAO acceptedCollaborations = new CollaborationDAO();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        ProfessorDAO professroDAO = new ProfessorDAO();
        int idProfesor = professroDAO.getProfessorIdByUser(user);
        collaborations = acceptedCollaborations.searchCollaborationByStatusAndProfessorId("Aceptada", 24);
        tableViewAcceptedCollaborations.getItems().addAll(collaborations);
        if(collaborations.size() == 0){
            tableViewAcceptedCollaborations.setPlaceholder(labelCollaborationNotFound);
        }
        
    }

    @FXML
    private TextField textFieldSearch;
        public void searchAcceptedCollaborations(ActionEvent event){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> activeCollaborations = new ArrayList<>();
        String collaborationName = "%" + textFieldSearch.getText() + "%";
        activeCollaborations = collaborationDAO.searchCollaborationByStatusAndName("Activa", collaborationName);
        tableViewAcceptedCollaborations.getItems().clear();
        tableViewAcceptedCollaborations.getItems().addAll(activeCollaborations);
        if(activeCollaborations.size() == 0){
            tableViewAcceptedCollaborations.setPlaceholder(labelCollaborationNotFound);
        }
        

    }

    @FXML
    private void initialize(){
        loadAcceptedCollaborations();
        tableViewAcceptedCollaborations.setOnMouseClicked(event -> {
            if(event.getClickCount() == 1){
                Collaboration acceptedCollaboration = tableViewAcceptedCollaborations.getSelectionModel().getSelectedItem();
                if(acceptedCollaboration != null){
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/acceptedCollaborationDetails.fxml"));
                        Parent root = loader.load();
                        AcceptedCollaborationDetailsController controller = loader.getController();
                        controller.initialize(acceptedCollaboration.getCollaborationId());
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                        
                    } catch (IOException e){
                        //TODO: Agregar bitácora
                    }
                }
            }
        });
    }




}
