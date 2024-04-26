package logic.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dataAccess.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart.Data;
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
            preparedStatement.setString(2, university.getUniversityCountry());
            preparedStatement.setString(3, university.getUniversityLanguage());
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

    public Integer getUniversityId(String university){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT idUniversidad FROM universidad WHERE nombreUniversidad = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, university);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idUniversidad");
                }
            }

        } catch(SQLException getUniversityIdException){
            LOG.error("ERROR: ",getUniversityIdException);
        }
        return null;
    }

    public boolean isUniversityRegistered(String universityName){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) FROM universidad WHERE nombreUniversidad = ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, universityName);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }

        }catch(SQLException isUniversityRegisteredException){
            LOG.error("ERROR: ", isUniversityRegisteredException);
        }
        return false;
    }

    public ObservableList<String> loadUniversities() {
        ObservableList<String> universities = FXCollections.observableArrayList();
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT nombreUniversidad FROM universidad";
        try {
            Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery(); 
            while (resultSet.next()) {
                universities.add(resultSet.getString("nombreUniversidad"));
            }
        } catch (SQLException loadUniversitiesException) {
            LOG.error("ERROR: ", loadUniversitiesException);
        }

        return universities;
    }



}