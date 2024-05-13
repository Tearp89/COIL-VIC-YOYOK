/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAO;

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
     public void testAddProfessorUV() {
        System.out.println("addProfessorUV");
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 1;
        professor.setName("Jorge Alberto");
        professor.setStatus("Aceptado");
        professor.setPhoneNumber("2220000000");
        professor.setEmail("test2@email.com");
        professor.setCountry("México");
        professor.setUniversityId(1);
        professor.setType("UV");
        professor.setAcademicArea("Económico");
        professor.setPersonalNumber(222222);
        professor.setRegion("Xalapa");
        professor.setContractType("luegocheco");
        professor.setContractCategory("luegochecox2");
        professor.setDiscipline("luegochecox3");
        professor.setUser("Usuario de prueba uv1");
        professor.setPassword("testtest");
        
        int result = instance.addProfessorUV(professor);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAddProfessorUVFailed(){
         Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 0;
        professor.setName("Jorge Alberto");
        professor.setStatus("Aceptado");
        professor.setPhoneNumber("2220000000");
        professor.setEmail("test2@email.com");
        professor.setCountry("México");
        professor.setUniversityId(1);
        professor.setType("UV");
        professor.setAcademicArea("Económico");
        professor.setPersonalNumber(222222);
        professor.setRegion("Xalapa");
        professor.setContractType("luegocheco");
        professor.setContractCategory("luegochecox2");
        professor.setDiscipline("luegochecox3");
        professor.setUser("Usuario de prueba uv1");
        professor.setPassword("testtest");
        
        int result = instance.addProfessorUV(professor);
        assertEquals(expResult, result);
    }
    
    @org.junit.Test
    public void testAddProfessorForeign() {
        System.out.println("addProfessorForeign");
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 1;
        professor.setName("Juan Díaz");
        professor.setStatus("Aceptado");
        professor.setPhoneNumber("34534522");
        professor.setEmail("juanDiaz@gmail.com");
        professor.setCountry("México");
        professor.setUniversityId(2);
        professor.setType("Externo");
        professor.setUser("Usuario de prueba externo1");
        professor.setPassword("testtest");
        
        int result = instance.addProfessorForeign(professor);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testAddProfessorForeignFailed(){
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 0;
        professor.setName("Juan Díaz");
        professor.setPhoneNumber("34534522");
        professor.setStatus("Aceptado");
        professor.setType("Externo");
        professor.setEmail("juanDiaz@gmail.com");
        professor.setCountry("México");
        professor.setUniversityId(4);
        professor.setUser("Usuario de prueba externo2");
        professor.setPassword("testtest");
        
        int result = instance.addProfessorForeign(professor);
        assertEquals(expResult, result);
    }


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
        int expResult = 0;
        professor.setProfessorId(25);
        int result = instance.deleteProfessor(professor);
        assertEquals(expResult, result);
    }


    @org.junit.Test
    public void testUpdateProfessorForeignSuccess() {
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
        int result = instance.updateProfessorForeign(professor);
        assertEquals(expResult, result);
    }
    
    @Test
    public void updateProfessorForeignFailedUniversity(){
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 0;
        professor.setName("Lucas Perez");
        professor.setStatus("En espera");
        professor.setType("Externo");
        professor.setCountry("Alemania");
        professor.setUniversityId(5);
        professor.setProfessorId(7);
        int result = instance.updateProfessorForeign(professor);
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

        @Test
        public void testSearchProfessorByCollaborationSuccess(){
            int expectedResult = 1;
            ProfessorDAO instance = new ProfessorDAO();
            int collaborationId = 13;
            ArrayList<Professor> professors = instance.searchProfessorByCollaboration(collaborationId);
            assertEquals(expectedResult, professors.size());
        }

        @Test
        public void testSearchProfessorByCollaborationFailed(){
            int expectedResult = 0;
            ProfessorDAO instance = new ProfessorDAO();
            int collaborationId = 34;
            ArrayList<Professor> professors = instance.searchProfessorByCollaboration(collaborationId);
            assertEquals(expectedResult, professors.size());
        }

        @Test
        public void changeProfessorStatusByIdSuccess(){
            System.out.println("changeStateProfessor");
            Professor professor = new Professor();
            professor.setProfessorId(10); 
            professor.setStatus("Activo");
            
            ProfessorDAO instance = new ProfessorDAO();
            int expectedResult = 1;
            int result = instance.changeProfessorStatusById(professor);
            assertEquals(expectedResult, result);

        }
        
        @Test
        public void changeProfessorStatusByIdFailed(){
            System.out.println("changeStateProfessor");
            Professor professor = new Professor();
            professor.setProfessorId(1000); 
            professor.setStatus("Activo"); 
            
            ProfessorDAO instance = new ProfessorDAO();
            int expectedResult = 0;
            int result = instance.changeProfessorStatusById(professor);
            assertEquals(expectedResult, result);

        }

        @Test
        public void professorRequestCollaborationSuccess(){
            Professor professor = new Professor();
            ProfessorDAO professorDAO = new ProfessorDAO();
            professor.setProfessorId(10);
            professor.setCollaborationId(15);

            int result = professorDAO.professorRequestCollaboration(professor);
            assertEquals(1, result);
        }

        @Test
        public void professorRequestCollaborationFailed(){
            Professor professor = new Professor();
            ProfessorDAO professorDAO = new ProfessorDAO();
            professor.setProfessorId(100000);
            professor.setCollaborationId(213231);

            int result = professorDAO.professorRequestCollaboration(professor);
            assertEquals(0, result);
        }
}       

