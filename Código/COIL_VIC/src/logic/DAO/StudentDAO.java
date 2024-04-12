package logic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dataAccess.DatabaseManager;
import logic.Interfaces.IStudent;
import logic.classes.Student;

public class StudentDAO implements IStudent {

    public int addStudent(Student student) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO estudiante(correoElectrónico) VALUES (?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getEmail());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addStudentException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, addStudentException);
        }
        return result;
    }
    
}
