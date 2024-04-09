/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COIL_VIC_LOGIC.Classes;

/**
 *
 * @author isabe
 */

public class Syllabus{
    private int syllabusId;
    private String activity;
    private String description;
    private String nameOfResponsable;

    public Syllabus(){

    }
    public int getSyllabusId() {
        return syllabusId;
    }
    
    public void setSyllabusId(int syllabusId) {
        this.syllabusId = syllabusId;
    }
    
    public String getActivity() {
        return activity;
    }
    
    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameOfResponsable() {
        return nameOfResponsable;
    }

    public void SetNameOfResponsable(String nameOfResponsable) {
        this.nameOfResponsable = nameOfResponsable;
    }

}