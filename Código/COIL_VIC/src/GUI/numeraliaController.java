/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dataAccess.DatabaseConnectionChecker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import log.Log;
import logic.RegionData;
import logic.AcademicAreaData;
import logic.SaveToFile;
import logic.classes.Admin;
/**
 *
 * @author daur0
 */
public class NumeraliaController {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(NumeraliaController.class);
    

    @FXML
    private Menu menuItem2024;
    
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
    private TableView<AcademicAreaData> academicAreaTable;

    @FXML
    private TableView<RegionData> regionTable;

    @FXML
    private Button buttonDownload;

    @FXML
    private Button buttonHome;

    @FXML
    private Button buttonCollaborations;

    @FXML
    private Button buttonProfessors;
    
    @FXML
    private Button buttonLogout;

    @FXML
    private Button buttonMinimize;

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonUniversities;

    @FXML
    private Label labelName;

    @FXML
    private Label labelNotConnection = new Label("No hay conexión con la base para recuperar la numeralia");

    private ObservableList<RegionData> regionDataList = FXCollections.observableArrayList();
    private ObservableList<AcademicAreaData> academicAreaDataList = FXCollections.observableArrayList();
    
    @FXML
    private void minimizeWindow(ActionEvent event){
        ChangeWindowManager.minimizeWindow(event);
    }

    @FXML
    private void closeWindow(ActionEvent event){
        ChangeWindowManager.closeWindow(event);
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
    private void handleYear2024(ActionEvent event) {
        loadDataByYear("2024");
        menuItem2024.setVisible(false);
        menuItem2024.setVisible(true);
    }
    
    @FXML
    public void initialize() {
        if(!DatabaseConnectionChecker.isDatabaseConnected()){
            DatabaseConnectionChecker.showNoConnectionDialog();
            buttonDownload.setDisable(true);
            academicAreaTable.setPlaceholder(labelNotConnection);
            regionTable.setPlaceholder(labelNotConnection);
        } else {
            loadDataByYear("2022");
            buttonDownload.setOnAction(this::handleDownloadButton);
            Admin adminData = new Admin();
            adminData = UserSessionManager.getInstance().getAdminUserData();
            labelName.setText(adminData.getAdminName());
        }
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
                Alert downloadNumeraliaAlert = new Alert(AlertType.INFORMATION);
                downloadNumeraliaAlert.setHeaderText("Descarga completada");
                downloadNumeraliaAlert.setContentText("Se ha descargado correctamente la numeralia");
                downloadNumeraliaAlert.show();

            } catch (IOException fileNotSavedException){
                LOG.error(fileNotSavedException);
            }
        }
    }

    @FXML
    private void gotoHome(ActionEvent event){
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/GUI/AdminHomeWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, homeLoader);
    }

    @FXML
    private void logout(ActionEvent event){
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/GUI/LoginWindow.fxml"));
        try {
            ChangeWindowManager.logout(event, loginLoader);
            UserSessionManager.getInstance().logoutAdmin();
        } catch (IOException ioException){
            LOG.error(ioException);
        }
    }

    @FXML
    private void goToUniversities(ActionEvent event){
        FXMLLoader universitiesOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminUniversityOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, universitiesOptionsLoader);
    }

    @FXML
    private void goToProfessors(ActionEvent event){
        FXMLLoader professorOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminProfessorOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, professorOptionsLoader);
    }

    @FXML
    private void goToCollaborations(ActionEvent event){
        FXMLLoader collaborationOptionsLoader = new FXMLLoader(getClass().getResource("/GUI/AdminCollaborationOptionsWindow.fxml"));
        ChangeWindowManager.changeWindowTo(event, collaborationOptionsLoader);
    }
}