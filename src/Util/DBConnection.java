package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/MiAnimal";

    // Método para obtener la conexión según las credenciales del usuario
    public static Connection getConnection(String usuario, String contrasena) throws SQLException {
        return DriverManager.getConnection(URL, usuario, contrasena);
    }

    // Método para probar la conexión
    public static void testConnection(String usuario, String contrasena) {
        try (Connection connection = getConnection(usuario, contrasena)) {
            if (connection != null) {
                System.out.println("Conexión exitosa para el usuario: " + usuario);
            } else {
                System.out.println("Conexión fallida.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}
