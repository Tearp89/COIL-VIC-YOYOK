/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAO;

import org.junit.Test;
import javafx.collections.ObservableList;
import logic.DAO.UniversityDAO;
import logic.classes.University;
import static org.junit.Assert.*;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class UniversityDAOTest {
    /**
     * Test of addUniversity method, of class UniversityDAO.
     */
    @Test
    public void testAddUniversity() {
        UniversityDAO universityDAO = new UniversityDAO();
        University university = new University();
        university.setUniversityCountry("Uganda");
        university.setUniversityName("Uganda University");
        university.setUniversityLanguage("Ingles");
        
        int rowsAffected = universityDAO.addUniversity(university);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of deleteUniversity method, of class UniversityDAO.
     */
    @Test
    public void testDeleteUniversity() {
        UniversityDAO universityDAO = new UniversityDAO();
        University university = new University();
        university.setUniversityName("Uganda University");
        
        int rowsAffected = universityDAO.deleteUniversity(university);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of updateUniversity method, of class UniversityDAO.
     */
    @Test
    public void testUpdateUniversity() {
        UniversityDAO universityDAO = new UniversityDAO();
        University university = new University();
        university.setUniversityCountry("CambioPaís");
        university.setUniversityName("NuevoNombre");
        university.setUniversityLanguage("NuevoIdioma");
        university.setUniversityId(14);
        int rowsAffected = universityDAO.updateUniversity(university);
        assertEquals(1, rowsAffected);
    }

    @Test
    public void testGetUniversityIdSuccess(){
        UniversityDAO universityDAO = new UniversityDAO();
        String universityName = "MIT";
        int result = universityDAO.getUniversityId(universityName);
        int expectedResult = 9;
        assertEquals(result, expectedResult);
    }

    @Test 
    public void testUniversityRegisteredSucces(){
        UniversityDAO universityDAO = new UniversityDAO();
        String universityName = "MIT";
        boolean expectedResult = true;
        boolean result = universityDAO.isUniversityRegistered(universityName);
        assertEquals(expectedResult, result);

    }

    @Test
    public void testLoadUniversities() throws SQLException {
        UniversityDAO universityDAO = new UniversityDAO();
        int expectedResult = 7;
        ObservableList<String> universities = universityDAO.loadUniversities();

        assertEquals(expectedResult, universities.size());
    }

    @Test
    public void testSearchUniversitySuccess(){
        UniversityDAO universityDAO = new UniversityDAO();
        int expectedResult = 8;
        int result = universityDAO.searchUniversity().size();
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSearchUniversityByNameSuccess(){
        UniversityDAO universityDAO = new UniversityDAO();
        int expectedResult = 1;
        String name = "MIT";
        int result = universityDAO.searchUniversityByName(name).size();
        assertEquals(expectedResult, result);
    }
    
}
