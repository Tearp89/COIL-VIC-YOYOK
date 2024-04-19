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
        String query = "INSERT INTO profesor(nombreProfesor, estado, tipoProfesor, país, Universidad_idUniversidad) VALUES (?,?,?,?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getStatus());
            preparedStatement.setString(3, professor.getType());
            preparedStatement.setString(4, professor.getCountry());
            preparedStatement.setInt(5, professor.getUniversityId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addProfessorException) {
            LOG.error("ERROR: ", addProfessorException);
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
                    int adminId = resultSet.getInt("Administrador_idAdministrativo");
                    int workShopId = resultSet.getInt("Curso-Taller_idCursoTaller");
                    
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    professor.setAdministratorId(adminId);
                    professor.setWorkShopId(workShopId);
                    
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
                    int adminId = resultSet.getInt("Administrador_idAdministrativo");
                    int workShopId = resultSet.getInt("Curso-Taller_idCursoTaller");
                    
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    professor.setAdministratorId(adminId);
                    professor.setWorkShopId(workShopId);
                    
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
        String query = "SELECT * FROM profesor WHERE estado = ?";
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
                    int adminId = resultSet.getInt("Administrador_idAdministrativo");
                    int workShopId = resultSet.getInt("Curso-Taller_idCursoTaller");
                    String country = resultSet.getString("país");
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    professor.setAdministratorId(adminId);
                    professor.setWorkShopId(workShopId);
                    
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

    public int changeProfessorStatusById(Professor professor){
    DatabaseManager dbManager = new DatabaseManager();
    String query = "UPDATE profesor SET estado = ? WHERE idProfesor = ?";
    int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getStatus());
            preparedStatement.setInt(2, professor.getProfessorId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException changeProfessorStatusException){
            LOG.error("ERROR: ", changeProfessorStatusException);
    }
    return result;
    }

    public int professorRequestCollaboration(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE profesor SET idColaboración = ? WHERE idProfesor = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professor.getCollaborationId());
            preparedStatement.setInt(2, professor.getProfessorId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException professorRequestCollaboratioException){
            LOG.error("ERROR: ", professorRequestCollaboratioException);
        }
        return result;
    }
}




