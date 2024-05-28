/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.util.ArrayList;
import logic.DAO.CollaborationStatsDAO;

/**
 *
 * @author daur0
 */
public class AcademicAreaData {
    private String academicArea;
    private int professorCount;
    private int studentCount;

    public AcademicAreaData(String academicArea, int studentCount, int professorCount) {
        this.academicArea = academicArea;
        this.professorCount = professorCount;
        this.studentCount = studentCount;
    }

    public String getAcademicArea() {
        return academicArea;
    }

    public int getProfessorCount() {
        return professorCount;
    }

    public int getStudentCount() {
        return studentCount;
    }
    
    public static ArrayList<AcademicAreaData> getUsers(String year) {
        CollaborationStatsDAO collaborationStatsDAO = new CollaborationStatsDAO();
        ArrayList<AcademicAreaData> academicAreaList = new ArrayList();
        academicAreaList.add(new AcademicAreaData("Humanidades", collaborationStatsDAO.countStudentsByAcademicAreaAndYear("Humanidades", year), collaborationStatsDAO.countProfessorsByAcademicAreaAndYear("Humanidades", year)));
        academicAreaList.add(new AcademicAreaData("Técnica", collaborationStatsDAO.countStudentsByAcademicAreaAndYear("Técnica", year), collaborationStatsDAO.countProfessorsByAcademicAreaAndYear("Técnica", year)));
        academicAreaList.add(new AcademicAreaData("Económico-Administrativo", collaborationStatsDAO.countStudentsByAcademicAreaAndYear("Económico-Administrativo", year), collaborationStatsDAO.countProfessorsByAcademicAreaAndYear("Económico-Administrativo", year)));
        academicAreaList.add(new AcademicAreaData("Ciencias de la salud", collaborationStatsDAO.countStudentsByAcademicAreaAndYear("Ciencias de la salud", year), collaborationStatsDAO.countProfessorsByAcademicAreaAndYear("Ciencias de la salud", year)));
        academicAreaList.add(new AcademicAreaData("Biológico-Agropecuarias", collaborationStatsDAO.countStudentsByAcademicAreaAndYear("Biológico-Agropecuarias", year), collaborationStatsDAO.countProfessorsByAcademicAreaAndYear("Biológico-Agropecuarias", year)));
        academicAreaList.add(new AcademicAreaData("AFBG", collaborationStatsDAO.countStudentsByAcademicAreaAndYear("AFBG", year), collaborationStatsDAO.countProfessorsByAcademicAreaAndYear("AFBG", year)));
        academicAreaList.add(new AcademicAreaData("DGRI", collaborationStatsDAO.countStudentsByAcademicAreaAndYear("DGRI", year), collaborationStatsDAO.countProfessorsByAcademicAreaAndYear("DGRI", year)));
        return academicAreaList;
    }
}
