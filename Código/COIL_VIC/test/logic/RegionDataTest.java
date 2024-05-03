/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package logic;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import logic.DAO.CollaborationStatsDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author daur0
 */
public class RegionDataTest {
    
    public RegionDataTest() {
    }

    /**
     * Test of getUsers method, of class RegionData.
     */
    @Test
    public void testGetUsers() {
        CollaborationStatsDAO collaborationStatsDAO = new CollaborationStatsDAO();
        ArrayList<RegionData> regionDataList = new ArrayList();
        regionDataList.add(new RegionData("Xalapa", collaborationStatsDAO.countProfessorsByRegionAndYear("Xalapa", "2022"), collaborationStatsDAO.countStudentsByRegionAndYear("Xalapa", "2022")));
        regionDataList.add(new RegionData("Orizaba", collaborationStatsDAO.countProfessorsByRegionAndYear("Orizaba", "2022"), collaborationStatsDAO.countStudentsByRegionAndYear("Orizaba", "2022")));
        regionDataList.add(new RegionData("Poza Rica", collaborationStatsDAO.countProfessorsByRegionAndYear("Poza Rica", "2022"), collaborationStatsDAO.countStudentsByRegionAndYear("Poza Rica", "2022")));
        regionDataList.add(new RegionData("Veracruz", collaborationStatsDAO.countProfessorsByRegionAndYear("Veracruz", "2022"), collaborationStatsDAO.countStudentsByRegionAndYear("Veracruz", "2022")));
        regionDataList.add(new RegionData("Coatzacoalcos", collaborationStatsDAO.countProfessorsByRegionAndYear("Coatzacoalcos", "2022"), collaborationStatsDAO.countStudentsByRegionAndYear("Coatzacoalcos", "2022")));
        for (RegionData objeto : regionDataList) {
            System.out.println(objeto.getProfessorsCount());
        }
    }
    
}
