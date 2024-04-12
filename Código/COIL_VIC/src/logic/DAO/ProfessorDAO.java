package logic.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataAccess.DatabaseManager;
import logic.Interfaces.IProfessor;
import logic.classes.Professor;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.util.ArrayList;
import java.sql.ResultSet;




public class ProfessorDAO implements IProfessor{

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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, addProfessorException);
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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, deleteProfessorException);
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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, updateProfessorException);
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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, searchProfessorException);
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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, searchProfessorByCountryException);
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
             Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, searchProfessorByStatus);
        }
        return professors;
    }
}




