/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import COIL_VIC_LOGIC.Classes.Collaboration;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.ResultSet;

/**
 *
 * @author Daniel
 */
public class CollaborationDAOTest {
    
    public CollaborationDAOTest() {
    }

    /**
     * Test of addCollaboration method, of class CollaborationDAO.
     */
    @org.junit.Test
    public void testAddCollaboration() {
        Collaboration collaboration = new Collaboration();
        CollaborationDAO instance = new CollaborationDAO();
        
        LocalDate dateStartTest =  LocalDate.of(2000, 1, 1); 
        LocalDate dateFinishTest =  LocalDate.of(2000, 2, 1);
        
        collaboration.setCollaborationName("TestNombreCol");
        collaboration.setDescription("TestDescripcion");
        collaboration.setFinishDate(dateFinishTest);
        collaboration.setStartDate(dateStartTest);
        
        
        int rowsAffected = instance.addCollaboration(collaboration);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of deleteCollaboration method, of class CollaborationDAO.
     */
    @org.junit.Test
    public void testDeleteCollaboration() {
        Collaboration collaboration = new Collaboration();
        CollaborationDAO instance = new CollaborationDAO();
        
        LocalDate dateStartTest = LocalDate.of(2000, 3, 1);
        LocalDate dateFinishtTest = LocalDate.of(2000, 1, 1);
        
        collaboration.setCollaborationName("TestNombreCol");
        collaboration.setDescription("TestDescripcion");
        collaboration.setFinishDate(dateFinishtTest);
        collaboration.setStartDate(dateStartTest);
        collaboration.setCollaborationId(2);
        
        
        int rowsAffected = instance.deleteCollaboration(collaboration);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of updateCollaboration method, of class CollaborationDAO.
     */
    @org.junit.Test
    public void testUpdateCollaboration() {
        Collaboration collaboration = new Collaboration();
        CollaborationDAO instance = new CollaborationDAO();
        
        LocalDate dateStartTest = LocalDate.of(2000, 1, 1);
        LocalDate dateFinishtTest = LocalDate.of(2000, 2, 2);
        
        collaboration.setCollaborationName("NuevoTestNombreCol");
        collaboration.setDescription("NuevoTestDescripcion");
        collaboration.setFinishDate(dateFinishtTest);
        collaboration.setStartDate(dateStartTest);
        collaboration.setCollaborationId(1);
        
        int rowsAffected = instance.updateCollaboration(collaboration);
        assertEquals(1, rowsAffected);
    }
    
    
    @Test
    public void testSearchCollaboration(){
        int expectedResult = 4;
        CollaborationDAO instance = new CollaborationDAO();
        String name = "TestNombreCol";
        ArrayList<Collaboration> collaborations = instance.searchCollaboration(name);
        assertEquals(expectedResult, collaborations.size());
    }
    
}
