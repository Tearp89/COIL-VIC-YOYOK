/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import COIL_VIC_LOGIC.Classes.Admin;
import COIL_VIC_LOGIC.DAO.AdminDAO;
import org.junit.Test;
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
        admin.setAdminId("IDAdminTest");
        admin.setAdminName("AdminNombreTest");
        admin.setAdminRol("RolTest");
        admin.setAdminUser("UsuarioAdminTest");
        admin.setPassword("ContraseñaTest");
        
        int rowsAffected = adminDAO.addAdmin(admin);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of deleteFacilitator method, of class AdminDAO.
     */
    @Test
    public void testDeleteFacilitator() {
        Admin admin = new Admin();
        AdminDAO adminDAO = new AdminDAO();
        admin.setAdminId("IDAdminTest");
        admin.setAdminName("AdminNombreTest");
        admin.setAdminRol("RolTest");
        admin.setAdminUser("UsuarioAdminTest");
        admin.setPassword("ContraseñaTest");
        
        int rowsAffected = adminDAO.deleteAdmin(admin);
        assertEquals(1, rowsAffected);
    }

    /**
     * Test of updateAdmin method, of class AdminDAO.
     */
    @Test
    public void testUpdateAdmin() {
        Admin admin = new Admin();
        AdminDAO adminDAO = new AdminDAO();
        
        admin.setAdminId("IDAdminTest");
        admin.setAdminName("NuevoAdminNombreTest");
        admin.setAdminRol("NuevoRolTest");
        admin.setAdminUser("NuevoUsuarioAdminTest");
        admin.setPassword("NuevoContraseñaTest");
        
        int rowsAffected = adminDAO.updateAdmin(admin);
        assertEquals(1, rowsAffected);
    }
    
}
