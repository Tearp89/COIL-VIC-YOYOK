/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import java.util.ArrayList;
import org.junit.Test;

import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

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
    
    
    @Test
    public void testAddProfessorFailed(){
         Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 0;
        professor.setName("Juan Díaz");
        professor.setStatus("Aceptado");
        professor.setType("Externo");
        professor.setCountry("México");
        professor.setUniversityId(4);
        
        int result = instance.addProfessor(professor);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteProfessor method, of class ProfessorDAO.
     */
    @org.junit.Test
    public void testDeleteProfessorSuccess() {
        System.out.println("deleteProfessor");
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 1;
        professor.setProfessorId(14);
        int result = instance.deleteProfessor(professor);
        assertEquals(expResult, result);
       
    }
    
    @Test
    public void testDeleteProfessorFailed(){
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expectedResult = 0;
        professor.setProfessorId(25);
        int result = instance.deleteProfessor(professor);
        assertEquals(expectedResult, result);
    }

    /**
     * Test of updateProfessor method, of class ProfessorDAO.
     */
    @org.junit.Test
    public void testUpdateProfessorSuccess() {
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
    public void updateProfessorFailedUniversity(){
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 0;
        professor.setName("Lucas Perez");
        professor.setStatus("En espera");
        professor.setType("Externo");
        professor.setCountry("Alemania");
        professor.setUniversityId(5);
        professor.setProfessorId(7);
        int result = instance.updateProfessor(professor);
        assertEquals(expResult, result);
    }
        @Test
        public void testSearchProfessorSuccess(){
        int expectedResult = 6;
        ProfessorDAO instance = new ProfessorDAO();
        int idUniversidad = 2;
        ArrayList<Professor> professors = instance.searchProfessor(idUniversidad);
        assertEquals(expectedResult, professors.size());
    }
        
        @Test
        public void testSearchProfessorFailed(){
            int expectedResult = 0;
            ProfessorDAO instance = new ProfessorDAO();
            int idUniversidad = 5;
            ArrayList<Professor> professors = instance.searchProfessor(idUniversidad);
            assertEquals(expectedResult, professors.size());
        }
    
        @Test
        public void testSearchProfessorByCountrySuccess(){
            int expectedResult = 3;
            ProfessorDAO instance = new ProfessorDAO();
            String country = "México";
            ArrayList<Professor> professors = instance.searchProfessorByCountry(country);
            assertEquals(expectedResult, professors.size());
        }
        
        @Test
        public void testSearchProfessorsByCountryFailed(){
            int expectedResult = 0;
            ProfessorDAO instance = new ProfessorDAO();
            String country = "Italia";
            ArrayList<Professor> professors = instance.searchProfessorByCountry(country);
            assertEquals(expectedResult, professors.size());
            
        }
        
        @Test
        public void testSearchProfessorByStatusFailed(){
            int expectedResult = 0;
            ProfessorDAO instance = new ProfessorDAO();
            String status = "Rechazado";
            ArrayList<Professor> professors = instance.searchProfessorByStatus(status);
            assertEquals(expectedResult, professors.size());
        }
        
        @Test
        public void testSearchProfessorByStatusSuccess(){
            int expectedResult = 3;
            ProfessorDAO instance = new ProfessorDAO();
            String status = "En espera";
            ArrayList<Professor> professors = instance.searchProfessorByStatus(status);
            assertEquals(expectedResult, professors.size());
        }
}
