package logic;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import logic.DAO.CollaborationDAO;
import logic.classes.Collaboration;

public class CollaborationValidatorTest {

    @Test
    public void validateCollaborationFieldsTestSucccess(){
        Collaboration collaboration = new Collaboration();
        collaboration.setCollaborationName("Pluriculturalidad");
        collaboration.setDescription("Busca fomentar el conocimiento de las culturas");
        collaboration.setCollaborationType("COIL-VIC");
        collaboration.setStudentProfile("Inglés B2");
        collaboration.setCollaborationGoal("Promover la pluriculturalidad");
        collaboration.setNoStudents(25);
        collaboration.setStartDate(LocalDate.of(2024, 06, 23));
        collaboration.setFinishDate(LocalDate.of(2024, 11, 23));
        collaboration.setSubject("Cultura");
        boolean expectedResult = true;
        boolean result = CollaborationValidator.onlyValidateCollaborationFields(collaboration);
        assertEquals(expectedResult, result);

    }

}
