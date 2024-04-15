package DAO;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.classes.Syllabus;
import logic.DAO.SyllabusDAO;

/**
 *
 * @author isabe
 */
public class SyllabusDAOTest {

    @Test
    public void testAddSyllabus() {
        Syllabus syllabus = new Syllabus();
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        syllabus.setActivity("NombreActividadTest");
        syllabus.setDescription("DescripcionTest");
        syllabus.SetNameOfResponsable("NombreResponsableTest");

        int rowsAffected = syllabusDAO.addSyllabus(syllabus);
        assertEquals(1, rowsAffected);
    }

    @Test
    public void testDeleteSyllabus() {
        Syllabus syllabus = new Syllabus();
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        syllabus.setSyllabusId(1);
        syllabus.setActivity("NombreActividadTest");
        syllabus.setDescription("DescripcionTest");
        syllabus.SetNameOfResponsable("NombreResponsableTest");

        int rowsAffected = syllabusDAO.deleteSyllabus(syllabus);
        assertEquals(1, rowsAffected);
    }

    @Test 
    public void testUpdateSyllabus() {
        Syllabus syllabus = new Syllabus();
        SyllabusDAO syllabusDAO = new SyllabusDAO();
        syllabus.setSyllabusId(1);
        syllabus.setActivity("NuevoNombreActividadTest");
        syllabus.setDescription("NuevaDescripcionTest");
        syllabus.SetNameOfResponsable("NuevoNombreResponsable");
        
        int rowsAffected = syllabusDAO.updateSyllabus(syllabus);
        assertEquals(1, rowsAffected);
    }
}