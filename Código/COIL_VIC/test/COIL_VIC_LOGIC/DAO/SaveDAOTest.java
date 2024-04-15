/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAO;

import java.io.IOException;
import javax.swing.JTable;
import org.junit.Test;

import logic.DAO.SaveDAO;

import static org.junit.Assert.*;

/**
 *
 * @author daur0
 */
public class SaveDAOTest {

    /**
     * Test of exportExcel method, of class SaveDAO.
     */
    @Test
    public void testExportExcel() throws IOException {
        JTable table = new JTable(new Object[][]{{"a", "b", "c"}, {1, 2, 3}}, new Object[]{"Col1", "Col2", "Col3"});
        SaveDAO exporter = new SaveDAO();

        try {
            exporter.exportExcel(table);

            assertTrue(true);
        } catch (IOException e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }
    
}
