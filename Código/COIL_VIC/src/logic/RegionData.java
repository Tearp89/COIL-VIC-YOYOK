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
public class RegionData {
    private String region;
    private int professorsCount;
    private int studentsCount;

    public RegionData(String region, int studentsCount, int professorsCount) {
        this.region = region;
        this.studentsCount = studentsCount;
        this.professorsCount = professorsCount;
    }

    
    public static ArrayList<RegionData> getUsers(String year) {
        CollaborationStatsDAO collaborationStatsDAO = new CollaborationStatsDAO();
        ArrayList<RegionData> regionDataList = new ArrayList<>();
        regionDataList.add(new RegionData("Xalapa", collaborationStatsDAO.countStudentsByRegionAndYear("Xalapa", year), collaborationStatsDAO.countProfessorsByRegionAndYear("Xalapa", year)));
        regionDataList.add(new RegionData("Orizaba-Cordoba", collaborationStatsDAO.countStudentsByRegionAndYear("Orizaba-Cordoba", year), collaborationStatsDAO.countProfessorsByRegionAndYear("Orizaba-Cordoba", year)));
        regionDataList.add(new RegionData("Poza Rica-Tuxpan", collaborationStatsDAO.countStudentsByRegionAndYear("Poza Rica-Tuxpan", year), collaborationStatsDAO.countProfessorsByRegionAndYear("Poza Rica-Tuxpan", year)));
        regionDataList.add(new RegionData("Veracruz", collaborationStatsDAO.countStudentsByRegionAndYear("Veracruz", year), collaborationStatsDAO.countProfessorsByRegionAndYear("Veracruz", year)));
        regionDataList.add(new RegionData("Coatzacoalcos", collaborationStatsDAO.countStudentsByRegionAndYear("Coatzacoalcos", year), collaborationStatsDAO.countProfessorsByRegionAndYear("Coatzacoalcos", year)));
        return regionDataList;
    }


    public String getRegion() {
        return region;
    }


    public void setRegion(String region) {
        this.region = region;
    }


    public int getProfessorsCount() {
        return professorsCount;
    }


    public void setProfessorsCount(int professorsCount) {
        this.professorsCount = professorsCount;
    }


    public int getStudentsCount() {
        return studentsCount;
    }


    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }
}