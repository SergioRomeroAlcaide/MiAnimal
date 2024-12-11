package Util;
import Modelo.Cliente;
import dao.ClienteDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBConnectionTest {

    public static void main(String[] args) {
        
        ClienteDAO clienteDAO = new ClienteDAO();
        
        // 🔥 Llamar al método obtenerTodos() y mostrar la lista
        List<Cliente> clientes = clienteDAO.obtenerTodos();
        
        System.out.println("📋 Lista de clientes obtenidos:");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        try (Connection connection = DBConnection.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                System.out.println("✅ Conexión establecida exitosamente.");
                System.out.println("🔍 Estado de la conexión: " + (connection.isClosed() ? "Cerrada" : "Abierta"));
                System.out.println("🔍 URL de la conexión: " + connection.getMetaData().getURL());
                System.out.println("🔍 Usuario de la conexión: " + connection.getMetaData().getUserName());
            } else {
                System.err.println("❌ La conexión está cerrada o es nula.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error general: " + e.getMessage());
            e.printStackTrace();
        }
    }
}}

