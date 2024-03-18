/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package COIL_VIC_LOGIC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import COIL_VIC_DataAccess.DatabaseManager;
import COIL_VIC_LOGIC.Classes.Admin;
import COIL_VIC_LOGIC.Interfaces.IAdmin;
/**
 *
 * @author isabe
 */
public class AdminDAO implements IAdmin {
    public int addAdmin (Admin admin){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO administrador (idAdministrativo, contraseña, nombreAdministrador, rol, usuario) VALUES (?,?,?,?,?)";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, admin.getAdminId());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getAdminName());
            preparedStatement.setString(4, admin.getAdminRol());
            preparedStatement.setString(5, admin.getAdminUser());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addAdminException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, addAdminException);
        }
        return result;
    }
    
    public int deleteAdmin(Admin admin){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "DELETE FROM administrador where nombreAdministrador = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,admin.getAdminName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteAdminException){
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, deleteAdminException);
        }
        return result;
    }
    
    public int updateAdmin (Admin admin){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE administrador set contraseña = ?, nombreAdministrador = ?, rol = ?, usuario = ? WHERE idAdministrativo = ? ";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, admin.getAdminId());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3, admin.getAdminName());
            preparedStatement.setString(4, admin.getAdminRol());
            preparedStatement.setString(5, admin.getAdminUser());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateAdminException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, updateAdminException);
        }
        return result;
    }
}
