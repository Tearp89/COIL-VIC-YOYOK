/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package logic;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daur0
 */
public class FieldValidatorTest {
    

    @Test
    public void testOnlyTextSuccess() {
        assertTrue(FieldValidator.onlyText("Texto sin números"));
    }

    @Test
    public void testOnlyTextFailed() {
        assertFalse(FieldValidator.onlyText("Texto con números 123"));
    }

    @Test
    public void testOnlyTextIsBlank(){
        assertFalse(FieldValidator.onlyText(""));
    }

    /**
     * Test of onlyNumber method, of class FieldValidator.
     */
    @Test
    public void testOnlyNumberSuccess() {
        assertTrue(FieldValidator.onlyNumber("12345"));
    }

    @Test
    public void testOnlyNumberFailed() {
        assertFalse(FieldValidator.onlyNumber("123abc"));
    }

    @Test
    public void testOnlyNumberIsBlank(){
        assertFalse(FieldValidator.onlyNumber(""));
    }

    /**
     * Test of isEmail method, of class FieldValidator.
     */
    @Test
    public void testIsEmailSuccess() {
        assertTrue(FieldValidator.isEmail("correo@example.com"));
    }

    @Test
    public void testIsEmailFailed(){
        assertFalse(FieldValidator.isEmail("correo.com"));
        assertFalse(FieldValidator.isEmail("correo@.com"));
    }
    
    @Test
    public void testIsEmailIsBlank() {
        assertFalse(FieldValidator.isEmail(""));
    }
}