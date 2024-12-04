package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    // URL de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/MiAnimal?useSSL=true&requireSSL=true";

    // Configuración para el usuario veterinario
     //private static final String USER = "veterinario"; 
     //private static final String PASSWORD = "veterinario"; 

    // Configuración para el usuario recepcionista
     //private static final String USER = "recepcionista"; 
     //private static final String PASSWORD = "recepcionista"; 

    // Configuración para el usuario admin (por defecto)
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    // Ruta al certificado CA
    private static final String CA_CERT = "C:/xampp2/mysql/certs/ca-cert.pem";

    // Método para obtener una conexión
    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        // Configuración de usuario y contraseña
        properties.setProperty("user", USER);
        properties.setProperty("password", PASSWORD);

        // Configuración de SSL
        properties.setProperty("useSSL", "true");
        properties.setProperty("requireSSL", "true");
        properties.setProperty("trustCertificateKeyStoreUrl", "file:" + CA_CERT);

        // Establecer conexión
        return DriverManager.getConnection(URL, properties);
    }

    // Método principal para pruebas
    public static void main(String[] args) {
        try (Connection connection = DBConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa con el usuario: " + USER);
            }
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión con el usuario: " + USER);
            System.err.println("Mensaje de error: " + e.getMessage());
        }
    }
}


