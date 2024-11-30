package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/MiAnimal";
    private static final String USER = "admin"; // Usuario administrador
    private static final String PASSWORD = "admin"; // Contraseña del usuario administrador

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para probar la conexión
    public static void testConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa con la base de datos.");
            } else {
                System.out.println("La conexión con la base de datos falló.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        testConnection(); // Llama al método para probar la conexión
    }
}