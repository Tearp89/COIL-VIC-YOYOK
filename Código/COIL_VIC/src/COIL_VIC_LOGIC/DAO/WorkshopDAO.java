package COIL_VIC_LOGIC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import COIL_VIC_DataAccess.DatabaseManager;
import COIL_VIC_LOGIC.Classes.Workshop;

public class WorkshopDAO {

    public int addWorkshop(Workshop workshop){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO curso-taller (nombreCursoTaller, fechaInicio, fechaFin, requisitos) VALUES (?, ?, ?, ?)";
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
        String query = "DELETE FROM curso-taller WHERE idCursoTaller = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, workshop.getWorkshopId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteWorkshopException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, deleteWorkshopException);
        }
        return result;
    }

    public int updateWorkshop(Workshop workshop){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE curso-taller SET nombreCursoTaller = ?, fechaInicio = ?, fechaFin = ?, requisitos = ?";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, workshop.getWorkshopName());
            preparedStatement.setObject(2, workshop.getStartDate());
            preparedStatement.setObject(3, workshop.getFinishDate());
            preparedStatement.setString(4, workshop.getRequirements());
            result = preparedStatement.executeUpdate();
        } catch (SQLException  updateWorkshopException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, updateWorkshopException);
        }
        return result;
    }

}
