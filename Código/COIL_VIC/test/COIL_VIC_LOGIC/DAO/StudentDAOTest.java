/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import org.junit.Test;

import logic.DAO.StudentDAO;
import logic.classes.Student;

import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class StudentDAOTest {
  
    /**
     * Test of addStudent method, of class StudentDAO.
     */
    @Test
    public void testAddStudent() {
        StudentDAO studentDAO = new StudentDAO();
        Student student = new Student();
        student.setEmail("Correo@ejemplo.com");
        int result = studentDAO.addStudent(student);
        assertEquals(1, result);
    }
    
}
