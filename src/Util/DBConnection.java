package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Parámetros de conexión
    private static final String URL = "jdbc:mariadb://localhost:3306/mianimal";
    private static final String USER = "admin"; // Asegúrate de que el usuario sea correcto
    private static final String PASSWORD = "admin"; // Reemplaza con tu contraseña real

    // Objeto de conexión
    private static Connection connection;

    /**
     * Método para obtener la conexión
     * @return Connection
     */
    public static synchronized Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión exitosa a la base de datos: " + URL);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Método para cerrar la conexión
     */
    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null; // Evitar reutilización de la conexión cerrada
                System.out.println("✅ Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
