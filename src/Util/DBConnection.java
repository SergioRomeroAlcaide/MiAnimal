package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Parámetros de conexión
    private static final String URL = "jdbc:mariadb://localhost:3306/mianimal";
    private static final String USER = "root"; // Asegúrate de que el usuario sea correcto
    private static final String PASSWORD = ""; // Reemplaza con tu contraseña real

    // Objeto de conexión
    private static Connection connection;

    static {
        try {
            // Cargar el controlador MariaDB
            Class.forName("org.mariadb.jdbc.Driver");

            // Conectar a la base de datos
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos: " + URL);
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error general: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener la conexión
     * @return Connection
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Cargar el controlador MariaDB
                Class.forName("org.mariadb.jdbc.Driver");

                // Reconectar a la base de datos
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión restablecida a la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al restablecer la conexión a la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error general: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Método para cerrar la conexión
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("✅ Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
