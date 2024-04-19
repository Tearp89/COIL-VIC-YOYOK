package logic.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import dataAccess.DatabaseManager;
import log.Log;
import logic.interfaces.IUniversity;
import logic.classes.University;

public class UniversityDAO implements IUniversity{
    private static final org.apache.log4j.Logger LOG = Log.getLogger(UniversityDAO.class);
   
    public int addUniversity(University university){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO universidad(nombreUniversidad, país, idioma) VALUES (?,?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, university.getUniversityName());
            preparedStatement.setString(2, university.getUniversityLanguage());
            preparedStatement.setString(3, university.getUniversityCountry());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addUniversityException) {
            LOG.error("ERROR: ", addUniversityException);
        }
        return result;
    }
    
    public int deleteUniversity (University university){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "DELETE FROM universidad WHERE nombreUniversidad = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, university.getUniversityName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteUniversityException) {
            LOG.error("ERROR: ", deleteUniversityException);
        }
        
        return result;
            
        }
    
    public int updateUniversity(University university){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE universidad SET nombreUniversidad = ?, idioma = ?, pais = ? WHERE idUniversidad = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, university.getUniversityName());
            preparedStatement.setString(3, university.getUniversityLanguage());
            preparedStatement.setString(4, university.getUniversityCountry());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateUniversityException){
            LOG.error("ERROR: ", updateUniversityException);
        }
        return result;
    }



}