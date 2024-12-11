package dao;

import Modelo.Cliente;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    /**
     * Inserta un nuevo cliente en la base de datos.
     * @param cliente Cliente con los datos a insertar.
     * @return true si se insertó correctamente, false de lo contrario.
     */
    public boolean insertar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre, telefono, direccion, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todos los clientes de la base de datos.
     * @return Lista de clientes.
     */
    public List<Cliente> obtenerTodos() {
    List<Cliente> clientes = new ArrayList<>();
    String sql = "SELECT * FROM cliente";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            clientes.add(new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("telefono"),
                rs.getString("direccion"),
                rs.getString("email")
            ));
        }
        System.out.println("📋 Clientes obtenidos: " + clientes); // Verifica la lista
    } catch (SQLException e) {
        System.err.println("❌ Error al obtener los clientes: " + e.getMessage());
        e.printStackTrace();
    }
    return clientes;
}


    /**
     * Obtiene un cliente por su ID.
     * @param id ID del cliente.
     * @return Cliente con el ID especificado o null si no se encuentra.
     */
    public Cliente obtenerPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el cliente por ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza los datos de un cliente en la base de datos.
     * @param cliente Cliente con los nuevos datos a actualizar.
     * @return true si se actualizó correctamente, false de lo contrario.
     */
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre = ?, telefono = ?, direccion = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     * @param id ID del cliente a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca clientes por nombre o teléfono.
     * @param criterio Texto a buscar (puede ser nombre o teléfono).
     * @return Lista de clientes que coincidan con el criterio.
     */
    public List<Cliente> buscarClientes(String criterio) {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente WHERE nombre LIKE ? OR telefono LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + criterio + "%");
            stmt.setString(2, "%" + criterio + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    clientes.add(new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar clientes: " + e.getMessage());
        }
        return clientes;
    }
}
