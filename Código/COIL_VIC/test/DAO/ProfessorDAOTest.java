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
    public void testAddProfessor() {
        System.out.println("addProfessor");
        Professor professor = new Professor();
        ProfessorDAO instance = new ProfessorDAO();
        int expResult = 1;
        professor.setName("Pedro Jimenez");
        professor.setStatus("Pendiente");
        professor.setType("UV");
        professor.setCountry("México");
        professor.setUniversityId(1);
        professor.setAcademicArea("Económico");
        professor.setEmail("pppimenz@gmail.com");
        professor.setUser("jPedro");
        professor.setPassword("testtest");
        professor.setPhoneNumber("2220000000");
        professor.setWorkShop("Sí");
        
        int result = instance.addProfessor(professor);
        assertEquals(expResult, result);
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
            String status = "Aceptado";
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
            int professorId = 10;
            String status = "Aceptado";
            ProfessorDAO instance = new ProfessorDAO();
            int expectedResult = 1;
            int result = instance.changeProfessorStatusById(status, professorId);
            assertEquals(expectedResult, result);

        }
        
        @Test
        public void changeProfessorStatusByIdFailed(){
            System.out.println("changeStateProfessor");
            Professor professor = new Professor();
            int professorId = 1000000;
            String status = "Aceptado";
            ProfessorDAO instance = new ProfessorDAO();
            int expectedResult = 0;
            int result = instance.changeProfessorStatusById(status, professorId);
            assertEquals(expectedResult, result);

        }

        @Test
        public void professorRequestCollaborationSuccess(){
            
            ProfessorDAO professorDAO = new ProfessorDAO();
           int idProfesor = 24;
           int idColaboración = 33;

            int result = professorDAO.professorRequestCollaboration(idColaboración, idProfesor);
            assertEquals(1, result);
        }

        @Test
        public void professorRequestCollaborationFailed(){
            ProfessorDAO professorDAO = new ProfessorDAO();
            int idProfesor = 12343;
            int idColaboración = 8239230;

            int result = professorDAO.professorRequestCollaboration(idColaboración, idProfesor);
            assertEquals(0, result);
        }

        @Test
public void changeRequestStatusTestSuccess(){
    String status = "Aceptada";
    int idColaboración = 33;
    int idProfessor = 24;
    ProfessorDAO professorDAO = new ProfessorDAO();
    int expectedResult = 1;
    int result = professorDAO.changeRequestStatus(status, idColaboración, idProfessor);
    assertEquals(expectedResult, result);
}
}       


