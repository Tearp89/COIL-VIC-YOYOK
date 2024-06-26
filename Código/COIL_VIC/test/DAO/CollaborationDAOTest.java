/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAO;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;

import logic.DAO.CollaborationDAO;
import logic.DAO.CollaborationStatsDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

import static org.junit.Assert.*;
import java.sql.SQLException;

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
        
        LocalDate dateStartTest =  LocalDate.of(2022, 1, 1); 
        LocalDate dateFinishTest =  LocalDate.of(2026, 2, 1);
        
        collaboration.setCollaborationName("colaboración si");
        collaboration.setDescription("TestDescripcion");
        collaboration.setFinishDate(dateFinishTest);
        collaboration.setStartDate(dateStartTest);
        collaboration.setCollaborationStatus("Rechazada");
        collaboration.setCollaborationGoal("Unir estudiantes");
        collaboration.setNoStudents(25);
        collaboration.setStudentProfile("Inglés, ");
        collaboration.setSubject("Trabajo colaborativo");
        collaboration.setCollaborationType("COIL-VIC");
        
        
        int rowsAffected = instance.addCollaboration(collaboration);
        assertEquals(1, rowsAffected);
    }

    @org.junit.Test
    public void testUpdateCollaborationSuccess() {
        Collaboration collaboration = new Collaboration();
        CollaborationDAO instance = new CollaborationDAO();
        
        LocalDate dateStartTest = LocalDate.of(2000, 1, 1);
        LocalDate dateFinishtTest = LocalDate.of(2000, 2, 2);
        
        collaboration.setCollaborationName("NuevoTestNombreCol");
        collaboration.setDescription("NuevoTestDescripcion");
        collaboration.setFinishDate(dateFinishtTest);
        collaboration.setStartDate(dateStartTest);
        collaboration.setCollaborationId(46);
        collaboration.setCollaborationGoal("Conviviri");
        collaboration.setNoStudents(20);
        collaboration.setStudentProfile("Ganas de trabajar");
        collaboration.setSubject("Orueba");
        collaboration.setCollaborationGoal("otra prueba");
        int rowsAffected = instance.updateCollaboration(collaboration);
        assertEquals(1, rowsAffected);
    }
    
    @Test
    public void testUpdateCollaborationFailed(){
        Collaboration collaboration = new Collaboration();
        CollaborationDAO instance = new CollaborationDAO();
        
        LocalDate dateStartTest = LocalDate.of(2000, 1, 1);
        LocalDate dateFinishtTest = LocalDate.of(2000, 2, 2);
        
        collaboration.setCollaborationName("NuevoTestNombreCol");
        collaboration.setDescription("NuevoTestDescripcion");
        collaboration.setFinishDate(dateFinishtTest);
        collaboration.setStartDate(dateStartTest);
        collaboration.setCollaborationId(197);
        
        int rowsAffected = instance.updateCollaboration(collaboration);
        assertEquals(0, rowsAffected);
    }
    
    

    /**
     * Test of deleteCollaboration method, of class CollaborationDAO.
     */
    @org.junit.Test
    public void testDeleteCollaboration() {
        Collaboration collaboration = new Collaboration();
        CollaborationDAO instance = new CollaborationDAO();
        
        LocalDate dateStartTest = LocalDate.of(2000, 01, 01);
        LocalDate dateFinishtTest = LocalDate.of(2000, 02, 02);
        
        collaboration.setCollaborationName("NuevoTestNombreCol");
        collaboration.setDescription("NuevoTestDescripcion");
        collaboration.setFinishDate(dateFinishtTest);
        collaboration.setStartDate(dateStartTest);
        collaboration.setCollaborationId(53);
        
        
        int rowsAffected = instance.deleteCollaboration(collaboration);
        assertEquals(1, rowsAffected);
    }
    
    @Test
    public void testDeleteCollaborationFailed(){
        Collaboration collaboration = new Collaboration();
        CollaborationDAO instance = new CollaborationDAO();
        
        LocalDate dateStartTest = LocalDate.of(2000, 3, 1);
        LocalDate dateFinishtTest = LocalDate.of(2000, 1, 1);
        
        collaboration.setCollaborationName("TestNombreCol");
        collaboration.setDescription("TestDescripcion");
        collaboration.setFinishDate(dateFinishtTest);
        collaboration.setStartDate(dateStartTest);
        collaboration.setCollaborationId(78);
        
        
        int rowsAffected = instance.deleteCollaboration(collaboration);
        assertEquals(0, rowsAffected);
    }

    
    
    
    @Test
    public void testSearchCollaborationSuccess(){
        int expectedResult = 1;
        CollaborationDAO instance = new CollaborationDAO();
        String name = "Pendiente";
        ArrayList<Collaboration> collaborations = instance.searchCollaborationByStatus(name);
        assertEquals(expectedResult, collaborations.size());
    }
    
    @Test
    public void testSearchCollaborationFailed(){
        int expectedResult = 0;
        CollaborationDAO instance = new CollaborationDAO();
        String name = "Colaboración-2024";
        ArrayList<Collaboration> collaborations = instance.searchCollaborationByStatus(name);
        assertEquals(expectedResult, collaborations.size());
    }
    @Test
    public void testSearchCollaborationByYearSuccess(){
        int expectedResult = 5;
        CollaborationDAO instance = new CollaborationDAO();
        String year = "2024";
        ArrayList<Collaboration> collaborations = instance.searchCollaborationByYear(year);
        assertEquals(expectedResult, collaborations.size());
    }
    
    @Test 
    public void testSearchCollaborationByYearFailed(){
        int expectedResult = 0;
        CollaborationDAO instance = new CollaborationDAO();
        String year = "2034";
        ArrayList<Collaboration> collaborations = instance.searchCollaborationByYear(year);
        assertEquals(expectedResult, collaborations.size());
    }


    @Test
    public void testCountStudentsByRegionSuccess(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 2;
        String region = "Poza Rica-Tuxpan";
        int actualResult = instance.countStudentsByRegion(region);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountStudentsByRegionFailed(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 2;
        String region = "Poza Rica-Tuxpan";
        int actualResult = instance.countStudentsByRegion(region);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountStudentByRegionAndYearSuccess(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 0;
        String region = "Veracruz";
        String year = "2000";
        int actualResult = instance.countStudentsByRegionAndYear(region, year);
        assertEquals(expectedResult, actualResult);
    
    }

    @Test
    public void testCountStudentByRegionAndYearFailed(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 0;
        String region = "Xalapa";
        String year = "2000";
        int realResult = instance.countStudentsByRegionAndYear(region, year);
        assertEquals(expectedResult, realResult);
    }

    @Test
    public void testCountStudentByAcademicAreaSuccess(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 0;
        String academicArea = "Técnica";
        int actualResult = instance.countStudentsByAcademicArea(academicArea);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountStudentByAcademicAreaFailed(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 0;
        String academicArea = "Económico-Administrativa";
        int actualResult = instance.countStudentsByAcademicArea(academicArea);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testCountProfessorsByAcademicAreaSuccess(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 1;
        String academicArea = "Técnica";
        int actualResult = instance.countProfessorsByAcademicArea(academicArea);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountProfessorsByAcademicAreaFailed(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 0;
        String academicArea = "Humanidades";
        int actualResult = instance.countProfessorsByAcademicArea(academicArea);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountProfessorsByAcademicAreaAndYearSuccess(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 1;
        String academicArea = "Ciencias de la salud";
        String year = "2024";
        int actualResult = instance.countProfessorsByAcademicAreaAndYear(academicArea, year);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountProfessorsByAcademicAreaAndYearFailed(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 0;
        String academicArea = "Humanidades";
        String year = "2027";
        int actualResult = instance.countProfessorsByAcademicAreaAndYear(academicArea, year);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountProfessorsByRegionSuccess(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 1;
        String region = "Poza Rica-Tuxpan";
        int actualResult = instance.countProfessorsByRegion(region);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountProfessorsByRegionFailed(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 2;
        String region = "Xalapa";
        int actualResult = instance.countProfessorsByRegion(region);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountProfessorByRegionAndYearSuccess(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 1;
        String region = "Poza Rica-Tuxpan";
        String year = "2024";
        int actualResult = instance.countProfessorsByRegionAndYear(region, year);
        assertEquals(expectedResult, actualResult); 
    }

    @Test
    public void testCountProfessorByRegionAndYearFailed(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 0;
        String region = "Xalapa";
        String year = "2798";
        int actualResult = instance.countProfessorsByRegionAndYear(region, year);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void changeCollaborationStatusSuccess(){
        System.out.println("changeStateProfessor");
            int collaborationId = 45; 
            String status = "Activa";
            
            CollaborationDAO instance = new CollaborationDAO();
            int expectedResult = 1;
            int result = instance.changeCollaborationStatus(status, collaborationId);
            assertEquals(expectedResult, result);
    }

    @Test
    public void changeCollaborationStatusFailed(){
        System.out.println("changeStateProfessor");
        int collaborationId = 100; 
            String status = "Activa";
            
        CollaborationDAO instance = new CollaborationDAO();
        int expectedResult = 0;
        int result = instance.changeCollaborationStatus(status, collaborationId);
        assertEquals(expectedResult, result);

        }

        @Test 
        public void assignStudentToCollaborationSuccess() throws SQLException{
            CollaborationDAO instance = new CollaborationDAO();
            int expectedResult = 1;
            String studentEmail = "zS22013640@estudiantes.uv.mx";
            int collaborationId = 50;
            int actualResult = instance.assignStudentToCollaboration(studentEmail, collaborationId);
            assertEquals(expectedResult, actualResult);
        }

        @Test
        public void assignProfessorToCollaborationSuccess() throws SQLException{
            CollaborationDAO instance = new CollaborationDAO();
            int expectedResult = 1;
            int professorId = 41;
            int collaborationId = 46;
            int actualResult = instance.assignProfessorToCollaboration(professorId, collaborationId);
            assertEquals(expectedResult, actualResult);

        }

        @Test
        public void getCollaborationNameSuccess(){
            CollaborationDAO instance = new CollaborationDAO();
            String expectedResult = "Bonjour al frances";
            String actualResult = instance.getCollaborationNameById(47);
            assertEquals(expectedResult, actualResult);
        }

        @Test
        public void getCollaborationDescriptionSuccess(){
            CollaborationDAO instance = new CollaborationDAO();
            String expectedResult = "Aprendizaje del idioma frances";
            String actualResult = instance.getCollaborationDescriptionById(47);
            assertEquals(expectedResult, actualResult);
        }

        @Test
        public void getCollaborationStartDateSuccess(){
            CollaborationDAO instance = new CollaborationDAO();
            String expectedResult = "2024-06-01";
            String actualResult = instance.getCollaborationStartDateById(47);
            assertEquals(expectedResult, actualResult);
        }

        @Test
        public void searchCollaborationByStatusAndProfessorIdTestSuccess(){
            CollaborationDAO instance = new CollaborationDAO();
            int expectedResult = 1;
            int professorId = 36;
            String status = "Activa";
            int result = instance.searchCollaborationByStatusAndProfessorId(status, professorId).size();
            assertEquals(expectedResult, result);

        }

        @Test
        public void searchCollaborationByStatusNameandProfessorIdTestSuccess(){
            CollaborationDAO instance = new CollaborationDAO();
            int expectedResult = 1;
            int professorId = 36;
            String status = "Activa";
            int result = instance.searchCollaborationByStatusAndProfessorId(status, professorId).size();
            assertEquals(expectedResult, result);


        }

        @Test
        public void getCollaboratorNameByIdTestSuccess(){
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            String expectedResult = "Julian Gutierrez";
            String result = collaborationDAO.getCollaboratorNameById(47, 36);
            assertEquals(expectedResult, result);
        }

        @Test
        public void isStudentAssignedTestSuccess(){
            int collaborationId = 47;
            String email = "loromaro@estudiantes.uv.mx";
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            boolean expectedResult = true;
            boolean result = collaborationDAO.isStudentAssignedToCollaboration(email, collaborationId);
            assertEquals(expectedResult, result);
        }

        @Test
        public void validateCollaborationProfessorsLimitTestSuccess(){
            boolean expectedResult = false;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            boolean result = collaborationDAO.validateCollaborationProfessorsLimit(47);
            assertEquals(expectedResult, result);
        }

        @Test
        public void isProfessorInCollaborationTestSuccess(){
            int professorId = 40;
            boolean expectedResult = true;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            boolean result = collaborationDAO.isProfessorInCollaboration(professorId);
            assertEquals(expectedResult, result);
            
        }

        @Test
        public void validateCollaborationNameExceptActualTestSuccess(){
            boolean expectedResult = true;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            boolean result = collaborationDAO.validateCollaborationNameExceptActual("NuevoTestNombreCol", 45);
            assertEquals(expectedResult, result);
        }

        @Test
        public void changeRequestStatusTestSuccess(){
            String status = "Rechazada";
            int collaborationId = 46;
            int professorId = 40;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            int expectedResult = 1;
            int result = collaborationDAO.changeRequestStatus(professorId, collaborationId, status);
            assertEquals(expectedResult, result);
        }

        @Test 
        public void changeRequestStatusByNotChosenTestSuccess(){
            int professorId = 39;
            int collaborationId = 46;
            String status = "No seleccionado";
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            int expectedResult = 1;
            int result = collaborationDAO.changeRequestStatusByNotChosen(professorId, collaborationId, status);
            assertEquals(expectedResult, result);
        }

        @Test
        public void getProfessorWithCollaborationRequestTestSuccess(){
            int collaborationId = 46;
            ArrayList<Professor> possibleCollaborators = new ArrayList<>();
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            possibleCollaborators = collaborationDAO.getProfessorsWithCollaborationRequests(collaborationId);
            int expectedResult = 2;
            int result = possibleCollaborators.size();
            assertEquals(expectedResult, result);
        }

        @Test
        public void validateCollaborationProfessorsLimit(){
            int collaborationId = 46;
            boolean expectedResult = false;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            boolean result = collaborationDAO.validateCollaborationProfessorsLimit(collaborationId);
            assertEquals(expectedResult, result);
        }

       

        @Test
        public void loadSubjectsTestSuccess(){
            int expectedResult = 6;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            int result = collaborationDAO.loadSubjects().size();
            assertEquals(expectedResult, result);
        }

        @Test
        public void getUnreviewedCollaborationsByAdminTestSucces(){
            int expectedResult = 2;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            int result = collaborationDAO.getUnreviewedCollaborationsByAdmin(4).size();
            assertEquals(expectedResult, result);

        }

        @Test
        public void getUnreviewedCollaborationsByProfessorTestSucces(){
            int expectedResult = 1;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            int result = collaborationDAO.getUnreviewedCollaborationsByProfessor(40).size();
            assertEquals(expectedResult, result);

        }

        @Test
        public void getUnreviewedCollaborationsByStudentTestSucces(){
            int expectedResult = 1;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            int result = collaborationDAO.getUnreviewedCollaborationsByStudent("loromaro@estudiantes.uv.mx").size();
            assertEquals(expectedResult, result);

        }

        @Test
        public void validateCollaborationStudentLimitTestSuccess(){
            int collaborationId = 49;
            CollaborationDAO collaborationDAO = new CollaborationDAO();
            boolean expectedResult = true;
            boolean result = collaborationDAO.validateCollaborationStudentLimit(collaborationId, 25);
            assertEquals(expectedResult, result);
        }

        




         
        

        





}

