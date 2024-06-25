package logic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.classes.Professor;

public class ProfessorValidatorTest {

    @Test
    public void validateUvProfessorFieldsTestSuccess(){
        Professor professor = new Professor();
        professor.setName("Marla Aguilar");
        professor.setPhoneNumber("2284963967");
        professor.setCountry("México");
        professor.setEmail("marla838.ma@gamil.com");
        professor.setWorkShop("Sí");
        professor.setAcademicArea("Técnica");
        professor.setPersonalNumber(235467);
        professor.setRegion("Xalapa");
        professor.setContractType("Becado Tipo A");
        professor.setContractCategory("Beca tipo A");
        professor.setUniversityName("Universidad Veracruzana");
        boolean expectedResult = true;
        boolean result = ProfessorValidator.validateUvProfessorFields(professor);
        assertEquals(expectedResult, result);
    }

    @Test
    public void validateForeignProfessorFieldTestSuccess(){
        Professor professor = new Professor();
        professor.setName("Marla Aguilar");
        professor.setPhoneNumber("2284963967");
        professor.setCountry("México");
        professor.setEmail("marla838.ma@gamil.com");
        professor.setWorkShop("Sí");
        professor.setUniversityName("MIT");
        professor.setLanguage("Inglés");
        boolean expectedResult = true;
        boolean result = ProfessorValidator.validateForeignProfessorFields(professor);
        assertEquals(expectedResult, result);

    }
}
