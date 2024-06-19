package logic.classes;

import java.sql.Date;
import java.time.LocalDate;

public class Collaboration {
    private int collaborationId;
    private String description;
    private LocalDate finishDate;
    private LocalDate startDate;
    private String collaborationName;
    private String collaborationStatus;
    private String collaborationGoal;
    private String subject;
    private int noStudents;
    private String studentProfile;
    private String collaborationType;


    public Collaboration() {
        
    }

    public int getCollaborationId() {
        return collaborationId;
    }

    public void setCollaborationId(int collaborationId) {
        this.collaborationId = collaborationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getCollaborationName() {
        return collaborationName;
    }

    public void setCollaborationName(String collaborationName) {
        this.collaborationName = collaborationName;
    }

    public String getCollaborationStatus(){
        return collaborationStatus;
    }

    public void setCollaborationStatus(String collaborationStatus){
        this.collaborationStatus = collaborationStatus;
    }

    public String getCollaborationGoal() {
        return collaborationGoal;
    }

    public void setCollaborationGoal(String collaborationGoal) {
        this.collaborationGoal = collaborationGoal;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNoStudents() {
        return noStudents;
    }

    public void setNoStudents(int noStudents) {
        this.noStudents = noStudents;
    }

    public String getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(String studentProfile) {
        this.studentProfile = studentProfile;
    }

    public String getCollaborationType() {
        return collaborationType;
    }

    public void setCollaborationType(String collaborationType) {
        this.collaborationType = collaborationType;
    }

}
