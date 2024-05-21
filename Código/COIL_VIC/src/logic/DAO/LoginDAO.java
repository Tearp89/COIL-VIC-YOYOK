package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataAccess.DatabaseManager;
import log.Log;

public class LoginDAO {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(LoginDAO.class);

    public boolean validateAdmin(String username, String password) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) AS count FROM administrador WHERE usuario = ? AND contraseña = SHA2(?, 256)";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count == 1; 
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return false; 
    }

    public boolean validateProfessor(String username, String password){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) AS count FROM profesor WHERE usuario = ? AND contraseña = SHA2(?, 256)";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count == 1; 
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return false;
    }

    public boolean validateStudent(String username, String password){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) AS count FROM estudiante WHERE correo = ? AND contraseña = SHA2(?, 256)";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count == 1; 
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return false;

    }

    
    
}
