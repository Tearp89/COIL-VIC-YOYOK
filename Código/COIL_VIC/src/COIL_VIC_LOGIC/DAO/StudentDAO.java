package COIL_VIC_LOGIC.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import COIL_VIC_DataAccess.DatabaseManager;
import COIL_VIC_LOGIC.Classes.Student;
import COIL_VIC_LOGIC.Interfaces.IStudent;

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
