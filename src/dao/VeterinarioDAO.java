/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package dao;

import Modelo.Veterinario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    private final Connection connection;

    public VeterinarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Crear un nuevo veterinario
    public void createVeterinario(Veterinario veterinario) throws SQLException {
        String sql = "INSERT INTO veterinarios (nombre, especialidad, telefono, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());
            stmt.executeUpdate();
        }
    }

    // Leer todos los veterinarios
    public List<Veterinario> readVeterinarios() throws SQLException {
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM veterinarios";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                veterinarios.add(new Veterinario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("email")
                ));
            }
        }
        return veterinarios;
    }

    // Actualizar un veterinario existente
    public void updateVeterinario(Veterinario veterinario) throws SQLException {
        String sql = "UPDATE veterinarios SET nombre = ?, especialidad = ?, telefono = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());
            stmt.setInt(5, veterinario.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar un veterinario por ID
    public void deleteVeterinario(int id) throws SQLException {
        String sql = "DELETE FROM veterinarios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}


