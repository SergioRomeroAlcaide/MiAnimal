package Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection2 {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = DBConnection2.class.getClassLoader().getResourceAsStream("Util/configuracion.properties")) {
            if (input == null) {
                System.err.println("❌ No se encontró el archivo de propiedades 'configuracion.properties' en el classpath.");
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("❌ Error al cargar el archivo de propiedades: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        String url = properties.getProperty("DB_URL");
        String user = properties.getProperty("DB_USER");
        String password = properties.getProperty("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            System.err.println("❌ Las propiedades de conexión no están definidas correctamente.");
            if (url == null) System.err.println("❌ Propiedad faltante: DB_URL");
            if (user == null) System.err.println("❌ Propiedad faltante: DB_USER");
            if (password == null) System.err.println("❌ Propiedad faltante: DB_PASSWORD");
            return null;
        }

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión exitosa a la base de datos: " + url);
            return connection;
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage() + ". URL: " + url + ", Usuario: " + user);
            e.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
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
