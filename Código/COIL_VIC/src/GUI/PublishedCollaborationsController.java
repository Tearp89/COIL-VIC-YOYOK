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
import log.Log;
import logic.DAO.CollaborationDAO;
import logic.classes.Collaboration;

public class PublishedCollaborationsController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(PublishedCollaborationsController.class);

    @FXML
    private TableView<Collaboration> tableViewPublishedCollaborations;
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
    Label labelCollaborationNotFound = new Label("No se encontraron colaboraciones");

    public void loadPublishedCollaborations(){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> publishedCollaborations = new ArrayList<>();
        publishedCollaborations = collaborationDAO.searchCollaborationByStatus("Publicada");
        tableViewPublishedCollaborations.getItems().addAll(publishedCollaborations);
        if(publishedCollaborations.size() == 0){
            tableViewPublishedCollaborations.setPlaceholder(labelCollaborationNotFound);
        }
    }

    @FXML
    private TextField textFieldSearch;
    public void searchPublishedCollaborations(ActionEvent event){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> publishedCollaborations = new ArrayList<>();
        String collaborationName = "%" + textFieldSearch.getText() + "%";
        publishedCollaborations = collaborationDAO.searchCollaborationByStatusAndName("Publicada", collaborationName);
        tableViewPublishedCollaborations.getItems().clear();
        tableViewPublishedCollaborations.getItems().addAll(publishedCollaborations);
        if(publishedCollaborations.size() == 0){
            tableViewPublishedCollaborations.setPlaceholder(labelCollaborationNotFound);
        }

    }

    @FXML
    private void initialize(){
        loadPublishedCollaborations();
        tableViewPublishedCollaborations.setOnMouseClicked(event ->{
            if(event.getClickCount() == 1){
                Collaboration publishedCollaboration = tableViewPublishedCollaborations.getSelectionModel().getSelectedItem();
                if(publishedCollaboration != null){
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/PublishedCollaborationsDetails.fxml"));
                        Parent root = loader.load();
                        PublishedCollaborationsDetailsController controller = loader.getController();
                        controller.initialize(publishedCollaboration.getCollaborationId());
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();

                    }catch (IOException e){
                        LOG.error("ERROR:", e);
                    }
                }
            }
        });
    }

}
