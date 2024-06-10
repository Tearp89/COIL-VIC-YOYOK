/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dataAccess.DatabaseManager;
import javafx.scene.chart.PieChart.Data;
import log.Log;
import logic.classes.Activity;
import logic.interfaces.IActivity;
import java.sql.Statement;

/**
 *
 * @author isabe
 */
public class ActivityDAO implements IActivity {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ActivityDAO.class);
    
    public int addActivity(Activity activity) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO actividad (título, descripcion, tipo, semana) VALUES (?, ?, ?, ?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, activity.getTitle());
            preparedStatement.setString(2, activity.getDescription());
            preparedStatement.setString(3, activity.getType());
            preparedStatement.setString(4, activity.getWeek());
            result = preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int activityId = resultSet.getInt(1);
                activity.setActivityId(activityId);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
            
        } catch (SQLException addActivityException) {
            LOG.error("ERROR: ", addActivityException);
        }
        return result;       
    }


        public int deleteActivity (Activity activity) {
            DatabaseManager dbManager = new DatabaseManager();
            String query = "DELETE FROM actividad where idActividad = ?";
            int result = 0;
            try { 
                Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,activity.getActivityId());
                result = preparedStatement.executeUpdate();  
                connection.close();
            } catch (SQLException deleteActivityException){
                LOG.error("ERROR: ", deleteActivityException);
            }
            return result;
        }

        public int updateActivity (Activity activity) { 
            DatabaseManager dbManager = new DatabaseManager();
            String query = "UPDATE actividad set título = ?, descripcion = ?, tipo = ?, semana = ?  WHERE idActividad = ?";
            int result = 0;
            try{
                Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, activity.getTitle());
                preparedStatement.setString(2, activity.getDescription());
                preparedStatement.setString(3, activity.getType());
                preparedStatement.setString(4, activity.getWeek());
                preparedStatement.setInt(5, activity.getActivityId());
                result = preparedStatement.executeUpdate();
                connection.close();
            } catch (SQLException updateActivityException) {
            LOG.error("ERROR: ", updateActivityException);
            }
            return result;       
            
        }


        public int assignActivityToCollaboration(int collaborationId, int activityId){
            DatabaseManager dbManager = new DatabaseManager();
            String query = "INSERT INTO cronograma_actividades (idColaboración, idActividad) VALUES (?, ?)";
            int result = 0;
            try {
                Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, collaborationId);
                preparedStatement.setInt(2, activityId);
                result = preparedStatement.executeUpdate();
                connection.close();
            } catch (SQLException assignActivityException){
                LOG.error("ERROR:", assignActivityException);
            }

            return result;

        }


    public List<Activity> getActivitiesByCollaborationAndWeek(int collaborationId, String week) {
        DatabaseManager dbManager = new DatabaseManager();
        List<Activity> activities = new ArrayList<>();
        String query = "SELECT a.* " +
                        "FROM actividad a " +
                        "JOIN cronograma_actividades sa ON a.idActividad = sa.idActividad " +
                        "WHERE sa.idColaboración = ? AND a.semana = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, collaborationId);
            preparedStatement.setString(2, week);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int activityId = resultSet.getInt("idActividad");
                    String title = resultSet.getString("título");
                    String description = resultSet.getString("descripcion");
                    String type = resultSet.getString("tipo");
                    String activityWeek = resultSet.getString("semana");
                    Activity activity = new Activity();
                    activity.setActivityId(activityId);
                    activity.setTitle(title);
                    activity.setDescription(description);
                    activity.setType(type);
                    activity.setWeek(activityWeek);
                    activities.add(activity);
                }
            }
        } catch (SQLException getActivitiesByCollaborationAndWeekException) {
            LOG.error("ERROR:", getActivitiesByCollaborationAndWeekException);
        }
        return activities;
    }
    //TODO: Test
    public boolean isActivityAssignedInWeek(int collaborationId, String week) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) FROM actividad a " +
                        "JOIN cronograma_actividades ca ON a.idActividad = ca.idActividad " +
                        "WHERE ca.idColaboración = ? AND a.semana = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, collaborationId);
            preparedStatement.setString(2, week);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return false;
    }

    
    public boolean isActivityTypeAssigned(int collaborationId, String type) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) FROM actividad a " +
                        "JOIN cronograma_actividades ca ON a.idActividad = ca.idActividad " +
                        "WHERE ca.idColaboración = ? AND a.tipo = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, collaborationId);
            preparedStatement.setString(2, type);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return false;
    }

    public String getActivityTypeById(int activityId) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT tipo FROM actividad WHERE idActividad = ?";
        String type = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, activityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    type = resultSet.getString("tipo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return type;
    }

    public String getActivityWeekById(int activityId) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT semana FROM actividad WHERE idActividad = ?";
        String week = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, activityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    week = resultSet.getString("semana");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return week;
    }
    







}