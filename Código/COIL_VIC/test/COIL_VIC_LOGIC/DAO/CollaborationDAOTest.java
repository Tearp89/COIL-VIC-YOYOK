/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;

import logic.DAO.CollaborationDAO;
import logic.DAO.CollaborationStatsDAO;
import logic.classes.Collaboration;

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
        collaboration.setCollaborationId(20);
        
        
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

    /**
     * Test of updateCollaboration method, of class CollaborationDAO.
     */
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
        collaboration.setCollaborationId(1);
        
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
    
    
    @Test
    public void testSearchCollaborationSuccess(){
        int expectedResult = 5;
        CollaborationDAO instance = new CollaborationDAO();
        String name = "TestNombreCol";
        ArrayList<Collaboration> collaborations = instance.searchCollaboration(name);
        assertEquals(expectedResult, collaborations.size());
    }
    
    @Test
    public void testSearchCollaborationFailed(){
        int expectedResult = 0;
        CollaborationDAO instance = new CollaborationDAO();
        String name = "Colaboración-2024";
        ArrayList<Collaboration> collaborations = instance.searchCollaboration(name);
        assertEquals(expectedResult, collaborations.size());
    }
    @Test
    public void testSearchCollaborationByYearSuccess(){
        int expectedResult = 12;
        CollaborationDAO instance = new CollaborationDAO();
        String year = "2000";
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
        int expectedResult = 1;
        String region = "Veracruz";
        int actualResult = instance.countStudentsByRegion(region);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountStudentsByRegionFailed(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 0;
        String region = "Xalapa";
        int actualResult = instance.countStudentsByRegion(region);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountStudentByRegionAndYearSuccess(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 1;
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
        int expectedResult = 1;
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
        String academicArea = "Técnica";
        String year = "2000";
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
        String region = "Veracruz";
        int actualResult = instance.countProfessorsByRegion(region);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountProfessorsByRegionFailed(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 0;
        String region = "Xalapa";
        int actualResult = instance.countProfessorsByRegion(region);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCountProfessorByRegionAndYearSuccess(){
        CollaborationStatsDAO instance = new CollaborationStatsDAO();
        int expectedResult = 1;
        String region = "Veracruz";
        String year = "2000";
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



}

