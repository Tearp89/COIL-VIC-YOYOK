package COIL_VIC_LOGIC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import COIL_VIC_DataAccess.DatabaseManager;
import COIL_VIC_LOGIC.Classes.Workshop;
import COIL_VIC_LOGIC.Interfaces.IWorkshop;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.ResultSet;

public class WorkshopDAO implements IWorkshop {

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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, addWorkshopException);
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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, deleteWorkshopException);
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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, updateWorkshopException);
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
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, searchWorkshopException);
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
                Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, searchWorkshopByYearException);
                }
        
        return workshops;
        
  }
}
