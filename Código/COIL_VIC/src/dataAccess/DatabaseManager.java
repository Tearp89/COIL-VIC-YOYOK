/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import log.Log;
import org.apache.log4j.Logger;


/**
 *
 * @author marla
 */
public class DatabaseManager {
        private Connection connection;
    private final String DATABASE_NAME="jdbc:mysql://127.0.0.1/COIL_VIC";
    private final String DATABASE_USER="admin";
    private final String DATABASE_PASSWORD="taylor";
    private static final org.apache.log4j.Logger LOG = Log.getLogger(DatabaseManager.class);
    
    public Connection getConnection() throws SQLException{
        connect();
        return connection;
    }
    
    private void connect() throws SQLException{
        connection=DriverManager.getConnection(DATABASE_NAME,DATABASE_USER,DATABASE_PASSWORD);
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
