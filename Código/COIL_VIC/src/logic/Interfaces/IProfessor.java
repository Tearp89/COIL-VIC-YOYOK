/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;
import logic.classes.Professor;

/**
 *
 * @author marla
 */
public interface IProfessor {
    int addProfessorUV(Professor professor);
    int addProfessorForeign(Professor professor);
    int deleteProfessor(Professor professor);
    int updateProfessorUV(Professor professor);
    int updateProfessorForeign(Professor professor);
}

