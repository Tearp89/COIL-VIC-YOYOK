/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Desktop;
import java.io.File;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.*;

import dataAccess.DatabaseManager;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import log.Log;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author daur0
 */
public class SaveDAO {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(CollaborationDAO.class);
    
    public void exportExcel(JTable t) throws IOException {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setDialogTitle("Guardar archivo");
        jFileChooser.setAcceptAllFileFilterUsed(false);
        if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = jFileChooser.getSelectedFile().toString().concat(".xls");
            try {
                File excelFile = new File(ruta);
                if (excelFile.exists()) {
                    excelFile.delete();
                }
                excelFile.createNewFile();
                Workbook workbook = new HSSFWorkbook();
                try (FileOutputStream file = new FileOutputStream(excelFile)) {
                    Sheet sheet = workbook.createSheet("Mi hoja de trabajo 1");
                    sheet.setDisplayGridlines(false);
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row row = sheet.createRow(f);
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            Cell cell = row.createCell(c);
                            if (f == 0) {
                                cell.setCellValue(t.getColumnName(c));
                            }
                        }
                    }
                    int startRow = 1;
                    for (int f = 0; f < t.getRowCount(); f++) {
                        Row row = sheet.createRow(startRow);
                        startRow++;
                        for (int c = 0; c < t.getColumnCount(); c++) {
                            Cell cell = row.createCell(c);
                            if (t.getValueAt(f, c) instanceof Double) {
                                cell.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                            } else if (t.getValueAt(f, c) instanceof Float) {
                                cell.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                            } else {
                                cell.setCellValue(String.valueOf(t.getValueAt(f, c)));
                            }
                        }
                    }
                    workbook.write(file);
                }
                Desktop.getDesktop().open(excelFile);
            } catch (IOException | NumberFormatException e) {
                LOG.error("ERROR: ", e);
            }
        }
    }
}
