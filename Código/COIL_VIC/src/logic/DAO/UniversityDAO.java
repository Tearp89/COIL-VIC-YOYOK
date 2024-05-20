package logic.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String query = "UPDATE universidad SET nombreUniversidad = ?, idioma = ?, país = ? WHERE idUniversidad = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, university.getUniversityName());
            preparedStatement.setString(2, university.getUniversityLanguage());
            preparedStatement.setString(3, university.getUniversityCountry());
            preparedStatement.setInt(4, university.getUniversityId());
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

    public ArrayList<University> searchUniversity(){
        University university = new University();
        ArrayList<University> universities = new ArrayList<>();
        String query = "SELECT * FROM universidad";
        DatabaseManager dbManager = new DatabaseManager();
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int universityId = resultSet.getInt("idUniversidad");
                    String universityName = resultSet.getString("nombreUniversidad");
                    String universityLanguage = resultSet.getString("idioma");
                    String universityCountry = resultSet.getString("país");

                    university = new University();
                    university.setUniversityId(universityId);
                    university.setUniversityName(universityName);
                    university.setUniversityCountry(universityCountry);
                    university.setUniversityLanguage(universityLanguage);

                    universities.add(university);
                }
            }
        } catch (SQLException searchUniversityException){
            LOG.error("ERROR:", searchUniversityException);
        }

        return universities;
    }

    public ArrayList<University> searchUniversityByName(String name){
        University university = new University();
        ArrayList<University> universities = new ArrayList<>();
        String query = "SELECT * FROM universidad WHERE nombreUniversidad LIKE ?";
        DatabaseManager dbManager = new DatabaseManager();
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int universityId = resultSet.getInt("idUniversidad");
                    String universityName = resultSet.getString("nombreUniversidad");
                    String universityLanguage = resultSet.getString("idioma");
                    String universityCountry = resultSet.getString("país");

                    university = new University();
                    university.setUniversityId(universityId);
                    university.setUniversityName(universityName);
                    university.setUniversityCountry(universityCountry);
                    university.setUniversityLanguage(universityLanguage);

                    universities.add(university);
                }
            }
        } catch (SQLException searchUniversityException){
            LOG.error("ERROR:", searchUniversityException);
        }

        return universities;
    }

    public String getUniversityNameById(int universityId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT nombreUniversidad FROM universidad WHERE idUniversidad = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, universityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("nombreUniversidad");
                }
            }

        } catch(SQLException getUniversityNameByIdException){
            LOG.error("ERROR: ",getUniversityNameByIdException);
        }
        return null;
    }

    public String getUniversityLanguageById(int universityId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT idioma FROM universidad WHERE idUniversidad = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, universityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("idioma");
                }
            }

        } catch(SQLException getUniversityLanguageByIdException){
            LOG.error("ERROR: ",getUniversityLanguageByIdException);
        }
        return null;
    }

    public String getUniversityCountryById(int universityId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT país FROM universidad WHERE idUniversidad = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, universityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("país");
                }
            }

        } catch(SQLException getUniversityCountryByIdException){
            LOG.error("ERROR: ",getUniversityCountryByIdException);
        }
        return null;
    }




}