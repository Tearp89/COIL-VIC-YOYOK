/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.DAO.CollaborationStatsDAO;

/**
 *
 * @author marla
 */
public class NumeraliaController {
    @FXML
    private TableView<CollaborationStatsDAO> tableView;
    @FXML
    private TableColumn<CollaborationStatsDAO, String> region;
    @FXML
    
    
    public void initialize(){
        region.setCellValueFactory(new PropertyValueFactory<>("Región"));
        
        tableView.getItems().get(0).countProfessorsByAcademicArea("Veracruz");
            

    }
}
