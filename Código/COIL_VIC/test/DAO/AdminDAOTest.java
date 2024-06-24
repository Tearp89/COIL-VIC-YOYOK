/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAO;

import org.junit.Test;

import logic.DAO.AdminDAO;
import logic.classes.Admin;

import static org.junit.Assert.*;

/**
 *
 * @author Daniel
 */
public class AdminDAOTest {
    
    public AdminDAOTest() {
    }

    /**
     * Test of addAdmin method, of class AdminDAO.
     */
    @Test
    public void testAddAdmin() {
        Admin admin = new Admin();
        AdminDAO adminDAO = new AdminDAO();
        admin.setAdminName("AdminNombreTest");
        admin.setAdminRol("RolTest");
        admin.setAdminUser("UsuarioTest113");
        admin.setPassword("ContraseñaTest");
        
        int rowsAffected = adminDAO.addAdmin(admin);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of deleteFacilitator method, of class AdminDAO.
     */
   

    /**
     * Test of updateAdmin method, of class AdminDAO.
     */
    @Test
    public void testUpdateAdmin() {
        Admin admin = new Admin();
        AdminDAO adminDAO = new AdminDAO();
        
        admin.setAdminName("Leah Dominguez");
        admin.setAdminRol("Administrador");
        admin.setAdminUser("lDominguez2");
        admin.setPassword("ContraseñaTest");
        admin.setAdminId(6);
        
        int rowsAffected = adminDAO.updateAdmin(admin);
        assertEquals(1, rowsAffected);
    }
    
    @Test
    public void testGetAdminNameByUserSuccess(){
        Admin admin = new Admin();
        AdminDAO adminDAO = new AdminDAO();

        admin.setAdminUser("rPerez");
        String stringExpected = adminDAO.getAdminNameByUser(admin.getAdminUser());
        assertEquals("Rodrigo Perez", stringExpected);
    }

    @Test
    public void testGetAdminNameByUserFailed(){
        Admin admin = new Admin();
        AdminDAO adminDAO = new AdminDAO();

        admin.setAdminUser("failedTest");
        String stringExpected = adminDAO.getAdminNameByUser(admin.getAdminUser());
        assertNotEquals("Taylor", stringExpected);
    }

    @Test
    public void testDeleteAdmin() {
        Admin admin = new Admin();
        AdminDAO adminDAO = new AdminDAO();
        admin.setAdminName("Leah Dominguez");
        admin.setAdminRol("RolTest");
        admin.setAdminUser("UsuarioAdminTest");
        admin.setPassword("ContraseñaTest");
        
        int rowsAffected = adminDAO.deleteAdmin(admin);
        assertEquals(1, rowsAffected);
    }
}
