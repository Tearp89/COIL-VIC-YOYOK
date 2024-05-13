package GUI;
import logic.classes.*;

public class UserSessionManager {
    private static UserSessionManager instance;
    private Professor professorUser;
    private Admin adminUser;

    public static UserSessionManager getInstance(){
        if (instance == null){
            instance = new UserSessionManager();
        }
        return instance;
    }

    public void loginProfessor(Professor professor){
        this.professorUser = professor;
    }

    public void loginAdmin(Admin admin){
        this.adminUser = admin;
    }

    public void logoutProfessor(){
        this.professorUser = null;
    }

    public void logoutAdmin(){
        this.adminUser = null;
    }

    public boolean isProfessorLogIn(){
        return professorUser != null;
    }

    public boolean isAdminLogIn(){
        return adminUser != null;
    }

    public Professor getProfessorUserData(){
        return professorUser;
    }

    public Admin getAdminUserData(){
        return adminUser;
    }
}
