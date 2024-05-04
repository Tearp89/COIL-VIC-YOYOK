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
public class numeraliaController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(numeraliaController.class);

    @FXML
    private Menu menuItem2017;
    
    @FXML
    private Menu menuItem2018;
    
    @FXML
    private Menu menuItem2019;
    
    @FXML
    private Menu menuItem2020;
    
    @FXML
    private Menu menuItem2021;
    
    @FXML
    private Menu menuItem2022;
    
    @FXML
    private Menu menuItem2023;
    
    
    @FXML
    private TableView<CollaborationStatsDAO> tableView;
    @FXML
    private TableView<RegionData> regionTable;
    
    @FXML
    private void handleYear2017(ActionEvent event) {
        loadDataByYear("2017");
        menuItem2017.setVisible(false);
        menuItem2017.setVisible(true);
    }

    @FXML
    private void handleYear2018(ActionEvent event) {
        loadDataByYear("2018");
        menuItem2018.setVisible(false);
        menuItem2018.setVisible(true);
    }

    @FXML
    private void handleYear2019(ActionEvent event) {
        loadDataByYear("2019");
        menuItem2019.setVisible(false);
        menuItem2019.setVisible(true);
    }

    @FXML
    private void handleYear2020(ActionEvent event) {
        loadDataByYear("2020");
        menuItem2020.setVisible(false);
        menuItem2020.setVisible(true);
    }

    @FXML
    private void handleYear2021(ActionEvent event) {
        loadDataByYear("2021");
        menuItem2021.setVisible(false);
        menuItem2021.setVisible(true);
    }

    @FXML
    private void handleYear2022(ActionEvent event) {
        loadDataByYear("2022");
        menuItem2022.setVisible(false);
        menuItem2022.setVisible(true);
    }

    @FXML
    private void handleYear2023(ActionEvent event) {
        loadDataByYear("2023");
        menuItem2023.setVisible(false);
        menuItem2023.setVisible(true);
    }
    
    @FXML
    
    
    private ObservableList<RegionData> regionDataList = FXCollections.observableArrayList();
    private ObservableList<AcademicAreaData> academicAreaDataList = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        loadDataByYear("2022");

        download.setOnAction(this::handleDownloadButton);
    }
    
    private void loadDataByYear(String year) {
        regionDataList.clear();
        academicAreaDataList.clear();
        
        ArrayList<RegionData> regionList = new ArrayList<>();
        
        regionList = RegionData.getUsers(year);
        
        for(RegionData region : regionList){
            regionDataList.add(region);
        }
        regionTable.setItems(regionDataList);
        regionTable.requestLayout();
        
        ArrayList<AcademicAreaData> areaList = new ArrayList<>();
        
        areaList = AcademicAreaData.getUsers(year);
        
        for(AcademicAreaData area : areaList){
            academicAreaDataList.add(area);
        }
        academicAreaTable.setItems(academicAreaDataList);
        academicAreaTable.requestLayout();
    }

    
    @FXML
    private void handleYearMenu(ActionEvent event) {
        MenuItem selectedMenuItem = (MenuItem) event.getSource();
        String selectedYear = selectedMenuItem.getText();
        System.out.println("Año seleccionado: " + selectedYear);
        loadDataByYear(selectedYear);
    }
    
    @FXML
    private void handleDownloadButton(ActionEvent event) {
        exportTablesToExcel();
    }

    private void exportTablesToExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));

        File file = fileChooser.showSaveDialog(regionTable.getScene().getWindow());

        if (file != null) {
            try {
                SaveToFile.exportTwoTablesToExcel(regionTable, "Tabla de Región", academicAreaTable, "Tabla por Área Academica", file.getAbsolutePath());
            } catch (IOException notsavedException){
                LOG.error(notsavedException);
            }
        }
    }

}