package COIL_VIC_LOGIC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import COIL_VIC_DataAccess.DatabaseManager;
import COIL_VIC_LOGIC.Classes.Collaboration;
import COIL_VIC_LOGIC.Interfaces.ICollaboration;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

public class CollaborationDAO implements ICollaboration {

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
        String query = "DELETE FROM colaboración WHERE idColaboración = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, collaboration.getCollaborationId());
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
            preparedStatement.setInt(5, collaboration.getCollaborationId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateCollaborationException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, updateCollaborationException);
        }
        return result;
    }
    
    public ArrayList<Collaboration> searchCollaboration(String name){
        DatabaseManager dbManager = new DatabaseManager();
        Collaboration collaboration = new Collaboration();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM colaboración WHERE nombreColaboración = ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int idCollaboration = resultSet.getInt("idColaboración");
                    String collaborarionName = resultSet.getString("nombreColaboración");
                    String description = resultSet.getString("descripción");
                    LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                    LocalDate finishDate = resultSet.getObject("fechaFin", LocalDate.class);
                    
                    collaboration = new Collaboration();
                    collaboration.setCollaborationId(idCollaboration);
                    collaboration.setDescription(description);
                    collaboration.setStartDate(startDate);
                    collaboration.setFinishDate(finishDate);
                    
                    collaborations.add(collaboration);
                }
            }
        }catch(SQLException SearchCollaborationException){
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, SearchCollaborationException);
        }
        return collaborations;
    }

}
