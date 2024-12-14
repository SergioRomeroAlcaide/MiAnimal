package Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * La clase DBConnection2 proporciona métodos para establecer y cerrar la conexión 
 * con la base de datos. Usa un archivo de propiedades para obtener la URL, 
 * el usuario y la contraseña de la conexión. 
 * 
 * Esta clase utiliza un bloque de inicialización estático para cargar las propiedades
 * desde el archivo 'configuracion.properties' que debe estar en la carpeta 'Util' 
 * dentro del classpath.
 * 
 * <p>Esta clase permite:
 * <ul>
 *   <li>Conectar a la base de datos de forma segura.</li>
 *   <li>Cerrar la conexión de forma controlada.</li>
 * </ul>
 * 
 * <p>Requisitos:
 * <ul>
 *   <li>El archivo de propiedades debe llamarse <strong>configuracion.properties</strong> 
 *       y debe estar ubicado en la carpeta <strong>Util</strong> dentro del classpath.</li>
 * </ul>
 * 
 * @author 
 * @version 1.0
 */
public class DBConnection2 {

    /**
     * Objeto de tipo {@link Properties} que contiene las propiedades de conexión.
     * Este objeto se carga de forma estática al inicializar la clase, leyendo 
     * el archivo 'Util/configuracion.properties'.
     */
    private static Properties properties = new Properties();

    /**
     * Bloque de inicialización estático.
     * 
     * <p>Este bloque se ejecuta una vez al cargar la clase. Se encarga de 
     * cargar el archivo de configuración 'Util/configuracion.properties' 
     * para inicializar las propiedades de la conexión (URL, usuario y contraseña).
     * 
     * <p>Si el archivo no se encuentra o no se puede cargar, se muestra un mensaje de error.
     */
    static {
        try (InputStream input = DBConnection2.class.getClassLoader().getResourceAsStream("Util/configuracion.properties")) {
            if (input == null) {
                System.err.println("❌ No se encontró el archivo de propiedades 'Util/configuracion.properties' en el classpath.");
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("❌ Error al cargar el archivo de propiedades: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene una conexión a la base de datos.
     * 
     * <p>Este método utiliza los parámetros de conexión definidos en el archivo
     * <strong>configuracion.properties</strong>. Los parámetros que se deben definir 
     * en el archivo son:
     * 
     * <ul>
     *   <li><strong>DB_URL</strong>: URL de la base de datos (por ejemplo, jdbc:mariadb://localhost:3306/mianimal).</li>
     *   <li><strong>DB_USER</strong>: Usuario de la base de datos.</li>
     *   <li><strong>DB_PASSWORD</strong>: Contraseña de la base de datos.</li>
     * </ul>
     * 
     * <p>Si alguna de estas propiedades no está definida, se muestra un mensaje de error 
     * en la consola y se devuelve <code>null</code>.
     * 
     * @return La conexión a la base de datos ({@link Connection}), o <code>null</code> si hubo un error.
     */
    public static Connection getConnection() {
        String url = properties.getProperty("DB_URL");
        String user = properties.getProperty("DB_USER");
        String password = properties.getProperty("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            System.err.println("❌ Las propiedades de conexión no están definidas correctamente.");
            return null;
        }

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Conexión exitosa a la base de datos: " + url);
            return connection;
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Cierra la conexión proporcionada a la base de datos.
     * 
     * <p>Este método cierra la conexión si no es <code>null</code> y si aún está abierta.
     * 
     * <p>Si ocurre un error al cerrar la conexión, se muestra un mensaje de error 
     * en la consola y se imprime la traza de la excepción.
     * 
     * @param connection La conexión a la base de datos que se desea cerrar.
     */
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
