/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dataAccess.DatabaseManager;
import log.Log;
import logic.interfaces.IAdmin;
import logic.classes.Admin;
/**
 *
 * @author isabe
 */
public class AdminDAO implements IAdmin {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(AdminDAO.class);
    
    public int addAdmin (Admin admin){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO administrador (contraseña, nombreAdministrador, rol, usuario) VALUES (SHA2(?, 256),?,?,?)";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, admin.getPassword());
            preparedStatement.setString(2, admin.getAdminName());
            preparedStatement.setString(3, admin.getAdminRol());
            preparedStatement.setString(4, admin.getAdminUser());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addAdminException) {
            LOG.error("ERROR: ", addAdminException);
            LOG.warn(addAdminException.getMessage());
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
            LOG.error("ERROR: ", deleteAdminException);
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
            preparedStatement.setString(1, admin.getPassword());
            preparedStatement.setString(2, admin.getAdminName());
            preparedStatement.setString(3, admin.getAdminRol());
            preparedStatement.setString(4, admin.getAdminUser());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateAdminException) {
            LOG.error("ERROR: ", updateAdminException);
        }
        return result;
    }

    public String getAdminNameByUser(String user){
        String name = null;
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT nombreAdministrador FROM administrador WHERE usuario = ? ";
        try {
            Connection connection = dbManager.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, user);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    name = resultSet.getString("nombreAdministrador");
                }
                
            }
        } catch (SQLException getAdminNameByUserException) {
            LOG.error(getAdminNameByUserException);
            getAdminNameByUserException.printStackTrace();
        }   

        return name;
    }
}
