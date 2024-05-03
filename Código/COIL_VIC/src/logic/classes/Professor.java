/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.classes;

/**
 *
 * @author marla
 */
public class Professor {
    private String name;
    private String status;
    private String type;
    private String country;
    private int professorId;
    private int universityId;
    private int administratorId;
    private int workShopId;
    private int collaborationId;
    private String user;
    private String email;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;
    private String academicArea;

    public String getAcademicArea() {
        return academicArea;
    }

    public void setAcademicArea(String academicArea) {
        this.academicArea = academicArea;
    }

    public Professor() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public int getProfessorId(){
        return professorId;
    }
    
    public void setProfessorId(int professorId){
        this.professorId = professorId;
    }
    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }

    public int getWorkShopId() {
        return workShopId;
    }

    public void setWorkShopId(int workShopId) {
        this.workShopId = workShopId;
    }

    public int getCollaborationId(){
        return collaborationId;
    }

    public void setCollaborationId(int collaborationId){
        this.collaborationId = collaborationId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
