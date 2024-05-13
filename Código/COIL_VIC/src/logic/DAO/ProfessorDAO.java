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

    public int addProfessorUV(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO profesor(nombreProfesor, usuario, telefono, estado,"+ 
        "tipoProfesor, país, Universidad_idUniversidad, area_academica, correo, contraseña, No.Personal," +
        "region, tipoContratación, categoriaContratacion, disciplina) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getUser());
            preparedStatement.setString(3, professor.getPhoneNumber());
            preparedStatement.setString(4, professor.getStatus());
            preparedStatement.setString(5, professor.getType());
            preparedStatement.setString(6, professor.getCountry());
            preparedStatement.setInt(7, professor.getUniversityId());
            preparedStatement.setString(8, professor.getAcademicArea());
            preparedStatement.setString(9, professor.getEmail());
            preparedStatement.setString(10, professor.getPassword());
            preparedStatement.setInt(11, professor.getPersonalNumber());
            preparedStatement.setString(12, professor.getRegion());
            preparedStatement.setString(13, professor.getContractType());
            preparedStatement.setString(14, professor.getContractCategory());
            preparedStatement.setString(15, professor.getDiscipline());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addProfessorUVException) {
            LOG.error("ERROR: ", addProfessorUVException);
        }
        return result;
    }

    public int addProfessorForeign(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO profesor(nombreProfesor, usuario, telefono," + 
        "estado, país, Universidad_idUniversidad, correo, contraseña) VALUES (?,?,?,?,?,?,?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getUser());
            preparedStatement.setString(3, professor.getPhoneNumber());
            preparedStatement.setString(4, professor.getStatus());
            preparedStatement.setString(5, professor.getCountry());
            preparedStatement.setInt(6, professor.getUniversityId());
            preparedStatement.setString(7, professor.getEmail());
            preparedStatement.setString(8, professor.getPassword());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addProfessorForeignException) {
            LOG.error("ERROR: ", addProfessorForeignException);
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

    public int updateProfessorUV(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE profesor SET nombreProfesor = ?, telefono = ?, estado = ?, tipoProfesor = ?," + 
        "país = ?, Universidad_idUniversidad = ?, area_academica = ?, correo, contraseña = ?, No.Personal = ?," +
        "region = ?, tipoContratación = ?, categoriaContratacion = ?, disciplina = ?  WHERE idProfesor = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(3, professor.getPhoneNumber());
            preparedStatement.setString(4, professor.getStatus());
            preparedStatement.setString(5, professor.getType());
            preparedStatement.setString(6, professor.getCountry());
            preparedStatement.setInt(7, professor.getUniversityId());
            preparedStatement.setString(8, professor.getAcademicArea());
            preparedStatement.setString(9, professor.getEmail());
            preparedStatement.setString(10, professor.getPassword());
            preparedStatement.setInt(11, professor.getPersonalNumber());
            preparedStatement.setString(12, professor.getRegion());
            preparedStatement.setString(13, professor.getContractType());
            preparedStatement.setString(14, professor.getContractCategory());
            preparedStatement.setString(15, professor.getDiscipline());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateProfessorUVException){
            LOG.error("ERROR: ", updateProfessorUVException);
        }
        return result;
    }

    public int updateProfessorForeign(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE profesor SET nombreProfesor = ?, telefono = ?, estado = ?, tipoProfesor = ?," +
        "país = ?, Universidad_idUniversidad = ?, correo, contraseña = ? WHERE idProfesor = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getPhoneNumber());
            preparedStatement.setString(3, professor.getStatus());
            preparedStatement.setString(4, professor.getType());
            preparedStatement.setString(5, professor.getCountry());
            preparedStatement.setInt(6, professor.getUniversityId());
            preparedStatement.setString(7, professor.getEmail());
            preparedStatement.setString(8, professor.getPassword());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateProfessorForeignException){
            LOG.error("ERROR: ", updateProfessorForeignException);
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
                    
                    
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    professor.setAdministratorId(adminId);
                    
                    
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
                    
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    professor.setAdministratorId(adminId);
                    
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
                    String country = resultSet.getString("país");
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    professor.setAdministratorId(adminId);
                    
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




