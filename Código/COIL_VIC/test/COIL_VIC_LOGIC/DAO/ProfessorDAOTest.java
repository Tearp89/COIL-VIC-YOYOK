/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import COIL_VIC_LOGIC.Classes.Professor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class ProfessorDAOTest {
    /**
     * Test of addProfessor method, of class ProfessorDAO.
     */
    @org.junit.Test
    public void testAddProfessor() {
        System.out.println("addProfessor");
        Professor professor = null;
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 0;
        int result = instance.addProfessor(professor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteProfessor method, of class ProfessorDAO.
     */
    @org.junit.Test
    public void testDeleteProfessor() {
        System.out.println("deleteProfessor");
        Professor professor = null;
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 0;
        int result = instance.deleteProfessor(professor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateProfessor method, of class ProfessorDAO.
     */
    @org.junit.Test
    public void testUpdateProfessor() {
        System.out.println("updateProfessor");
        Professor professor = null;
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 0;
        int result = instance.updateProfessor(professor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
