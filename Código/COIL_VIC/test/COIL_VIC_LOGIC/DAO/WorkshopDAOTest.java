/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import COIL_VIC_LOGIC.Classes.Workshop;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;

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
        Date dateStartTest = new Date(2000, 0, 1);
        Date dateFinishtTest = new Date(2000, 1, 1);
        
        workshop.setWorkshopId("CU-TEST");
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
        Date dateStartTest = new Date(2000, 0, 1);
        Date dateFinishtTest = new Date(2000, 1, 1);
        
        workshop.setWorkshopId("CU-TEST");
        workshop.setWorkshopName("CT-Test");
        workshop.setRequirements("RequisitosTest");
        workshop.setStartDate(dateStartTest);
        workshop.setFinishDate(dateFinishtTest);
        
        
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
        Date dateStartTest = new Date(2000, 0, 1);
        Date dateFinishtTest = new Date(2000, 2, 2);
        
        workshop.setWorkshopId("CU-TEST");
        workshop.setWorkshopName("NuevoNombreCT-Test");
        workshop.setRequirements("NuevosRequisitosTest");
        workshop.setStartDate(dateStartTest);
        workshop.setFinishDate(dateFinishtTest);
        
        
        int rowsAffected = instance.addWorkshop(workshop);
        assertEquals(1, rowsAffected);
    }
    
}
