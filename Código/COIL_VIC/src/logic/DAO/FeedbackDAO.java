package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dataAccess.DatabaseManager;
import log.Log;
import logic.classes.Feedback;

public class FeedbackDAO {
     private static final org.apache.log4j.Logger LOG = Log.getLogger(FeedbackDAO.class);

     public int addProfessorReview(Feedback feedback){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO retroalimentación_académicos (idProfesor, idColaboración, calificación, observaciones) VALUES (?,?,?,?)";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, feedback.getProfessorId());
            preparedStatement.setInt(2, feedback.getCollaborationId());
            preparedStatement.setInt(3, feedback.getGrade());
            preparedStatement.setString(4, feedback.getComments());
            result = preparedStatement.executeUpdate();

        } catch (SQLException addProfessorReviewException){
            LOG.error("ERROR:", addProfessorReviewException);
        }

        return result;
     }


     public int addAdminReview(Feedback feedback){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO retroalimentación_administrativos (idAdministrativo, idColaboración, calificación, observaciones) VALUES (?,?,?,?)";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, feedback.getAdminId());
            preparedStatement.setInt(2, feedback.getCollaborationId());
            preparedStatement.setInt(3, feedback.getGrade());
            preparedStatement.setString(4, feedback.getComments());
            result = preparedStatement.executeUpdate();

        } catch (SQLException addAdminReviewException){
            LOG.error("ERROR:", addAdminReviewException);
        }

        return result;
     }

     public int addStudentReview(Feedback feedback){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO retroalimentación_estudiantes (correoElectrónico, idColaboración, calificación, observaciones) VALUES (?,?,?,?)";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, feedback.getEmail());
            preparedStatement.setInt(2, feedback.getCollaborationId());
            preparedStatement.setInt(3, feedback.getGrade());
            preparedStatement.setString(4, feedback.getComments());
            result = preparedStatement.executeUpdate();

        } catch (SQLException addStudentReviewException){
            LOG.error("ERROR:", addStudentReviewException);
        }

        return result;
     }

    

} 
