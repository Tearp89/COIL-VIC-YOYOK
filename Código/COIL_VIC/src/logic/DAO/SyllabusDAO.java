/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import dataAccess.DatabaseManager;
import log.Log;
import logic.classes.Syllabus;
import logic.interfaces.ISyllabus;

/**
 *
 * @author isabe
 */
public class SyllabusDAO implements ISyllabus {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(SyllabusDAO.class);
    
    public int addSyllabus (Syllabus syllabus) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO syllabus (idSyllabus, actividad, descripcion, responsable) VALUES (?, ?, ?, ?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, syllabus.getSyllabusId());
            preparedStatement.setString(2, syllabus.getActivity());
            preparedStatement.setString(3, syllabus.getDescription());
            preparedStatement.setString(4, syllabus.getNameOfResponsable());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addSyllabusException) {
            LOG.error("ERROR: ", addSyllabusException);
        }
        return result;       
    }

        public int deleteSyllabus (Syllabus syllabus) {
            DatabaseManager dbManager = new DatabaseManager();
            String query = "DELETE FROM syllabus where idSyllabus = ?";
            int result = 0;
            try { 
                Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,syllabus.getSyllabusId());
                result = preparedStatement.executeUpdate();  
            } catch (SQLException deleteSyllabusException){
                LOG.error("ERROR: ", deleteSyllabusException);
            }
            return result;
        }

        public int updateSyllabus (Syllabus syllabus) { 
            DatabaseManager dbManager = new DatabaseManager();
            String query = "UPDATE syllabus set actividad = ?, descripcion = ?, responsable = ? WHERE idSyllabus = ?";
            int result = 0;
            try{
                Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, syllabus.getActivity());
                preparedStatement.setString(2, syllabus.getDescription());
                preparedStatement.setString(3, syllabus.getNameOfResponsable());
                preparedStatement.setInt(4, syllabus.getSyllabusId());
                result = preparedStatement.executeUpdate();
            } catch (SQLException updateSyllabusException) {
            LOG.error("ERROR: ", updateSyllabusException);
            }
            return result;       
        }


}