package dataAccess;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import log.Log;
import java.sql.SQLException;

public class DatabaseConnectionChecker {
    private static final org.apache.log4j.Logger LOG = Log.getLogger(DatabaseConnectionChecker.class);

    public static boolean isDatabaseConnected() {
        DatabaseManager dbManager = new DatabaseManager();
        try {
            dbManager.getConnection();
            return true;
        } catch (SQLException e) {
            LOG.error("No hay conexión con la base de datos " +  e.getMessage());
            return false;
        }
    }

    public static void showNoConnectionDialog(Stage ownerStage) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "No hay conexión con la base de datos. La aplicación no puede continuar.", ButtonType.OK);
        alert.setTitle("Error de Conexión");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
