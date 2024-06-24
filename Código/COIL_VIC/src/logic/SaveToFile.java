/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import log.Log;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


/**
 *
 * @author daur0
 */
public class SaveToFile {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(SaveToFile.class);

    
    public static <T> void exportToExcel(TableView<T> tableView, String sheetName, String filePath) throws IOException {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet(sheetName);

            ObservableList<TableColumn<T, ?>> columns = tableView.getColumns();

            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < columns.size(); i++) {
                headerRow.createCell(i).setCellValue(columns.get(i).getText());
            }

            ObservableList<T> items = tableView.getItems();

            for (int rowIdx = 0; rowIdx < items.size(); rowIdx++) {

                Row row = sheet.createRow(rowIdx + 1);
                T item = items.get(rowIdx);

                for (int colIdx = 0; colIdx < columns.size(); colIdx++) {

                    Object cellValue = columns.get(colIdx).getCellData(item);

                    if (cellValue != null) {
                        row.createCell(colIdx).setCellValue(cellValue.toString());
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }catch (FileNotFoundException e) {
                LOG.error("El archivo no pudo ser encontrado o no se pudo crear: " + e.getMessage());
            } catch (IOException e) {
                LOG.error("Error de entrada/salida al escribir en el archivo: " + e.getMessage());
            }
        }
    }
    
    public static <T, D> void exportTwoTablesToExcel(TableView<T> tableView1, String sheetName1, TableView<D> tableView2, String sheetName2, String filePath) throws IOException {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet1 = workbook.createSheet(sheetName1);

            ObservableList<TableColumn<T, ?>> columns1 = tableView1.getColumns();

            Row headerRow1 = sheet1.createRow(0);

            for (int i = 0; i < columns1.size(); i++) {
                headerRow1.createCell(i).setCellValue(columns1.get(i).getText());
            }

            ObservableList<T> items1 = tableView1.getItems();

            for (int rowIdx = 0; rowIdx < items1.size(); rowIdx++) {

                Row row = sheet1.createRow(rowIdx + 1);
                T item = items1.get(rowIdx);

                for (int colIdx = 0; colIdx < columns1.size(); colIdx++) {

                    Object cellValue = columns1.get(colIdx).getCellData(item);

                    if (cellValue != null) {
                        row.createCell(colIdx).setCellValue(cellValue.toString());
                    }
                }
            }
            
            Sheet sheet2 = workbook.createSheet(sheetName2);

            ObservableList<TableColumn<D, ?>> columns2 = tableView2.getColumns();

            Row headerRow = sheet2.createRow(0);

            for (int i = 0; i < columns2.size(); i++) {
                headerRow.createCell(i).setCellValue(columns2.get(i).getText());
            }

            ObservableList<D> items2 = tableView2.getItems();

            for (int rowIdx = 0; rowIdx < items2.size(); rowIdx++) {

                Row row = sheet2.createRow(rowIdx + 1);
                D item = items2.get(rowIdx);

                for (int colIdx = 0; colIdx < columns2.size(); colIdx++) {

                    Object cellValue = columns2.get(colIdx).getCellData(item);

                    if (cellValue != null) {
                        row.createCell(colIdx).setCellValue(cellValue.toString());
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            } catch (FileNotFoundException e) {
                LOG.error("El archivo no pudo ser encontrado o no se pudo crear: " + e.getMessage());
            } catch (IOException e) {
                LOG.error("Error de entrada/salida al escribir en el archivo: " + e.getMessage());
            }
        }
    }
}