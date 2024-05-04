/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import log.Log;
import logic.RegionData;
import logic.AcademicAreaData;
import logic.SaveToFile;
/**
 *
 * @author daur0
 */
public class NumeraliaController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(SaveToFile.class);


    @FXML
    private TableView<AcademicAreaData> academicAreaTable;

    @FXML
    private TableView<RegionData> regionTable;
    
    @FXML
    private Menu year2017;

    @FXML
    private Menu year2018;

    @FXML
    private Menu year2019;

    @FXML
    private Menu year2020;

    @FXML
    private Menu year2021;

    @FXML
    private Menu year2022;

    @FXML
    private Menu year2023;

    @FXML
    private MenuBar yearMenuBar;
    
    @FXML
    private Button download;
    
    
    private ObservableList<RegionData> regionDataList = FXCollections.observableArrayList();
    private ObservableList<AcademicAreaData> academicAreaDataList = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        loadDataByYear("2022");

        for (Menu menu : new Menu[]{year2017, year2018, year2019, year2020, year2021, year2022, year2023}) {
            menu.setOnAction(this::handleYearMenu);
        }
        download.setOnAction(this::handleDownloadButton);
    }
    
    private void loadDataByYear(String year) {
        
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
        String selectedYear = selectedMenuItem.getId().replace("year", "");
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