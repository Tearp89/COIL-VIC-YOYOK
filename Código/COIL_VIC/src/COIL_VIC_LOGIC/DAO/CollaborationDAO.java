package COIL_VIC_LOGIC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import COIL_VIC_DataAccess.DatabaseManager;
import COIL_VIC_LOGIC.Classes.Collaboration;

public class CollaborationDAO {

    public int addCollaboration(Collaboration collaboration){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO colaboración(descripción, fechaFin, fechaInicio, nombreColaboración) VALUES (?, ?, ?, ?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, collaboration.getDescription());
            preparedStatement.setObject(2, collaboration.getFinishDate());
            preparedStatement.setObject(3, collaboration.getStartDate());
            preparedStatement.setString(4, collaboration.getCollaborationName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addCollaborarionException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, addCollaborarionException);
        }

        return result;
    }

    public int deleteCollaboration(Collaboration collaboration){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "DELETE FROM colaboración WHERE nombreColaboración = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, collaboration.getCollaborationName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteCollaborationException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, deleteCollaborationException);
        }
        return result;
    }

    public int updateCollaboration(Collaboration collaboration){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE colaboración SET descripción = ?, fechaFin = ?, fechaInicio = ?, nombreColaboración = ? WHERE idColaboración = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, collaboration.getDescription());
            preparedStatement.setObject(2, collaboration.getFinishDate());
            preparedStatement.setObject(3, collaboration.getStartDate());
            preparedStatement.setString(4, collaboration.getCollaborationName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateCollaborationException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, updateCollaborationException);
        }
        return result;
    }

}
