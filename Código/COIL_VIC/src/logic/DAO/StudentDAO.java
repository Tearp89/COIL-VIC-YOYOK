package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import dataAccess.DatabaseManager;
import javafx.scene.chart.PieChart.Data;
import log.Log;
import logic.interfaces.IStudent;
import logic.classes.Collaboration;
import logic.classes.Student;

public class StudentDAO implements IStudent {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(StudentDAO.class);
    
    public int addStudent(Student student) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO estudiante(correoElectrónico, Profesor_idProfesor, contraseña) VALUES (?, ?, SHA2(?, 256))";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getEmail());
            preparedStatement.setInt(2, student.getProfessorId());
            preparedStatement.setString(3, student.getPassword());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addStudentException) {
            LOG.error("ERROR: ", addStudentException);
        }
        return result;
    }

    public int updateStudentPassword(String password, String email){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE estudiante SET contraseña = SHA2(?, 256) WHERE correoElectrónico = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            result = preparedStatement.executeUpdate();
            
        } catch (SQLException updateStudentPasswordException){
            LOG.error("ERROR:", updateStudentPasswordException);
        }
        return result;
    }

    //TODO: Test

    public boolean isStudentAssignedToProfessor(String email, int professorId) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) FROM estudiante WHERE Profesor_idProfesor = ? AND correoElectrónico = ?";
        boolean exists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professorId);
            preparedStatement.setString(2, email);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
                
            }
        } catch (SQLException isStudentAssignedException) {
                LOG.error("ERROR:", isStudentAssignedException);
            }
        
        return exists;
    }

    public boolean isStudentRegistered(String email) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) FROM estudiante WHERE correoElectrónico = ?";
        boolean exists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = dbManager.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0;
                
            }
        } catch (SQLException isStudentRegisteredException) {
                LOG.error("ERROR: ", isStudentRegisteredException);
            }
        
        return exists;
    }

    public int changeProfessorAssigned(int professorId, String correo){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE estudiante SET Profesor_idProfesor = ? WHERE correoElectrónico = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professorId);
            preparedStatement.setString(2, correo);
            result = preparedStatement.executeUpdate();
            
        } catch (SQLException changeProfessorAssignedExceptiom){
            LOG.error("ERROR:", changeProfessorAssignedExceptiom);
        }
        return result;

    }

    public ArrayList<Student> getStudentsByProfessorId(int professorId) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT correoElectrónico FROM estudiante WHERE Profesor_idProfesor = ?";
        ArrayList<Student> students = new ArrayList<>();

        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("correoElectrónico");
                Student student = new Student();
                student.setEmail(email);
                students.add(student);
                
            }
            
        } catch (SQLException getStudentsByProfessorIdException) {
            LOG.error("ERROR:", getStudentsByProfessorIdException);
         
        }

        return students;
    }


    public int changeStudentPassword(String password, String email){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE estudiante SET contraseña = ? WHERE correoElectrónico = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            result = preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException changeStudentPasswordException){
            LOG.error("ERROR:", changeStudentPasswordException);
        }
        return result;

    }

    
}





    
    

