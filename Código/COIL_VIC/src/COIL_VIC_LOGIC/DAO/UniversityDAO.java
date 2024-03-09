package COIL_VIC_LOGIC.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import COIL_VIC_DataAccess.DatabaseManager;
import COIL_VIC_LOGIC.Classes.University;
import COIL_VIC_LOGIC.Interfaces.IUniversidad;

public class UniversityDAO implements IUniversity{

    public int addUniversity(University university){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO universidad(idUniversidad, nombreUniversidad, país, idioma) VALUES (?,?,?,?,?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, university.getUniversityId());
            preparedStatement.setString(2, university.getUniversityName());
            preparedStatement.setString(3, university.getUniversityLanguage());
            preparedStatement.setString(4, university.getUniversityCountry());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addUniversityException) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE,null, addUniversityException);
        }
        return result;
    }


}