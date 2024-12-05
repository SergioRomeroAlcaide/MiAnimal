package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/MiAnimal?useSSL=true&requireSSL=true";
    private static final String DEFAULT_USER = "role_admin";
    private static final String DEFAULT_PASSWORD = "admin";
    private static final String CA_CERT = "C:/xampp2/mysql/certs/ca-cert.pem";

    // Método para obtener conexión con credenciales predeterminadas
    public static Connection getConnection() throws SQLException {
        return getConnection(DEFAULT_USER, DEFAULT_PASSWORD);
    }

    // Método sobrecargado para obtener conexión con credenciales específicas
    public static Connection getConnection(String user, String password) throws SQLException {
        Properties properties = new Properties();

        // Configuración de usuario y contraseña
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        // Configuración de SSL
        properties.setProperty("useSSL", "true");
        properties.setProperty("requireSSL", "true");
        properties.setProperty("trustCertificateKeyStoreUrl", "file:" + CA_CERT);

        // Establecer conexión
        return DriverManager.getConnection(URL, properties);
    }
}
