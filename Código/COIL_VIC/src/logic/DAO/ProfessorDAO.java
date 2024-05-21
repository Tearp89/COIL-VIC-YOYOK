package logic.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataAccess.DatabaseManager;
import logic.interfaces.IProfessor;
import logic.classes.Professor;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.util.ArrayList;
import java.sql.ResultSet;
import log.Log;




public class ProfessorDAO implements IProfessor{
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ProfessorDAO.class);

    public int addProfessor(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO Profesor (nombreProfesor, estado, tipoProfesor, país, Universidad_idUniversidad, area_academica, correo, usuario, contraseña, telefono, curso_taller) " + 
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, SHA2(?, 256), ?, ?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getStatus());
            preparedStatement.setString(3, professor.getType());
            preparedStatement.setString(4, professor.getCountry());
            preparedStatement.setInt(5, professor.getUniversityId());
            preparedStatement.setString(6, professor.getAcademicArea());
            preparedStatement.setString(7, professor.getEmail());
            preparedStatement.setString(8, professor.getUser());
            preparedStatement.setString(9, professor.getPassword());
            preparedStatement.setString(10, professor.getPhoneNumber());
            preparedStatement.setString(11, professor.getWorkShop());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addProfessorException) {
            LOG.error(addProfessorException);
        }
        return result;
    }

    public int deleteProfessor(Professor professor){
        DatabaseManager dbMananager = new DatabaseManager();
        String query = "DELETE FROM  profesor WHERE idProfesor = ?";
        int result = 0;
        try {
            Connection connection = dbMananager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professor.getProfessorId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteProfessorException) {
            LOG.error("ERROR: ", deleteProfessorException);
        }
        
        return result;
    }

    public int updateProfessor(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE profesor SET nombreProfesor = ?, estado = ?, tipoProfesor = ?, país = ?, Universidad_idUniversidad WHERE idProfesor = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getStatus());
            preparedStatement.setString(3, professor.getType());
            preparedStatement.setString(4, professor.getCountry());
            preparedStatement.setInt(5, professor.getProfessorId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateProfessorException){
            LOG.error("ERROR: ", updateProfessorException);
        }
        return result;
    }
    
    
    public ArrayList<Professor> searchProfessor (int universityId){
        Professor professor = new Professor();
        ArrayList<Professor> professors = new ArrayList<>();
        String query = "SELECT * FROM profesor WHERE Universidad_idUniversidad = ?";
        DatabaseManager dbManager = new DatabaseManager();
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, universityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int idProfesor = resultSet.getInt("idProfesor");
                    String name = resultSet.getString("nombreProfesor");
                    String status = resultSet.getString("estado");
                    String type = resultSet.getString("tipoProfesor");
                    String country = resultSet.getString("país");
    
                    
                    
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    
                    
                    
                    professors.add(professor);
                }
            }
        } catch (SQLException searchProfessorException){
            LOG.error("ERROR: ", searchProfessorException);
        }
        
        return professors;
    }
    
    public ArrayList<Professor> searchProfessorByCountry (String country){
        Professor professor = new Professor();
        ArrayList<Professor> professors = new ArrayList<>();
        String query = "SELECT * FROM profesor WHERE país = ?";
        DatabaseManager dbManager = new DatabaseManager();
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int idProfesor = resultSet.getInt("idProfesor");
                    String name = resultSet.getString("nombreProfesor");
                    String status = resultSet.getString("estado");
                    String type = resultSet.getString("tipoProfesor");
                    int universityId = resultSet.getInt("Universidad_idUniversidad");                    
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    
                    professors.add(professor);
                }
            }
            
        } catch (SQLException searchProfessorByCountryException){
            LOG.error("ERROR: ", searchProfessorByCountryException);
        }
        
        return professors;
    }
    
    public ArrayList<Professor> searchProfessorByStatus (String status){
        Professor professor = new Professor();
        ArrayList<Professor> professors = new ArrayList<>();
        String query = " select * from profesor where estado = ?";
        DatabaseManager dbManager = new DatabaseManager();
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int idProfesor = resultSet.getInt("idProfesor");
                    String name = resultSet.getString("nombreProfesor");
                    String type = resultSet.getString("tipoProfesor");
                    int universityId = resultSet.getInt("Universidad_idUniversidad");
                    String country = resultSet.getString("país");
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    
                    professors.add(professor);
                }
            }
        }catch (SQLException searchProfessorByStatus){
            LOG.error("ERROR: ", searchProfessorByStatus);
        }
        return professors;
    }

    public ArrayList<Professor> searchProfessorByCollaboration(int collaborationId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT idProfesor, nombreProfesor, estado, Universidad_idUniversidad FROM profesor WHERE idColaboración = ?";
        Professor professor = new Professor();
        ArrayList<Professor> professors = new ArrayList<>();
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, collaborationId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int professorId = resultSet.getInt("idProfesor");
                    String name = resultSet.getString("nombreProfesor");
                    String status = resultSet.getString("estado");
                    int universityId = resultSet.getInt("Universidad_idUniversidad");
                    professor = new Professor();
                    professor.setProfessorId(professorId);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setUniversityId(universityId);

                    professors.add(professor);
                }
            }
        }  catch (SQLException searchProfessorByCollaboration){
            LOG.error("ERROR: ", searchProfessorByCollaboration);
        }
        return professors;  
    }

    public int changeProfessorStatusById(String status, int professorId){
    DatabaseManager dbManager = new DatabaseManager();
    String query = "UPDATE profesor SET estado = ? WHERE idProfesor = ?";
    int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, professorId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException changeProfessorStatusException){
            LOG.error("ERROR: ", changeProfessorStatusException);
    }
    return result;
    }

    public int professorRequestCollaboration(int idColaboración, int idProfesor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO solicitud_Colaboración (idColaboración, idProfesor) VALUES (?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idColaboración);
            preparedStatement.setInt(2, idProfesor);
            result = preparedStatement.executeUpdate();
        } catch (SQLException professorRequestCollaboratioException){
            LOG.error("ERROR: ", professorRequestCollaboratioException);
        }
        return result;
    }

    public int getProfessorIdByUser(String user){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT idProfesor FROM Profesor WHERE usuario = ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("IdProfesor");
                }
            }

        } catch(SQLException getProfessorIdByUserException){
            LOG.error("ERROR:", getProfessorIdByUserException);
        }
        return 0;
    }

    public String getProfessorNameByUser(String user){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT nombreProfesor FROM Profesor WHERE usuario = ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getString("nombreProfesor");
                }
            }

        } catch(SQLException getProfessorNameByUserException){
            LOG.error("ERROR:", getProfessorNameByUserException);
        }
        return null;
    }
    //TODO: Hacer test de este método
    
}




