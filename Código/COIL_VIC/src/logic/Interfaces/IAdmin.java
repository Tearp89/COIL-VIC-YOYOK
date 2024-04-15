/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;
import logic.classes.Admin;
/**
 *
 * @author isabe
 */
public interface IAdmin {
    int addAdmin(Admin admin);
    int deleteAdmin(Admin admin);
    int updateAdmin(Admin admin);
}
