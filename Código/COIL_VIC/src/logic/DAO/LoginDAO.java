package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataAccess.DatabaseManager;

public class LoginDAO {

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
            // Manejo de la excepción SQL
            e.printStackTrace();
            // Puedes agregar aquí el código para registrar o manejar la excepción según tus necesidades
        }
        return false; // Si ocurre una excepción o no se encontró ningún usuario con las credenciales proporcionadas
    }
    
}
