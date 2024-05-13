/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dataAccess.DatabaseManager;
import java.sql.ResultSet;
import log.Log;

/**
 *
 * @author marla
 */
public class CollaborationStatsDAO {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(CollaborationStatsDAO.class);
    
    public int countStudentsByRegion(String region) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(DISTINCT correoElectrónico) AS total_students " +
                        "FROM colaboración c " +
                        "JOIN participa p ON c.idColaboración = p.Colaboración_idColaboración " +
                        "JOIN estudiante e ON p.Estudiante_correoElectrónico = e.correoElectrónico " +
                        "JOIN profesor pr ON e.Profesor_idProfesor = pr.idProfesor " +
                        "WHERE pr.region = ?";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, region);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("total_students");
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return result;
    }

    public int countStudentsByRegionAndYear(String region, String year){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(DISTINCT correoElectrónico) AS total_students " +
                        "FROM colaboración c " +
                        "JOIN participa p ON c.idColaboración = p.Colaboración_idColaboración " +
                        "JOIN estudiante e ON p.Estudiante_correoElectrónico = e.correoElectrónico " +
                        "JOIN profesor pr ON e.Profesor_idProfesor = pr.idProfesor " +
                        "WHERE pr.region = ? AND YEAR(c.fechaInicio) = ?";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, region);
            preparedStatement.setString(2, year);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("total_students");
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return result;
    }

    public int countStudentsByAcademicArea(String academicArea) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(DISTINCT correoElectrónico) AS total_students " +
                        "FROM colaboración c " +
                        "JOIN participa p ON c.idColaboración = p.Colaboración_idColaboración " +
                        "JOIN estudiante e ON p.Estudiante_correoElectrónico = e.correoElectrónico " +
                        "JOIN profesor pr ON e.Profesor_idProfesor = pr.idProfesor " +
                        "WHERE pr.area_academica = ?";  
        int result = 0;
        try (Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicArea);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("total_students");
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return result;
    }
    
    public int countStudentsByAcademicAreaAndYear(String academicArea, String year) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(DISTINCT correoElectrónico) AS total_students " +
                        "FROM colaboración c " +
                        "JOIN participa p ON c.idColaboración = p.Colaboración_idColaboración " +
                        "JOIN estudiante e ON p.Estudiante_correoElectrónico = e.correoElectrónico " +
                        "JOIN profesor pr ON e.Profesor_idProfesor = pr.idProfesor " +
                        "WHERE pr.area_academica = ? AND YEAR(c.fechaInicio) = ?";  
        int result = 0;
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicArea);
            preparedStatement.setString(2, year);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("total_students");
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return result;
    }

    public int countProfessorsByAcademicArea(String academicArea) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(DISTINCT cr.Profesor_idProfesor) AS total_professors " + 
                        "FROM colaboraciones_registradas cr " + 
                        "JOIN profesor p ON cr.Profesor_idProfesor = p.idProfesor " + 
                        "WHERE p.area_academica = ?";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicArea);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("total_professors");
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return result;
    }

    public int countProfessorsByAcademicAreaAndYear(String academicArea, String year) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(DISTINCT cr.Profesor_idProfesor) AS total_professors " + 
                        "FROM colaboraciones_registradas cr " + 
                        "JOIN profesor p ON cr.Profesor_idProfesor = p.idProfesor " + 
                        "JOIN colaboración c ON cr.Colaboración_idColaboración = c.idColaboración " + 
                        "WHERE p.area_academica = ? " + 
                        "AND YEAR(c.fechaInicio) = ? ";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicArea);
            preparedStatement.setString(2, year);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("total_professors");
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return result;
    }

    public int countProfessorsByRegion(String state) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(DISTINCT cr.Profesor_idProfesor) AS total_professors " + 
                        "FROM colaboraciones_registradas cr " + 
                        "JOIN profesor p ON cr.Profesor_idProfesor = p.idProfesor " + 
                        "JOIN regiones_profesor rp ON p.idProfesor = rp.Profesor_idProfesor " + 
                        "WHERE rp.region = ?";
        int result = 0;
        try (Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, state);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("total_professors");
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return result;
    }

    public int countProfessorsByRegionAndYear(String state, String year) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(DISTINCT cr.Profesor_idProfesor) AS total_professors " + 
                        "FROM colaboraciones_registradas cr " + 
                        "JOIN profesor p ON cr.Profesor_idProfesor = p.idProfesor " + 
                        "JOIN colaboración c ON cr.Colaboración_idColaboración = c.idColaboración " + 
                        "WHERE p.region = ? " + 
                        "AND YEAR(c.fechaInicio) = ?";        
        int result = 0;
        try (Connection connection = dbManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, state);
            preparedStatement.setString(2, year);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getInt("total_professors");
                }
            }
        } catch (SQLException e) {
            LOG.error("ERROR: ", e);
        }
        return result;
    }

}
