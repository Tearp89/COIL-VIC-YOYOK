package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataAccess.DatabaseManager;
import logic.interfaces.ICollaboration;
import logic.classes.Collaboration;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.time.LocalDate;
import log.Log;

public class CollaborationDAO implements ICollaboration {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(CollaborationDAO.class);

    public int addCollaboration(Collaboration collaboration){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO colaboración(descripción, fechaFin, fechaInicio, nombreColaboración, estado) VALUES (?, ?, ?, ?, ?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, collaboration.getDescription());
            preparedStatement.setObject(2, collaboration.getFinishDate());
            preparedStatement.setObject(3, collaboration.getStartDate());
            preparedStatement.setString(4, collaboration.getCollaborationName());
            preparedStatement.setString(5,collaboration.getCollaborationStatus());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addCollaborarionException) {
            LOG.error("ERROR: ", addCollaborarionException);
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
            LOG.error("ERROR: ", deleteCollaborationException);
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
            LOG.error("ERROR: ", updateCollaborationException);
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
                    String collaborationName = resultSet.getString("nombreColaboración");
                    String description = resultSet.getString("descripción");
                    LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                    LocalDate finishDate = resultSet.getObject("fechaFin", LocalDate.class);
                    
                    collaboration = new Collaboration();
                    collaboration.setCollaborationId(idCollaboration);
                    collaboration.setCollaborationName(collaborationName);
                    collaboration.setDescription(description);
                    collaboration.setStartDate(startDate);
                    collaboration.setFinishDate(finishDate);
                    
                    collaborations.add(collaboration);
                }
            }
        }catch(SQLException SearchCollaborationException){
            LOG.error("ERROR: ", SearchCollaborationException);
        }
        return collaborations;
    }
    
    public ArrayList<Collaboration> searchCollaborationByYear(String year){
        DatabaseManager dbManager = new DatabaseManager();
        Collaboration collaboration = new Collaboration();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM colaboración WHERE YEAR(fechaInicio) = ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatemen = connection.prepareStatement(query);
            preparedStatemen.setString(1, year);
            try(ResultSet resultSet = preparedStatemen.executeQuery()){
                while(resultSet.next()){
                    int idCollaboration = resultSet.getInt("idColaboración");
                    String collaborationName = resultSet.getString("nombreColaboración");
                    String description = resultSet.getString("descripción");
                    LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                    LocalDate finishDate = resultSet.getObject("fechaFin", LocalDate.class);
                    
                    collaboration = new Collaboration();
                    collaboration.setCollaborationId(idCollaboration);
                    collaboration.setCollaborationName(collaborationName);
                    collaboration.setDescription(description);
                    collaboration.setStartDate(startDate);
                    collaboration.setFinishDate(finishDate);
                    
                    collaborations.add(collaboration);
                    
                }
            }
        } catch (SQLException searchCollaborationByYearException){
            LOG.error("ERROR: ", searchCollaborationByYearException);
        }
        
        return collaborations;
    }

    public int changeCollaborationStatus(Collaboration collaboration){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE colaboración set estado = ? WHERE idColaboración = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, collaboration.getCollaborationStatus());
            preparedStatement.setInt(2, collaboration.getCollaborationId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException changeCollaborationStatusException){
            LOG.error("ERROR: ", changeCollaborationStatusException);
        }
        return result;
    } 


    public int assignProfessorToCollaboration(int professorID, int collaborationId) throws SQLException{
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO `Colaboraciones Registradas`(Profesor_idProfesor, Colaboración_idColaboración) VALUES (?, ?)";
        int result = 0;

        try (Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, professorID);
            preparedStatement.setInt(2, collaborationId);
            result = preparedStatement.executeUpdate();

            dbManager.closeConnection();
        }

        return result;

    }

    public int assignStudentToCollaboration(String studentEmail, int collaborationId) throws SQLException{
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO participa(Estudiante_correoElectrónico, Colaboración_idColaboración) VALUES (?, ?)";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)){
                    preparedStatement.setString(1, studentEmail);
                    preparedStatement.setInt(2, collaborationId);
                    result = preparedStatement.executeUpdate();

                    dbManager.closeConnection();
                }

                return result;
    }

}
