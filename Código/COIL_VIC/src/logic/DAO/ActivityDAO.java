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

/**
 *
 * @author isabe
 */
public class ActivityDAO implements IActivity {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ActivityDAO.class);
    
    public int addActivity (Activity activity) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO actividad (título, descripcion, tipo, semana) VALUES (?, ?, ?, ?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
            
        } catch (SQLException addSyllabusException) {
            LOG.error("ERROR: ", addSyllabusException);
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
            } catch (SQLException deleteActivityException){
                LOG.error("ERROR: ", deleteActivityException);
            }
            return result;
        }

        public int updateActivity (Activity activity) { 
            DatabaseManager dbManager = new DatabaseManager();
            String query = "UPDATE actividad set título = ?, descripcion = ?, tipo = ?, semana = ?,  WHERE idActividad = ?";
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
            try {
                Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query); 
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
                LOG.error("ERROR:",getActivitiesByCollaborationAndWeekException); // Basic error handling, you can enhance exception handling as needed
            }
            return activities;
    }







}