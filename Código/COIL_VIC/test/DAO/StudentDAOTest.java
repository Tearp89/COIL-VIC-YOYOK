/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAO;

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
    public void testAddStudentTestSuccess() {
        StudentDAO studentDAO = new StudentDAO();
        Student student = new Student();
        student.setEmail("testExtra@estudiantes.uv.mx");
        student.setProfessorId(40);
        student.setPassword("contraseñaEstudiante");
        int result = studentDAO.addStudent(student);
        assertEquals(1, result);
    }

    @Test
    public void updateStudentPasswordTestSuccess(){
        String password = "contraseñaEstudiante";
        StudentDAO studentDAO = new StudentDAO();
        String email = "torreo@ejemplo.com";
        int expectedResult = 1;
        int result = studentDAO.updateStudentPassword(password, email);
        assertEquals(expectedResult, result);
    }

    @Test
    public void isStudentAssignedToProfessorTestSuccess(){
        int professorId = 40;
        String email = "testExtra@estudiantes.uv.mx";
        StudentDAO studentDAO = new StudentDAO();
        boolean expectedResult = true;
        boolean result = studentDAO.isStudentAssignedToProfessor(email, professorId);
        assertEquals(expectedResult, result);
    }

    @Test
    public void isStudentRegisteredTestSuccess(){
        String email = "torreo@ejemplo.com";
        StudentDAO studentDAO = new StudentDAO();
        boolean expectedResult = true;
        boolean result = studentDAO.isStudentRegistered(email);
        assertEquals(expectedResult, result);
    }

    @Test
    public void getStudentsByProfessorIdTestSuccess(){
        int professorId = 40;
        StudentDAO studentDAO = new StudentDAO();
        int expectedResult = 4;
        int result = studentDAO.getStudentsByProfessorId(professorId).size();
        assertEquals(expectedResult, result);
    }


    
}
