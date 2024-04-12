/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import org.junit.Test;

import logic.DAO.UniversityDAO;
import logic.classes.University;

import static org.junit.Assert.*;

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
        university.setUniversityCountry("PaisTest");
        university.setUniversityName("TestNombre");
        university.setUniversityLanguage("IdiomaTest");
        
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
        university.setUniversityCountry("PaisTest");
        university.setUniversityName("TestNombre");
        university.setUniversityLanguage("IdiomaTest");
        
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
        university.setUniversityCountry("NuevoPais");
        university.setUniversityName("NuevoNombre");
        university.setUniversityLanguage("NuevoIdioma");
        
        int rowsAffected = universityDAO.updateUniversity(university);
        assertEquals(1, rowsAffected);
    }
    
}
