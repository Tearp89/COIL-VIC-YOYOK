/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import COIL_VIC_LOGIC.Classes.Collaboration;
import COIL_VIC_LOGIC.Classes.Professor;
import java.util.ArrayList;
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
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 1;
        professor.setName("Juan Díaz");
        professor.setStatus("Aceptado");
        professor.setType("Externo");
        professor.setCountry("México");
        professor.setUniversityId(2);
        
        int result = instance.addProfessor(professor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of deleteProfessor method, of class ProfessorDAO.
     */
    @org.junit.Test
    public void testDeleteProfessor() {
        System.out.println("deleteProfessor");
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 1;
        professor.setProfessorId(12);
        int result = instance.deleteProfessor(professor);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of updateProfessor method, of class ProfessorDAO.
     */
    @org.junit.Test
    public void testUpdateProfessor() {
        System.out.println("updateProfessor");
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 1;
        professor.setName("Lucas Perez");
        professor.setStatus("En espera");
        professor.setType("Externo");
        professor.setCountry("Alemania");
        professor.setUniversityId(2);{
        professor.setProfessorId(7);
    }
        int result = instance.updateProfessor(professor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
        @Test
        public void testSearchProfessor(){
        int expectedResult = 6;
        ProfessorDAO instance = new ProfessorDAO();
        int idUniversidad = 2;
        ArrayList<Professor> professors = instance.searchProfessor(idUniversidad);
        assertEquals(expectedResult, professors.size());
    }
}
