/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import COIL_VIC_LOGIC.Classes.Facilitator;
import COIL_VIC_LOGIC.DAO.FacilitatorDAO;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class FacilitatorDAOTest {
     
    @Test
    public void testAddFcilitator() {
        Facilitator facilitator = new Facilitator();
        FacilitatorDAO facilitadorDAO = new FacilitatorDAO();
        
        facilitator.setFacilitatorId("FacilitadorIDTest");
        facilitator.setFacilitatorName("NombreFacilitadorTest");
        facilitator.setWorkShopId("CursoIDTest");
        
        int rowsAffected = facilitadorDAO.addFcilitator(facilitator);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of deleteFacilitator method, of class FacilitatorDAO.
     */
    @Test
    public void testDeleteFacilitator() {
        Facilitator facilitator = new Facilitator();
        FacilitatorDAO facilitadorDAO = new FacilitatorDAO();
        
        facilitator.setFacilitatorId("FacilitadorIDTest");
        facilitator.setFacilitatorName("NombreFacilitadorTest");
        facilitator.setWorkShopId("CursoIDTest");
        
        int rowsAffected = facilitadorDAO.deleteFacilitator(facilitator);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of updateFacilitator method, of class FacilitatorDAO.
     */
    @Test
    public void testUpdateFacilitator() {
        Facilitator facilitator = new Facilitator();
        FacilitatorDAO facilitadorDAO = new FacilitatorDAO();
        
        facilitator.setFacilitatorId("FacilitadorIDTest");
        facilitator.setFacilitatorName("NuevoNombreFacilitadorTest");
        facilitator.setWorkShopId("CursoIDTest");
        
        int rowsAffected = facilitadorDAO.updateFacilitator(facilitator);
        assertEquals(1, rowsAffected);
    }
    
}
