package logic.classes;

public class Feedback {
    int professorId;
    int adminId;
    String email;
    String comments;
    int grade;
    public int collaborationId;

    public int getProfessorId() {
        return professorId;
    }
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public int getGrade() {
        return grade;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getCollaborationId() {
        return collaborationId;
    }
    public void setCollaborationId(int collaborationId) {
        this.collaborationId = collaborationId;
    }
    

}
