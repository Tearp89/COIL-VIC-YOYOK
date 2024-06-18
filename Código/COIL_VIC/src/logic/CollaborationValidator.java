package logic;

import java.time.LocalDate;

import GUI.UserSessionManager;
import dataAccess.DatabaseConnectionChecker;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import logic.DAO.CollaborationDAO;
import logic.DAO.ProfessorDAO;
import logic.classes.Collaboration;
import logic.classes.Professor;

public class CollaborationValidator {

    public static boolean validateCollaborationFields(Collaboration collaboration, CollaborationDAO collaborationDAO){
        String collaborationName = collaboration.getCollaborationName();
        String collaborationDescription = collaboration.getDescription();
        LocalDate startDate = collaboration.getStartDate();
        LocalDate finishDate = collaboration.getFinishDate();
        String collaborationGoal = collaboration.getCollaborationGoal();
        String collaborationSubject = collaboration.getSubject();
        String studentProfile = collaboration.getStudentProfile();
        String noStudentsText = String.valueOf(collaboration.getNoStudents());
        String collaborationType = collaboration.getCollaborationType();
        if(!FieldValidator.onlyText(collaborationName) ||
        !FieldValidator.onlyText(collaborationDescription) ||
        !FieldValidator.isValidDateRange(startDate, finishDate) ||
        !FieldValidator.onlyText(collaborationGoal) ||
        !FieldValidator.onlyText(collaborationSubject) ||
        !FieldValidator.onlyText(studentProfile) ||
        !FieldValidator.onlyNumber(noStudentsText)){
            Alert emptyFieldsAlert = new Alert(AlertType.ERROR);
            emptyFieldsAlert.setTitle("Campos vacíos o incorrectos");
            emptyFieldsAlert.setHeaderText("Campos vacíos o incorrectos");
            emptyFieldsAlert.setContentText("No se puede agregar la colaboración, hay campos vacíos o incorrectos");
            emptyFieldsAlert.show();
            return false;

        } else if(!collaborationDAO.validateCollaborationName(collaborationName)){
           
            
            
            int result = collaborationDAO.addCollaboration(collaboration);
            if(result == 1){
                Alert collaborationAddedAlert = new Alert(AlertType.INFORMATION);
                collaborationAddedAlert.setTitle("Colaboración enviada");
                collaborationAddedAlert.setHeaderText("colaboración enviada");
                collaborationAddedAlert.setContentText("Colaboración enviada exitosamente");
                collaborationAddedAlert.show();
                ButtonType accept = new ButtonType("Aceptar");
                collaborationAddedAlert.getButtonTypes().setAll(accept);
                Button okButton = (Button) collaborationAddedAlert.getDialogPane().lookupButton(accept);
                okButton.setOnAction(eventAssignProfessor -> {
                    if(!DatabaseConnectionChecker.isDatabaseConnected()){
                        DatabaseConnectionChecker.showNoConnectionDialog();
                        return;
                    }
                    ProfessorDAO professorDAO = new ProfessorDAO();
                    Professor professorData = UserSessionManager.getInstance().getProfessorUserData();
                    int professorId = professorDAO.getProfessorIdByUser(professorData.getUser());
                    int collaborationId = collaborationDAO.getCollaborationIdbyName(collaborationName);
                        collaborationDAO.assignProfessorToCollaboration(professorId, collaborationId);
                    
                });
                

                
            } else{
                Alert sendCollaborationErrorAlert = new Alert(AlertType.ERROR);
                sendCollaborationErrorAlert.setTitle("Error conexión");
                sendCollaborationErrorAlert.setHeaderText("Error conexión");
                sendCollaborationErrorAlert.setContentText("No se pudo conectar a la base de datos, inténtelo de nuevo más tarde");
                sendCollaborationErrorAlert.show();

            }
        } else {
                Alert duplicateNameAlert = new Alert(AlertType.ERROR);
                duplicateNameAlert.setTitle("Nombre duplicado");
                duplicateNameAlert.setHeaderText("Nombre duplicado");
                duplicateNameAlert.setContentText("No se puede agregar la colaboración el nombre no se encuentra disponible");
                duplicateNameAlert.show();
        }

    return true;
    }

}
