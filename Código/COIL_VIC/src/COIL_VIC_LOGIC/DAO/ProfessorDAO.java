package COIL_VIC_LOGIC.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import COIL_VIC_DataAccess.DatabaseManager;
import COIL_VIC_LOGIC.Classes.Professor;
import COIL_VIC_LOGIC.Interfaces.IProfessor;

public class ProfessorDAO implements IProfessor{

    public int addProfessor(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO profesor(nombreProfesor, estado, tipoProfesor, país, Universidad_idUniversidad) VALUES (?,?,?,?,?,?)";
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
        String query = "DELETE FROM  profesor WHERE nombreProfesor = ?";
        int result = 0;
        try {
            Connection connection = dbMananager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteProfessorException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, deleteProfessorException);
        }
        
        return result;
    }

    public int updateProfessor(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE profesor SET nombreProfesor = ?, estado = ?, tipoProfesor = ?, pais = ? WHERE idProfesor = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getStatus());
            preparedStatement.setString(3, professor.getType());
            preparedStatement.setString(4, professor.getCountry());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateProfessorException){
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, updateProfessorException);
        }
        return result;
    }


}

