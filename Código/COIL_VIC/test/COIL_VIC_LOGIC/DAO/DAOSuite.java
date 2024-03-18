/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Daniel
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({COIL_VIC_LOGIC.DAO.StudentDAOTest.class, COIL_VIC_LOGIC.DAO.UniversityDAOTest.class, COIL_VIC_LOGIC.DAO.ProfessorDAOTest.class})
public class DAOSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
