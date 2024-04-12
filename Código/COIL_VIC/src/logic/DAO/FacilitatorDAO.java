/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAO;

/**
 *
 * @author isabe
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataAccess.DatabaseManager;
import logic.interfaces.IFacilitator;
import logic.classes.Facilitator;

public class FacilitatorDAO implements IFacilitator{
    public int addFacilitator (Facilitator facilitator){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO facilitador (idFacilitador, nombreFacilitador, Curso-Taller_idCursoTaller) VALUES (?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, facilitator.getFacilitatorName());
            preparedStatement.setString(2, facilitator.getWorkShopId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addFacilitatorException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, addFacilitatorException);
        }
        return result;
    }
    
    public int deleteFacilitator(Facilitator facilitator){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "DELETE FROM facilitador where nombreFacilitador = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,facilitator.getFacilitatorName());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteFacilitatorException){
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, deleteFacilitatorException);
        }
        return result;
    }
    
    public int updateFacilitator (Facilitator facilitator){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE facilitador set nombreFacilitador = ?, Curso-Taller_idCursoTaller = ? WHERE idFacilitador = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(2, facilitator.getFacilitatorName());
            preparedStatement.setString(3, facilitator.getWorkShopId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateFacilitatorException){
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null,updateFacilitatorException);
        }
        return result;
    }

}
