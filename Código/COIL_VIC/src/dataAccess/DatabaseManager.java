/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import log.Log;
import org.apache.log4j.Logger;


/**
 *
 * @author marla
 */
public class DatabaseManager {
        private Connection connection;
    private final String DATABASE_NAME="db.url";
    private final String DATABASE_USER="db.user";
    private final String DATABASE_PASSWORD="db.password";
    private static final org.apache.log4j.Logger LOG = Log.getLogger(DatabaseManager.class);


    private Properties getDatabaseConfig(){
        Properties configuration = null;
        try{
            FileInputStream databaseConfig = new FileInputStream("src/dataAccess/DatabaseConfig.properties");
            if(databaseConfig != null){
                configuration = new Properties();
                configuration.load(databaseConfig);
            }
            databaseConfig.close();
        } 
        catch (FileNotFoundException getDatabaseConfigException){
            LOG.error("ERROR:", getDatabaseConfigException);
        } 
        catch (IOException databaseConfigIoException){
            LOG.error("ERROR:", databaseConfigIoException);

        }

        return configuration;
    }
    
    public Connection getConnection() throws SQLException{
        connect();
        return connection;
    }
    
    private void connect() throws SQLException{
        Properties properties = new DatabaseManager().getDatabaseConfig();
        if (properties != null){
            connection=DriverManager.getConnection( properties.getProperty(DATABASE_NAME), 
             properties.getProperty(DATABASE_USER), 
            properties.getProperty(DATABASE_PASSWORD));
        } else {
            throw new SQLException ("No se pudo conectar a la base de datos");
        }
        
    }
    
         public void closeConnection(){
        if(connection!=null){
            try {
                if(!connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException exception) {
                LOG.error("ERROR: ", exception);
            }
        }
    }
}
