package logic.DAO;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CollaborationStatsDAOTest {

    @Test
    public void testCountStudentsByRegion() {
        CollaborationStatsDAO dao = new CollaborationStatsDAO();
        int result = dao.countStudentsByRegion("Xalapa");
        assertEquals(10, result); 
    }

    @Test
    public void testCountStudentsByRegionAndYear() {
        CollaborationStatsDAO dao = new CollaborationStatsDAO();
        int result = dao.countStudentsByRegionAndYear("Xalapa", "2022");
        assertEquals(1, result);
    }

    @Test
    public void testCountStudentsByAcademicArea() {
        CollaborationStatsDAO dao = new CollaborationStatsDAO();
        int result = dao.countStudentsByAcademicArea("Area 1");
        assertEquals(20, result); 
    }

    @Test
    public void testCountProfessorsByAcademicArea() {
        CollaborationStatsDAO dao = new CollaborationStatsDAO();
        int result = dao.countProfessorsByAcademicArea("Area 1");
        assertEquals(15, result); 
    }

    @Test
    public void testCountProfessorsByAcademicAreaAndYear() {
        CollaborationStatsDAO dao = new CollaborationStatsDAO();
        int result = dao.countProfessorsByAcademicAreaAndYear("Area 1", "2020");
        assertEquals(10, result); 
    }

    @Test
    public void testCountProfessorsByRegion() {
        CollaborationStatsDAO dao = new CollaborationStatsDAO();
        int result = dao.countProfessorsByRegion("Region 1");
        assertEquals(25, result); 
    }

    @Test
    public void testCountProfessorsByRegionAndYear() {
        CollaborationStatsDAO dao = new CollaborationStatsDAO();
        int result = dao.countProfessorsByRegionAndYear("Region 1", "2020");
        assertEquals(15, result);
    }
}