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
        String query = "SELECT COUNT(*) AS count FROM administrador WHERE usuario = ? AND contraseña = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count == 1; // Si el conteo es 1, significa que se encontró un usuario con las credenciales proporcionadas
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return false; // Si ocurre una excepción o no se encontró ningún usuario con las credenciales proporcionadas
    }
    
}
