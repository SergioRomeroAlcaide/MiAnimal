/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Cliente;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    /**
     * Inserta un nuevo cliente en la base de datos.
     * 
     * @param cliente El cliente que se desea insertar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean insertar(Cliente cliente) {
        String query = "INSERT INTO Cliente (nombre, telefono, direccion, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getEmail());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar el cliente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todos los clientes almacenados en la base de datos.
     * 
     * @return Lista de objetos Cliente.
     */
    public List<Cliente> obtenerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los clientes: " + e.getMessage());
        }
        return clientes;
    }

    /**
     * Obtiene un cliente por su ID.
     * 
     * @param id El ID del cliente que se desea obtener.
     * @return El objeto Cliente correspondiente o null si no se encuentra.
     */
    public Cliente obtenerPorId(int id) {
        String query = "SELECT * FROM Cliente WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el cliente con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza la información de un cliente en la base de datos.
     * 
     * @param cliente El cliente con la información actualizada.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean actualizar(Cliente cliente) {
        String query = "UPDATE Cliente SET nombre = ?, telefono = ?, direccion = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el cliente con ID " + cliente.getId() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     * 
     * @param id El ID del cliente que se desea eliminar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean eliminar(int id) {
        String query = "DELETE FROM Cliente WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el cliente con ID " + id + ": " + e.getMessage());
            return false;
        }
    }
}
