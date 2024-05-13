package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import logic.DAO.CollaborationDAO;

public class AcceptedCollaborationDetailsController {
    @FXML
    Label labelCollaborationName;
    @FXML
    Label labelCollaborationDescription;
    @FXML
    Label labelStartDate;
    @FXML
    Label labelFinishDate;
    @FXML
    Label labelCollaborationGoal;
    @FXML
    Label labelNumberStudents;
    @FXML
    Label labelSubject;
    @FXML
    Label labelStudentProfile;
    @FXML
    Button buttonCancel;
    @FXML
    Button buttonUploadCollaboration;
    @FXML
    int collaborationId;

    @FXML
    void uploadCollaboration(ActionEvent event){
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        
        collaborationDAO.changeCollaborationStatus("Publicada", collaborationId);

    }

    @FXML
    void cancelUpload(ActionEvent event){

    }

    public void initialize(int collaborationId) {
        this.collaborationId = collaborationId;
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        String name = collaborationDAO.getCollaborationNameById(collaborationId);
        String description = collaborationDAO.getCollaborationDescriptionById(collaborationId);
        String startDate = collaborationDAO.getCollaborationStartDateById(collaborationId);
        String finishDate = collaborationDAO.getCollaborationFinishDateById(collaborationId);
        String collaborationGoal = collaborationDAO.getCollaborationGoalById(collaborationId);
        String collaborationSubject = collaborationDAO.getCollaborationSubjectById(collaborationId);
        int noStudents = collaborationDAO.getNumberStudentsById(collaborationId);
        String studentProfile = collaborationDAO.getStudentProfileById(collaborationId);
        labelCollaborationName.setText(name);
        labelCollaborationDescription.setText(description);
        labelStartDate.setText(startDate);
        labelFinishDate.setText(finishDate);
        labelCollaborationGoal.setText(collaborationGoal);
        labelSubject.setText(collaborationSubject);
        labelNumberStudents.setText(String.valueOf(noStudents));
        labelStudentProfile.setText(studentProfile);
        
    }

}
