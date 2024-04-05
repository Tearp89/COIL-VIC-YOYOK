/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daur0
 */
public class FieldValidatorDAOTest {
    
    /**
     * Test of onlyText method, of class FieldValidatorDAO.
     */
    @Test
    public void testOnlyText() {
        assertTrue(FieldValidatorDAO.onlyText("Texto sin números"));
        assertFalse(FieldValidatorDAO.onlyText("Texto con números 123"));
        assertFalse(FieldValidatorDAO.onlyText(""));
    }

    /**
     * Test of onlyNumber method, of class FieldValidatorDAO.
     */
    @Test
    public void testOnlyNumber() {
        assertTrue(FieldValidatorDAO.onlyNumber("12345"));
        assertFalse(FieldValidatorDAO.onlyNumber("123abc"));
        assertFalse(FieldValidatorDAO.onlyNumber(""));
    }

    /**
     * Test of isEmail method, of class FieldValidatorDAO.
     */
    @Test
    public void testIsEmail() {
        assertTrue(FieldValidatorDAO.isEmail("correo@example.com"));
        assertFalse(FieldValidatorDAO.isEmail(""));
        assertFalse(FieldValidatorDAO.isEmail("correo.com"));
        assertFalse(FieldValidatorDAO.isEmail("correo@.com"));
        
        
    }
    
}
