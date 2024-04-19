package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import dataAccess.DatabaseManager;
import logic.interfaces.IWorkshop;
import logic.classes.Workshop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.logging.Level;
import log.Log;
import org.apache.log4j.Logger;


public class WorkshopDAO implements IWorkshop {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(WorkshopDAO.class);
    
    public int addWorkshop(Workshop workshop){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO curso_taller (nombreCursoTaller, fechaInicio, fechaFin, requisitos) VALUES (?, ?, ?, ?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, workshop.getWorkshopName());
            preparedStatement.setObject(2, workshop.getStartDate());
            preparedStatement.setObject(3, workshop.getFinishDate());
            preparedStatement.setString(4, workshop.getRequirements());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addWorkshopException) {
            LOG.error("ERROR: ", addWorkshopException);
        }

        return result;
    }

    public int deleteWorkshop(Workshop workshop){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "DELETE FROM curso_taller WHERE idCursoTaller = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, workshop.getWorkshopId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteWorkshopException) {
            LOG.error("ERROR: ", deleteWorkshopException);
        }
        return result;
    }

    public int updateWorkshop(Workshop workshop){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE curso_taller SET nombreCursoTaller = ?, fechaInicio = ?, fechaFin = ?, requisitos = ? WHERE idCursoTaller = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, workshop.getWorkshopName());
            preparedStatement.setObject(2, workshop.getStartDate());
            preparedStatement.setObject(3, workshop.getFinishDate());
            preparedStatement.setString(4, workshop.getRequirements());
            preparedStatement.setInt(5, workshop.getWorkshopId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException  updateWorkshopException) {
            LOG.error("ERROR: ", updateWorkshopException);
        }
        return result;
    }
    
    public ArrayList<Workshop> searchWorkshop (int workshopId){
        Workshop workshop = new Workshop();
        ArrayList<Workshop> workshops = new ArrayList<>();
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT * FROM curso_taller WHERE idCursoTaller = ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, workshopId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int workShopId = resultSet.getInt("idCursoTaller");
                    String name = resultSet.getString("nombreCursoTaller");
                    LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                    LocalDate finishDate = resultSet.getObject("fechaFin", LocalDate.class);
                    String requirements = resultSet.getString("requisitos");
                    
                    
                    workshop = new Workshop();
                    workshop.setWorkshopId(workShopId);
                    workshop.setWorkshopName(name);
                    workshop.setStartDate(startDate);
                    workshop.setFinishDate(finishDate);
                    workshop.setRequirements(requirements);
                    workshops.add(workshop);
                }
            }
        }catch (SQLException searchWorkshopException){
            LOG.error("ERROR: ", searchWorkshopException);
        }
        
        return workshops;
    }
    
    public ArrayList<Workshop> searchWorkshopByYear (String year){
        Workshop workshop = new Workshop();
        ArrayList<Workshop> workshops = new ArrayList<>();
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT * FROM curso_taller WHERE YEAR(fechaInicio) = ?;";

        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, year);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int workShopId = resultSet.getInt("idCursoTaller");
                    String name = resultSet.getString("nombreCursoTaller");
                    LocalDate startDate = resultSet.getObject("fechaInicio", LocalDate.class);
                    LocalDate finishDate = resultSet.getObject("fechaFin", LocalDate.class);
                    String requirements = resultSet.getString("requisitos");
                    
                    
                    workshop = new Workshop();
                    workshop.setWorkshopId(workShopId);
                    workshop.setWorkshopName(name);
                    workshop.setStartDate(startDate);
                    workshop.setFinishDate(finishDate);
                    workshop.setRequirements(requirements);
                    workshops.add(workshop);
        } 
    }

    } catch (SQLException searchWorkshopByYearException){
                LOG.error("ERROR: ", searchWorkshopByYearException);
                }
        
        return workshops;
        
  }
}
