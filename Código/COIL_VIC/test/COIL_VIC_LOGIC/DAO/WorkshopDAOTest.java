/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import COIL_VIC_LOGIC.Classes.Collaboration;
import COIL_VIC_LOGIC.Classes.Workshop;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class WorkshopDAOTest {
    /**
     * Test of addWorkshop method, of class WorkshopDAO.
     */
    @Test
    public void testAddWorkshop(){
        Workshop workshop = new Workshop();
        WorkshopDAO instance = new WorkshopDAO();
        LocalDate dateStartTest = LocalDate.of(2000, 3, 1);
        LocalDate dateFinishtTest = LocalDate.of(2000, 1, 1);
        
        workshop.setWorkshopName("CT-Test");
        workshop.setRequirements("RequisitosTest");
        workshop.setStartDate(dateStartTest);
        workshop.setFinishDate(dateFinishtTest);
        
        
        int rowsAffected = instance.addWorkshop(workshop);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of deleteWorkshop method, of class WorkshopDAO.
     */
    @org.junit.Test
    public void testDeleteWorkshop() {
        Workshop workshop = new Workshop();
        WorkshopDAO instance = new WorkshopDAO();
        int expectedResult = 1;
        workshop.setWorkshopId(2);
        
        
        int rowsAffected = instance.deleteWorkshop(workshop);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of updateWorkshop method, of class WorkshopDAO.
     */
    @org.junit.Test
    public void testUpdateWorkshop() {
        Workshop workshop = new Workshop();
        WorkshopDAO instance = new WorkshopDAO();
        LocalDate dateStartTest =LocalDate.of(2000, 3, 1);
        LocalDate dateFinishtTest = LocalDate.of(2000, 2, 2);
        

        workshop.setWorkshopName("NuevoNombreCT-Test");
        workshop.setRequirements("NuevosRequisitosTest");
        workshop.setStartDate(dateStartTest);
        workshop.setFinishDate(dateFinishtTest);
        workshop.setWorkshopId(3);
        
        
        int rowsAffected = instance.updateWorkshop(workshop);
        assertEquals(1, rowsAffected);
    }
    
    @Test
    public void testSearchWorkshop(){
        int expectedResult = 1;
        WorkshopDAO instance = new WorkshopDAO();
        int workshopId = 3;
        ArrayList<Workshop> workshops = instance.searchWorkshop(workshopId);
        assertEquals(expectedResult, workshops.size());
    }
}
