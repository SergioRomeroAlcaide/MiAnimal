/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package dao;

import Modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conexion;

    // Configurar la conexión
    public void setConnection(Connection conexion) {
        this.conexion = conexion;
    }

    // Obtener todos los clientes
    public List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM Cliente";

        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"), // Ajusta este campo según el modelo
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("email")
                );
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    // Crear un nuevo cliente
    public void createCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO Cliente (nombre, telefono, direccion, email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getEmail());
            stmt.executeUpdate();
        }
    }

    // Actualizar un cliente
    public void updateCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE Cliente SET nombre = ?, telefono = ?, direccion = ?, email = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar un cliente
    public void deleteCliente(int id) throws SQLException {
        String query = "DELETE FROM Cliente WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Obtener un cliente por ID
    public Cliente getClienteById(int id) throws SQLException {
        String query = "SELECT * FROM Cliente WHERE id = ?";
        Cliente cliente = null;

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("email")
                    );
                }
            }
        }
        return cliente;
    }
}
