package logic.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dataAccess.DatabaseManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.interfaces.IProfessor;
import logic.classes.Professor;
import java.util.ArrayList;
import java.sql.ResultSet;
import log.Log;




public class ProfessorDAO implements IProfessor{
    private static final org.apache.log4j.Logger LOG = Log.getLogger(ProfessorDAO.class);

    public int addProfessor(Professor professor) {
        if (professor.getType().equals("UV")) {
            return addProfessorUV(professor);
        } else {
            return addProfessorForeign(professor);
        }
    }

    public int addProfessorUV(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO profesor(nombreProfesor, usuario, telefono, estado,"+ 
        "tipoProfesor, país, Universidad_idUniversidad, area_academica, correo, contraseña, No.Personal," +
        "region, tipoContratación, categoriaContratacion, disciplina) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getUser());
            preparedStatement.setString(3, professor.getPhoneNumber());
            preparedStatement.setString(4, professor.getStatus());
            preparedStatement.setString(5, professor.getType());
            preparedStatement.setString(6, professor.getCountry());
            preparedStatement.setInt(7, professor.getUniversityId());
            preparedStatement.setString(8, professor.getAcademicArea());
            preparedStatement.setString(9, professor.getEmail());
            preparedStatement.setString(10, professor.getPassword());
            preparedStatement.setInt(11, professor.getPersonalNumber());
            preparedStatement.setString(12, professor.getRegion());
            preparedStatement.setString(13, professor.getContractType());
            preparedStatement.setString(14, professor.getContractCategory());
            preparedStatement.setString(15, professor.getDiscipline());
            result = preparedStatement.executeUpdate();
        } catch (SQLException addProfessorUVException) {
            LOG.error("ERROR: ", addProfessorUVException);
        }
        return result;
    }

    public int addProfessorForeign(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO profesor(nombreProfesor, usuario, telefono," + 
        "estado, país, Universidad_idUniversidad, correo, contraseña, tipoProfesor) VALUES (?,?,?,?,?,?,?,?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getUser());
            preparedStatement.setString(3, professor.getPhoneNumber());
            preparedStatement.setString(4, professor.getStatus());
            preparedStatement.setString(5, professor.getCountry());
            preparedStatement.setInt(6, professor.getUniversityId());
            preparedStatement.setString(7, professor.getEmail());
            preparedStatement.setString(8, professor.getPassword());
            preparedStatement.setString(9, professor.getType());
            
            result = preparedStatement.executeUpdate();
        } catch (SQLException addProfessorForeignException) {
            LOG.error("ERROR: ", addProfessorForeignException);
        }
        return result;
    }

