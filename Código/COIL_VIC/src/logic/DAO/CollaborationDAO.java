package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dataAccess.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.interfaces.ICollaboration;
import logic.classes.Collaboration;
import logic.classes.Professor;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.time.LocalDate;
import log.Log;

public class CollaborationDAO implements ICollaboration {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(CollaborationDAO.class);

    public int addCollaboration(Collaboration collaboration) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO colaboración(nombreColaboración, descripción, fechaInicio, fechaFin, estado, objetivo, temaInterés, noEstudiantes, perfilEstudiante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, collaboration.getCollaborationName());
            preparedStatement.setObject(2, collaboration.getDescription());
            preparedStatement.setObject(3, collaboration.getStartDate());
            preparedStatement.setObject(4, collaboration.getFinishDate());
            preparedStatement.setString(5, collaboration.getCollaborationStatus());
            preparedStatement.setString(6, collaboration.getCollaborationGoal());
            preparedStatement.setString(7, collaboration.getSubject());
            preparedStatement.setInt(8, collaboration.getNoStudents());
            preparedStatement.setString(9, collaboration.getStudentProfile());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addCollaborationException) {
            LOG.error("ERROR: ", addCollaborationException);
        }
        return result;
    }

    public int deleteCollaboration(Collaboration collaboration) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "DELETE FROM colaboración WHERE idColaboración = ?";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, collaboration.getCollaborationId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteCollaborationException) {
            LOG.error("ERROR: ", deleteCollaborationException);
        }
        return result;
    }

    public int updateCollaboration(Collaboration collaboration) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE colaboración SET nombreColaboración = ?, descripción = ?, fechaInicio = ?, fechaFin = ?, objetivo = ?, temaInterés = ?, noEstudiantes = ?, perfilEstudiante = ?, estado = ? WHERE idColaboración = ?";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
    
    public ArrayList<Collaboration> searchCollaborationByStatus(String status) {
        DatabaseManager dbManager = new DatabaseManager();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM colaboración WHERE estado = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Collaboration collaboration = new Collaboration();
                    collaboration.setCollaborationId(resultSet.getInt("idColaboración"));
                    collaboration.setCollaborationName(resultSet.getString("nombreColaboración"));
                    collaboration.setStartDate(resultSet.getObject("fechaInicio", LocalDate.class));
                    collaboration.setFinishDate(resultSet.getObject("fechaFin", LocalDate.class));
                    collaboration.setCollaborationStatus(status);
                    collaborations.add(collaboration);
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return collaborations;
    }

    public ArrayList<Collaboration> searchCollaborationByStatusAndProfessorId(String status, int professorId) {
        DatabaseManager dbManager = new DatabaseManager();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT c.* FROM colaboración c INNER JOIN colaboraciones_registradas cr ON c.idColaboración = cr.Colaboración_idColaboración WHERE c.estado = ? AND cr.Profesor_idProfesor = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, professorId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Collaboration collaboration = new Collaboration();
                    collaboration.setCollaborationId(resultSet.getInt("idColaboración"));
                    collaboration.setCollaborationName(resultSet.getString("nombreColaboración"));
                    collaboration.setStartDate(resultSet.getObject("fechaInicio", LocalDate.class));
                    collaboration.setFinishDate(resultSet.getObject("fechaFin", LocalDate.class));
                    collaboration.setCollaborationStatus(status);
                    collaborations.add(collaboration);
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return collaborations;
    }
    

    public ArrayList<Collaboration> searchCollaborationByStatusAndName(String status, String name){
        DatabaseManager dbManager = new DatabaseManager();
        Collaboration collaboration = new Collaboration();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM colaboración WHERE estado = ? AND (nombreColaboración  LIKE ? OR temaInterés LIKE ?)";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, name);
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, collaborationId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException changeCollaborationStatusException){
            LOG.error("ERROR: ", changeCollaborationStatusException);
        }
        return result;
    } 


    public int assignProfessorToCollaboration(int professorID, int collaborationId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO Colaboraciones_Registradas (Profesor_idProfesor, Colaboración_idColaboración) VALUES (?, ?)";
        int result = 0;

        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, professorID);
            preparedStatement.setInt(2, collaborationId);
            result = preparedStatement.executeUpdate();

            dbManager.closeConnection();
        } catch (SQLException assignProfessorToCollaborationException){
            LOG.error("ERROR:", assignProfessorToCollaborationException);
        }

        return result;

    }

    public String getCollaboratorNameById(int collaborationId, int professorId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT p.nombreProfesor " +
                        "FROM profesor p " +  
                        "INNER JOIN colaboraciones_registradas cr ON p.idProfesor = cr.Profesor_idProfesor " +
                        "WHERE cr.Colaboración_idColaboración = ? AND p.idProfesor != ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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

    public int assignStudentToCollaboration(String studentEmail, int collaborationId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO participa(Estudiante_correoElectrónico, Colaboración_idColaboración) VALUES (?, ?)";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, studentEmail);
            preparedStatement.setInt(2, collaborationId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException assignStudentToCollaboraException){
            LOG.error("ERROR:", assignStudentToCollaboraException);
        }
            return result;
    }

    public String getCollaborationNameById(int id){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT nombreColaboración from Colaboración WHERE idColaboración = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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

    public int getCollaborationIdbyName(String collaborationName) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT idColaboración FROM Colaboración WHERE nombreColaboración = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, collaborationName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idColaboración");
                }
            }
        } catch (SQLException getCollaborationIdException) {
            LOG.error("ERROR:", getCollaborationIdException);
        }
        return 0;
    }
    

    public boolean validateCollaborationName(String collaborationName){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) AS count FROM colaboración WHERE nombreColaboración = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, collaborationName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count == 1; 
                }
            }
        } catch (SQLException validateCollaborationNameException) {
            LOG.error("ERROR: ", validateCollaborationNameException);
        }
        return false; 
    }


    public int changeRequestStatus(int professorId, int collaborationId, String status){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE solicitud_colaboración SET estado = ? WHERE idColaboración = ? AND idProfesor = ?";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, collaborationId);
            preparedStatement.setInt(3, professorId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException changeRequestStatusException){
            LOG.error("ERROR: ", changeRequestStatusException);
        }
        return result;
    }
    

    
    public int changeRequestStatusByNotChosen(int professorId, int collaborationId, String status){
        DatabaseManager dbManager = new DatabaseManager();
        String updateQuery = "UPDATE solicitud_colaboración SET estado = ? WHERE idColaboración = ? AND idProfesor != ?";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, "No seleccionado");
            updateStatement.setInt(2, collaborationId);
            updateStatement.setInt(3, professorId);
            result = updateStatement.executeUpdate();
        } catch (SQLException changeRequestStatusException){
            LOG.error("ERROR: ", changeRequestStatusException);
        }
        return result;
    }

    public boolean isStudentAssignedToCollaboration(String studentEmail, int collaborationId) {
        String query = "SELECT COUNT(*) FROM participa WHERE Estudiante_correoElectrónico = ? AND Colaboración_idColaboración = ?";
        boolean isAssigned = false;
        DatabaseManager dbManager = new DatabaseManager();
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, studentEmail);
            preparedStatement.setInt(2, collaborationId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isAssigned = resultSet.getInt(1) > 0;
            }
        } catch (SQLException studentValidationException) {
            LOG.error("ERROR:", studentValidationException);
        }

        return isAssigned;
    }

    public ArrayList<Professor> getProfessorsWithCollaborationRequests(int collaborationId) {
    ArrayList<Professor> professors = new ArrayList<>();
    String query = "SELECT p.* FROM profesor p JOIN solicitud_colaboración sc ON p.idProfesor = sc.idProfesor WHERE sc.idColaboración = ?";
    DatabaseManager dbManager = new DatabaseManager();
    try (Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, collaborationId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Professor professor = new Professor();
            professor.setProfessorId(resultSet.getInt("idProfesor"));
            professor.setName(resultSet.getString("nombreProfesor"));
            professor.setPhoneNumber(resultSet.getString("telefono"));
            professor.setCountry(resultSet.getString("país"));
            
            professors.add(professor);
        }
    } catch (SQLException collaboratorsRequestException) {
        LOG.error("ERROR:", collaboratorsRequestException);
    }

    return professors;
}

    public boolean validateCollaborationProfessorsLimit(int collaborationId) {
        boolean isValid = false;
        String query = "SELECT COUNT(Profesor_idProfesor) FROM colaboraciones_registradas WHERE Colaboración_idColaboración = ?";
        DatabaseManager dbManager = new DatabaseManager();
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, collaborationId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int professorsCount = resultSet.getInt(1);
                isValid = professorsCount < 2; 
            }
        } catch (SQLException validateLimitException) {
            LOG.error("ERROR:", validateLimitException);
        }

        return isValid;
    }

    public boolean isProfessorInCollaboration(int professorId) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(Profesor_idProfesor) FROM colaboraciones_registradas WHERE Profesor_idProfesor = ?";
        boolean isInCollaboration = false;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, professorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                isInCollaboration = count > 0;
            }
        } catch (SQLException isProfessorInCollaborationException) {
            LOG.error("ERROR:", isProfessorInCollaborationException);
            
        }
        return isInCollaboration;
    }

    public ArrayList<Collaboration> getCollaborationsByStudentEmail(String studentEmail) {
        DatabaseManager dbManager = new DatabaseManager();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT c.idColaboración, c.nombre " +
                        "FROM colaboraciones_registradas cr " +
                        "JOIN Colaboración c ON cr.Colaboración_idColaboración = c.idColaboración " +
                        "WHERE cr.Estudiante_correoElectrónico = ?";

        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, studentEmail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int collaborationId = resultSet.getInt("idColaboración");
                String name = resultSet.getString("nombreColaboración");
                Collaboration collaboration = new Collaboration();
                collaboration.setCollaborationId(collaborationId);
                collaboration.setCollaborationName(name);
                collaborations.add(collaboration);
            }
        } catch (SQLException getCollaborationsByStudentEmailException) {
            LOG.error("ERROR:", getCollaborationsByStudentEmailException);
        }

        return collaborations;
    }


    public ObservableList<String> loadSubjects(){
        ObservableList<String> subjects = FXCollections.observableArrayList();
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT DISTINCT temaInterés FROM Colaboración";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                subjects.add(resultSet.getString("temaInterés"));
            }
        } catch (SQLException loadSubjectsExceptions) {
            LOG.error("ERROR:", loadSubjectsExceptions);
        }

        return subjects;
    }

    public ArrayList<Collaboration> getUnreviewedCollaborationsByProfessor(int professorId){
        DatabaseManager dbManager = new DatabaseManager();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT c.* " +
                        "FROM colaboración c " +
                        "JOIN colaboraciones_registradas cr ON c.idColaboración = cr.Colaboración_idColaboración " +
                        "LEFT JOIN retroalimentación_académicos r " +
                        "ON c.idColaboración = r.idColaboración AND r.idProfesor = ? " +
                        "WHERE r.idColaboración IS NULL AND cr.Profesor_idProfesor = ? AND c.estado = 'Cerrada' ";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, professorId);
            preparedStatement.setInt(2, professorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int collaborationId = resultSet.getInt("idColaboración");
                String collaborationName = resultSet.getString("nombreColaboración");
                LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                LocalDate finsihDate = resultSet.getObject("fechaFin", LocalDate.class);
                Collaboration collaboration = new Collaboration();
                collaboration.setCollaborationId(collaborationId);
                collaboration.setCollaborationName(collaborationName);
                collaboration.setStartDate(startDate);
                collaboration.setFinishDate(finsihDate);
                collaborations.add(collaboration);
                
            }
        } catch(SQLException getUnreviewedCollaborationsByProfessorException){
            LOG.error("ERROR:", getUnreviewedCollaborationsByProfessorException);
        }

        return collaborations;
    }

    public ArrayList<Collaboration> getUnreviewedCollaborationsByAdmin(int adminId){
        DatabaseManager dbManager = new DatabaseManager();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "Select c.*" +
                        "FROM colaboración c " +
                        "LEFT JOIN retroalimentación_administrativos r " +
                        "ON c.idColaboración = r.idColaboración AND r.idAdministrativo = ? " +
                        "WHERE r.idColaboración IS NULL AND c.estado = 'Cerrada' ";
        try (Connection connection = dbManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, adminId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int collaborationId = resultSet.getInt("idColaboración");
                String collaborationName = resultSet.getString("nombreColaboración");
                LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                LocalDate finsihDate = resultSet.getObject("fechaFin", LocalDate.class);
                Collaboration collaboration = new Collaboration();
                collaboration.setCollaborationId(collaborationId);
                collaboration.setCollaborationName(collaborationName);
                collaboration.setStartDate(startDate);
                collaboration.setFinishDate(finsihDate);
                collaborations.add(collaboration);
                
            }
        } catch(SQLException getUnreviewedCollaborationsByProfessorException){
            LOG.error("ERROR:", getUnreviewedCollaborationsByProfessorException);
        }

        return collaborations;
    }

    public ArrayList<Collaboration> getUnreviewedCollaborationsByStudent(String email){
        DatabaseManager dbManager = new DatabaseManager();
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT c.* " +
                        "FROM colaboración c " +
                        "JOIN participa p ON c.idColaboración = p.Colaboración_idColaboración " +
                        "LEFT JOIN retroalimentación_estudiantes r " +
                        "ON c.idColaboración = r.idColaboración AND r.correoElectrónico = ? " +
                        "WHERE r.idColaboración IS NULL AND p.Estudiante_correoElectrónico = ? AND c.estado = 'Cerrada' ";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int collaborationId = resultSet.getInt("idColaboración");
                String collaborationName = resultSet.getString("nombreColaboración");
                LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                LocalDate finsihDate = resultSet.getObject("fechaFin", LocalDate.class);
                Collaboration collaboration = new Collaboration();
                collaboration.setCollaborationId(collaborationId);
                collaboration.setCollaborationName(collaborationName);
                collaboration.setStartDate(startDate);
                collaboration.setFinishDate(finsihDate);
                collaborations.add(collaboration);
                
            }
        } catch(SQLException getUnreviewedCollaborationsByProfessorException){
            LOG.error("ERROR:", getUnreviewedCollaborationsByProfessorException);
        }

        return collaborations;
    }
}
