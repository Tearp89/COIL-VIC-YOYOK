/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package COIL_VIC_DataAccess;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import dataAccess.DatabaseManager;

/**
 *
 * @author marla
 */
public class DatabaseManagerTest {
    
    public DatabaseManagerTest() {
    }

    @Test
    public void testGetConnectionSuccess() throws SQLException{
        System.out.println("getConnection");
        DatabaseManager instance = new DatabaseManager();       
        Connection result = instance.getConnection();
        assertNotNull(result);
    }
}
