package logic;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.DAO.ProfessorDAO;
import logic.classes.Professor;

public class ProfessorValidator {

    public static boolean validateUvProfessorFields(Professor professor) {
    String professorName = professor.getName();
    String professorPhoneNumber = professor.getPhoneNumber();
    String email = professor.getEmail();
    String country = professor.getCountry();
    String universityName = professor.getUniversityName();
    String workShop = professor.getWorkShop();
    String academicArea = professor.getAcademicArea();
    String personalNumber = String.valueOf(professor.getPersonalNumber());
    String region = professor.getRegion();
    String contractType = professor.getContractType();
    String contractCategory = professor.getContractCategory();
    ProfessorDAO professorDAO = new ProfessorDAO();
    if (!FieldValidator.isValidName(professorName) || !FieldValidator.onlyTextAndNumbers(professorPhoneNumber) || !FieldValidator.isEmail(email) || !FieldValidator.onlyText(country) || !FieldValidator.onlyText(universityName) || !FieldValidator.onlyText(workShop) || academicArea.trim().isBlank() || !FieldValidator.onlyNumber(personalNumber) || region.trim().isBlank() || !FieldValidator.onlyText(contractType) || !FieldValidator.onlyText(contractCategory)) {
        Alert emptyFieldsAlertUV = new Alert(AlertType.ERROR);
        emptyFieldsAlertUV.setTitle("Campos incorrectos o vacíos");
        emptyFieldsAlertUV.setHeaderText("Campos incorrectos o vacíos");
        emptyFieldsAlertUV.setContentText("Hay campos vacíos y/o incorrectos.");
        emptyFieldsAlertUV.showAndWait();
        return false;
    } else if (professorDAO.isProfessorRegistered(email)) {
        Alert professorDuplicatedAlert = new Alert(AlertType.INFORMATION);
        professorDuplicatedAlert.setTitle("Profesor duplicado");
        professorDuplicatedAlert.setHeaderText("Profesor duplicado");
        professorDuplicatedAlert.setContentText("Este correo ya está asociado a una cuenta");
        professorDuplicatedAlert.show();
        return false;
    }
    return true;
    }

    public static boolean validateForeignProfessorFields(Professor professor){
        String professorName = professor.getName();
        String professorPhoneNumber = professor.getPhoneNumber();
        String email = professor.getEmail();
        String country = professor.getCountry();
        String universityName = professor.getUniversityName();
        String language = professor.getLanguage();
        String workShop = professor.getWorkShop();
        ProfessorDAO professorDAO = new ProfessorDAO();

        if (!FieldValidator.isValidName(professorName) || !FieldValidator.onlyTextAndNumbers(professorPhoneNumber) || !FieldValidator.isEmail(email) || !FieldValidator.onlyText(country) || !FieldValidator.onlyText(universityName) || !FieldValidator.onlyText(language) || !FieldValidator.onlyText(workShop)) {
            Alert emptyFieldsAlertUV = new Alert(AlertType.ERROR);
            emptyFieldsAlertUV.setTitle("Campos incorrectos o vacíos");
            emptyFieldsAlertUV.setHeaderText("Campos incorrectos o vacíos");
            emptyFieldsAlertUV.setContentText("Hay campos vacíos y/o incorrectos.");
            emptyFieldsAlertUV.showAndWait();
            return false;
        } else if (professorDAO.isProfessorRegistered(email)) {
            Alert professorDuplicatedAlert = new Alert(AlertType.INFORMATION);
            professorDuplicatedAlert.setTitle("Profesor duplicado");
            professorDuplicatedAlert.setHeaderText("Profesor duplicado");
            professorDuplicatedAlert.setContentText("Este correo ya está asociado a una cuenta");
            professorDuplicatedAlert.show();
            return false;
        }
        return true;

    }



}
