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
        String query = "INSERT INTO colaboración(nombreColaboración, descripción, fechaInicio, fechaFin, estado, objetivo, temaInterés, noEstudiantes, perfilEstudiante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, collaboration.getCollaborationName());
            preparedStatement.setObject(2, collaboration.getDescription());
            preparedStatement.setObject(3, collaboration.getStartDate());
            preparedStatement.setObject(4, collaboration.getFinishDate());
            preparedStatement.setString(5,collaboration.getCollaborationStatus());
            preparedStatement.setString(6, collaboration.getCollaborationGoal());
            preparedStatement.setString(7, collaboration.getSubject());
            preparedStatement.setInt(8, collaboration.getNoStudents());
            preparedStatement.setString(9, collaboration.getStudentProfile());
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
        String query = "UPDATE colaboración SET nombreColaboración = ?, descripción = ?, fechaInicio = ?, fechaFin = ?, objetivo = ?, temaInterés = ?, noEstudiantes = ?, perfilEstudiante = ?, estado = ? WHERE idColaboración = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, collaboration.getCollaborationName());
            preparedStatement.setString(2, collaboration.getDescription());
            preparedStatement.setObject(3, collaboration.getStartDate());
            preparedStatement.setObject(4, collaboration.getFinishDate());
            preparedStatement.setString(5, collaboration.getCollaborationGoal());
            preparedStatement.setString(6, collaboration.getSubject());
            preparedStatement.setInt(7, collaboration.getNoStudents());
            preparedStatement.setString(8, collaboration.getStudentProfile());
            preparedStatement.setString(9, collaboration.getCollaborationStatus());
            preparedStatement.setInt(10, collaboration.getCollaborationId());

            result = preparedStatement.executeUpdate();
        } catch (SQLException updateCollaborationException) {
            LOG.error("ERROR: ", updateCollaborationException);
        }
        return result;
    }
    
    public ArrayList<Collaboration> searchCollaborationByStatus(String status){
        DatabaseManager dbManager = new DatabaseManager();
        Collaboration collaboration = new Collaboration();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM colaboración WHERE estado = ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int idCollaboration = resultSet.getInt("idColaboración");
                    String collaborationName = resultSet.getString("nombreColaboración");
                    LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                    LocalDate finishDate = resultSet.getObject("fechaFin", LocalDate.class);
                    
                    collaboration = new Collaboration();
                    collaboration.setCollaborationId(idCollaboration);
                    collaboration.setCollaborationName(collaborationName);
                    collaboration.setStartDate(startDate);
                    collaboration.setFinishDate(finishDate);
                    collaboration.setCollaborationStatus(status);
                    
                    collaborations.add(collaboration);
                }
            }
        }catch(SQLException SearchCollaborationException){
            LOG.error("ERROR: ", SearchCollaborationException);
        }
        return collaborations;
    }

    public ArrayList<Collaboration> searchCollaborationByStatusAndProfessorId(String status, int professorId) {
        DatabaseManager dbManager = new DatabaseManager();
        Collaboration collaboration = new Collaboration();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT c.* FROM colaboración c " +
                       "INNER JOIN colaboraciones_registradas cr ON c.idColaboración = cr.Colaboración_idColaboración " +
                       "WHERE c.estado = ? AND cr.Profesor_idProfesor = ?";
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, professorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idCollaboration = resultSet.getInt("idColaboración");
                    String collaborationName = resultSet.getString("nombreColaboración");
                    LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                    LocalDate finishDate = resultSet.getObject("fechaFin", LocalDate.class);
    
                    collaboration = new Collaboration();
                    collaboration.setCollaborationId(idCollaboration);
                    collaboration.setCollaborationName(collaborationName);
                    collaboration.setStartDate(startDate);
                    collaboration.setFinishDate(finishDate);
                    collaboration.setCollaborationStatus(status);
    
                    collaborations.add(collaboration);
                }
            }
        } catch (SQLException SearchCollaborationException) {
            LOG.error("ERROR: ", SearchCollaborationException);
        }
        return collaborations;
    }
    

    public ArrayList<Collaboration> searchCollaborationByStatusAndName(String status, String name){
        DatabaseManager dbManager = new DatabaseManager();
        Collaboration collaboration = new Collaboration();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM colaboración WHERE estado = ? AND nombreColaboración LIKE ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int idCollaboration = resultSet.getInt("idColaboración");
                    String collaborationName = resultSet.getString("nombreColaboración");
                    LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                    LocalDate finishDate = resultSet.getObject("fechaFin", LocalDate.class);
                    
                    collaboration = new Collaboration();
                    collaboration.setCollaborationId(idCollaboration);
                    collaboration.setCollaborationName(collaborationName);
                    collaboration.setStartDate(startDate);
                    collaboration.setFinishDate(finishDate);
                    collaboration.setCollaborationStatus(status);
                    
                    collaborations.add(collaboration);
                }
            }
        }catch(SQLException SearchCollaborationException){
            LOG.error("ERROR: ", SearchCollaborationException);
        }
        return collaborations;
    }

    public ArrayList<Collaboration> searchCollaborationByStatusNameandProfessorId(String status, String name, int professorId) {
        DatabaseManager dbManager = new DatabaseManager();
        Collaboration collaboration = new Collaboration();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT c.* FROM colaboración c " +
                       "INNER JOIN colaboraciones_registradas cr ON c.idColaboración = cr.Colaboración_idColaboración " +
                       "WHERE c.estado = ? AND c.nombreColaboración LIKE ? AND cr.Profesor_idProfesor = ?";
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, professorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idCollaboration = resultSet.getInt("idColaboración");
                    String collaborationName = resultSet.getString("nombreColaboración");
                    LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                    LocalDate finishDate = resultSet.getObject("fechaFin", LocalDate.class);
    
                    collaboration = new Collaboration();
                    collaboration.setCollaborationId(idCollaboration);
                    collaboration.setCollaborationName(collaborationName);
                    collaboration.setStartDate(startDate);
                    collaboration.setFinishDate(finishDate);
                    collaboration.setCollaborationStatus(status);
    
                    collaborations.add(collaboration);
                }
            }
        } catch (SQLException SearchCollaborationException) {
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

    public int changeCollaborationStatus(String status, int collaborationId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE colaboración set estado = ? WHERE idColaboración = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, collaborationId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException changeCollaborationStatusException){
            LOG.error("ERROR: ", changeCollaborationStatusException);
        }
        return result;
    } 


    public int assignProfessorToCollaboration(int professorID, int collaborationId) throws SQLException{
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO Colaboraciones_Registradas (Profesor_idProfesor, Colaboración_idColaboración) VALUES (?, ?)";
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

    public String getCollaboratorNameById(int collaborationId, int professorId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT p.nombreProfesor " +
                        "FROM profesor p " +  
                        "INNER JOIN colaboraciones_registradas cr ON p.idProfesor = cr.Profesor_idProfesor " +
                        "WHERE cr.Colaboración_idColaboración = ? AND p.idProfesor != ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, collaborationId);
            preparedStatement.setInt(2, professorId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString("nombreProfesor");
                }
               
            }
        }catch (SQLException getCollaborationNameByidException){
            LOG.error("ERROR:", getCollaborationNameByidException);
        }

        return null;

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

    public String getCollaborationNameById(int id){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT nombreColaboración from Colaboración WHERE idColaboración = ?";
        String result = "";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString("nombreColaboración");
                }
            }
        }catch(SQLException getCollaborationNameException){
            LOG.error("ERROR:", getCollaborationNameException);
        }
        return null;
    }

    public String getCollaborationDescriptionById (int id){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT descripción FROM Colaboración WHERE idColaboración = ?";
        String result = "";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString("descripción");
                }
            }
        } catch (SQLException getCollaborationDescriptionException){
            LOG.error("ERROR:", getCollaborationDescriptionException);
        }
        return null;
    }

    public String getCollaborationStartDateById (int id){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT fechaInicio FROM Colaboración WHERE idColaboración = ?";
        String result = "";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getDate("fechaInicio").toLocalDate().toString();
                }
            }
        } catch(SQLException getCollaborationStartDateException){
            LOG.error("ERROR:", getCollaborationStartDateException);
        }

        return null;
    }

    public String getCollaborationFinishDateById(int id){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT fechaFin FROM Colaboración WHERE idColaboración = ?";
        String result = "";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getDate("fechaFin").toLocalDate().toString();
                }
            }
        } catch(SQLException getCollaborationFinishDateException){
            LOG.error("ERROR:", getCollaborationFinishDateException);
        }

        return null;
    }

    public String getCollaborationGoalById(int id){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT objetivo FROM Colaboración WHERE idColaboración = ?";
        String result = "";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString("objetivo");
                }
            }
        } catch(SQLException getCollaborationGoalException){
            LOG.error("ERROR:", getCollaborationGoalException);
        }

        return null;

    }

    public String  getCollaborationSubjectById(int id){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT temaInterés FROM Colaboración WHERE idColaboración = ?";
        String result = "";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString("temaInterés");
                }
            }
        } catch(SQLException getCollaborationSubjectException){
            LOG.error("ERROR:", getCollaborationSubjectException);
        }

        return null;
    }

    public int getNumberStudentsById(int id){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT noEstudiantes FROM Colaboración WHERE idColaboración = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("noEstudiantes");
                }
            }
        } catch(SQLException getNoStudentsException){
            LOG.error("ERROR:", getNoStudentsException);
        }

        return 0;
    }

    public String getStudentProfileById(int id){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT perfilEstudiante FROM Colaboración WHERE idColaboración = ?";
        String result = "";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString("perfilEstudiante");
                }
            }
        } catch(SQLException getStudentProfileException){
            LOG.error("ERROR:", getStudentProfileException);
        }

        return null;
    }

}