    public boolean isProfessorRegistered(String email) {
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) FROM profesor WHERE correoElectrónico = ?";
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
        } catch (SQLException isProfessorRegisteredException) {
                LOG.error("ERROR: ", isProfessorRegisteredException);
            }
        
        return exists;
    }

    public boolean validateProfessorEmail(String email){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT COUNT(*) AS count FROM profesor WHERE email = ?";
        try (Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count == 1; 
                }
            }
        } catch (SQLException validateProfessorEmailException) {
            LOG.error("ERROR: ", validateProfessorEmailException);
        }
        return false; 
    }

    public ObservableList<String> loadProfessorsCountry(){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT DISTINCT país FROM profesor WHERE país IS NOT NULL";
        ObservableList<String> country = FXCollections.observableArrayList();
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                country.add(resultSet.getString("país"));
            }
        }  catch (SQLException loadProfessorsCountry){
            LOG.error("ERROR: ", loadProfessorsCountry);
        }
        return country;  
    }

    public ObservableList<String> loadProfessorsLanguage(){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT DISTINCT idioma FROM universidad WHERE idioma IS NOT NULL";
        ObservableList<String> language = FXCollections.observableArrayList();
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                language.add(resultSet.getString("idioma"));
            }
        }  catch (SQLException loadProfessorsLanguage){
            LOG.error("ERROR: ", loadProfessorsLanguage);
        }
        return language;  
    }

    public ObservableList<String> loadProfessorsAcademicArea(){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT DISTINCT area_academica FROM información_requerida WHERE area_academica IS NOT NULL";
        ObservableList<String> academicAreas = FXCollections.observableArrayList();
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                academicAreas.add(resultSet.getString("area_academica"));
            }
        }  catch (SQLException loadProfessorsAcademicArea){
            LOG.error("ERROR: ", loadProfessorsAcademicArea);
        }
        return academicAreas;  
    }

    public ObservableList<String> loadProfessorsRegion(){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT DISTINCT region FROM información_requerida WHERE region IS NOT NULL";
        ObservableList<String> regions = FXCollections.observableArrayList();
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                regions.add(resultSet.getString("region"));
            }
        }  catch (SQLException loadProfessorsRegion){
            LOG.error("ERROR: ", loadProfessorsRegion);
        }
        return regions;  
    }

    public ObservableList<String> loadProfessorsContractType(){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT DISTINCT tipoContratación FROM información_requerida WHERE tipoContratación IS NOT NULL";
        ObservableList<String> contractTypes = FXCollections.observableArrayList();
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                contractTypes.add(resultSet.getString("tipoContratación"));
            }
        }  catch (SQLException loadProfessorsContractType){
            LOG.error("ERROR: ", loadProfessorsContractType);
        }
        return contractTypes;  
    }

    public ObservableList<String> loadProfessorsContractCategory(){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT DISTINCT categoríaContratación FROM información_requerida WHERE categoríaContratación IS NOT NULL";
        ObservableList<String> contractCategorys = FXCollections.observableArrayList();
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                contractCategorys.add(resultSet.getString("categoríaContratación"));
            }
        }  catch (SQLException loadProfessorsContractCategory){
            LOG.error("ERROR: ", loadProfessorsContractCategory);
        }
        return contractCategorys;  
    }


    public int deleteProfessor(Professor professor){
        DatabaseManager dbMananager = new DatabaseManager();
        String query = "DELETE FROM  profesor WHERE idProfesor = ?";
        int result = 0;
        try {
            Connection connection = dbMananager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professor.getProfessorId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException deleteProfessorException) {
            LOG.error("ERROR: ", deleteProfessorException);
        }
        
        return result;
    }

    public int updateProfessorUV(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE profesor SET nombreProfesor = ?, telefono = ?, estado = ?, tipoProfesor = ?," + 
        "país = ?, Universidad_idUniversidad = ?, area_academica = ?, correo, contraseña = ?, No.Personal = ?," +
        "region = ?, tipoContratación = ?, categoriaContratacion = ?, disciplina = ?  WHERE idProfesor = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(3, professor.getPhoneNumber());
            preparedStatement.setString(4, professor.getStatus());
            preparedStatement.setString(5, professor.getType());
            preparedStatement.setString(6, professor.getCountry());
            preparedStatement.setInt(7, professor.getUniversityId());
            preparedStatement.setString(8, professor.getAcademicArea());
            preparedStatement.setString(9, professor.getEmail());
            preparedStatement.setString(10, professor.getPassword());
            preparedStatement.setInt(11, professor.getPersonalNumber());
            preparedStatement.setString(12, professor.getRegion());
            preparedStatement.setString(13, professor.getContractType());
            preparedStatement.setString(14, professor.getContractCategory());
            preparedStatement.setString(15, professor.getDiscipline());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateProfessorUVException){
            LOG.error("ERROR: ", updateProfessorUVException);
        }
        return result;
    }

    public int updateProfessorForeign(Professor professor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "UPDATE profesor SET nombreProfesor = ?, telefono = ?, estado = ?, tipoProfesor = ?," +
        "país = ?, Universidad_idUniversidad = ?, correo, contraseña = ? WHERE idProfesor = ?";
        int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getName());
            preparedStatement.setString(2, professor.getPhoneNumber());
            preparedStatement.setString(3, professor.getStatus());
            preparedStatement.setString(4, professor.getType());
            preparedStatement.setString(5, professor.getCountry());
            preparedStatement.setInt(6, professor.getUniversityId());
            preparedStatement.setString(7, professor.getEmail());
            preparedStatement.setString(8, professor.getPassword());
            result = preparedStatement.executeUpdate();
        } catch (SQLException updateProfessorForeignException){
            LOG.error("ERROR: ", updateProfessorForeignException);
        }
        return result;
    }
    
    
    public ArrayList<Professor> searchProfessor (int universityId){
        Professor professor = new Professor();
        ArrayList<Professor> professors = new ArrayList<>();
        String query = "SELECT * FROM profesor WHERE Universidad_idUniversidad = ?";
        DatabaseManager dbManager = new DatabaseManager();
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, universityId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int idProfesor = resultSet.getInt("idProfesor");
                    String name = resultSet.getString("nombreProfesor");
                    String status = resultSet.getString("estado");
                    String type = resultSet.getString("tipoProfesor");
                    String country = resultSet.getString("país");
    
                    
                    
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    
                    
                    
                    professors.add(professor);
                }
            }
        } catch (SQLException searchProfessorException){
            LOG.error("ERROR: ", searchProfessorException);
        }
        
        return professors;
    }
    
    public ArrayList<Professor> searchProfessorByCountry (String country){
        Professor professor = new Professor();
        ArrayList<Professor> professors = new ArrayList<>();
        String query = "SELECT * FROM profesor WHERE país = ?";
        DatabaseManager dbManager = new DatabaseManager();
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, country);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    int idProfesor = resultSet.getInt("idProfesor");
                    String name = resultSet.getString("nombreProfesor");
                    String status = resultSet.getString("estado");
                    String type = resultSet.getString("tipoProfesor");
                    int universityId = resultSet.getInt("Universidad_idUniversidad");                    
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    
                    professors.add(professor);
                }
            }
            
        } catch (SQLException searchProfessorByCountryException){
            LOG.error("ERROR: ", searchProfessorByCountryException);
        }
        
        return professors;
    }
    
    public ArrayList<Professor> searchProfessorByStatus (String status){
        Professor professor = new Professor();
        ArrayList<Professor> professors = new ArrayList<>();
        String query = " select * from profesor where estado = ?";
        DatabaseManager dbManager = new DatabaseManager();
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int idProfesor = resultSet.getInt("idProfesor");
                    String name = resultSet.getString("nombreProfesor");
                    String type = resultSet.getString("tipoProfesor");
                    int universityId = resultSet.getInt("Universidad_idUniversidad");
                    String country = resultSet.getString("país");
                    professor = new Professor();
                    professor.setProfessorId(idProfesor);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setType(type);
                    professor.setCountry(country);
                    professor.setUniversityId(universityId);
                    
                    professors.add(professor);
                }
            }
        }catch (SQLException searchProfessorByStatus){
            LOG.error("ERROR: ", searchProfessorByStatus);
        }
        return professors;
    }

    public ArrayList<Professor> searchProfessorByCollaboration(int collaborationId){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT idProfesor, nombreProfesor, estado, Universidad_idUniversidad FROM profesor WHERE idColaboración = ?";
        Professor professor = new Professor();
        ArrayList<Professor> professors = new ArrayList<>();
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, collaborationId);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int professorId = resultSet.getInt("idProfesor");
                    String name = resultSet.getString("nombreProfesor");
                    String status = resultSet.getString("estado");
                    int universityId = resultSet.getInt("Universidad_idUniversidad");
                    professor = new Professor();
                    professor.setProfessorId(professorId);
                    professor.setName(name);
                    professor.setStatus(status);
                    professor.setUniversityId(universityId);

                    professors.add(professor);
                }
            }
        }  catch (SQLException searchProfessorByCollaboration){
            LOG.error("ERROR: ", searchProfessorByCollaboration);
        }
        return professors;  
    }

    public int changeProfessorStatusById(String status, int professorId){
    DatabaseManager dbManager = new DatabaseManager();
    String query = "UPDATE profesor SET estado = ? WHERE idProfesor = ?";
    int result = 0;
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, professorId);
            result = preparedStatement.executeUpdate();
        } catch (SQLException changeProfessorStatusException){
            LOG.error("ERROR: ", changeProfessorStatusException);
    }
    return result;
    }

    public int professorRequestCollaboration(int idColaboración, int idProfesor){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "INSERT INTO solicitud_Colaboración (idColaboración, idProfesor) VALUES (?,?)";
        int result = 0;
        try {
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idColaboración);
            preparedStatement.setInt(2, idProfesor);
            result = preparedStatement.executeUpdate();
        } catch (SQLException professorRequestCollaboratioException){
            LOG.error("ERROR: ", professorRequestCollaboratioException);
        }
        return result;
    }

    public int getProfessorIdByUser(String user){
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT idProfesor FROM Profesor WHERE usuario = ?";
        try{
            Connection connection = dbManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt("IdProfesor");
                }
            }

        } catch(SQLException getProfessorIdByUserException){
            LOG.error("ERROR:", getProfessorIdByUserException);
        }
        return 0;
    }

    public String getProfessorNameByUser(String user){
        String name = null;
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT nombreProfesor FROM profesor WHERE usuario = ? ";
        try {
            Connection connection = dbManager.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, user);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    name = resultSet.getString("nombreProfesor");
                }
                
            }
        } catch (SQLException getProfesorNameByUserException) {
            LOG.error(getProfesorNameByUserException);
        }   
        return name;
    }

    public String getProfessorPhoneByUser(String user){
        String phoneNumber = null;
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT telefono FROM profesor WHERE usuario = ? ";
        try {
            Connection connection = dbManager.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, user);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    phoneNumber = resultSet.getString("telefono");
                }
                
            }
        } catch (SQLException getProfesorPhoneByUserException) {
            LOG.error(getProfesorPhoneByUserException);
        }   
        return phoneNumber;
    }

    public String getProfessorAreaByUser(String user){
        String name = null;
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT area_academica FROM profesor WHERE usuario = ? ";
        try {
            Connection connection = dbManager.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, user);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    name = resultSet.getString("area_academica");
                }
                
            }
        } catch (SQLException getProfesorAreaByUserException) {
            LOG.error(getProfesorAreaByUserException);
        }   
        return name;
    }

    public String getProfessorEmailByUser(String user){
        String name = null;
        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT correo FROM profesor WHERE usuario = ? ";
        try {
            Connection connection = dbManager.getConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, user);
                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    name = resultSet.getString("correo");
                }
                
            }
        } catch (SQLException getProfesorEmailByUserException) {
            LOG.error(getProfesorEmailByUserException);
        }   
        return name;
    }
}




