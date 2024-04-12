/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.Interfaces;

import logic.classes.Workshop;

/**
 *
 * @author isabe
 */
public interface IWorkshop {
    int addWorkshop(Workshop workshop);
    int deleteWorkshop(Workshop workshop);
    int updateWorkshop(Workshop workshop);
}
