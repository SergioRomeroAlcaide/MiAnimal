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
    private Connection connection;

    public VeterinarioDAO(Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("La conexión a la base de datos no puede ser nula.");
        }
        this.connection = connection;
    }

    // Crear veterinario
    public void agregarVeterinario(Veterinario veterinario) throws SQLException {
        String sql = "INSERT INTO veterinario (nombre, especialidad, telefono, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail()); // Cambio realizado aquí
            stmt.executeUpdate();
        }
    }

    // Leer todos los veterinarios
    public List<Veterinario> obtenerVeterinarios() throws SQLException {
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM veterinario";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Veterinario veterinario = new Veterinario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad"),
                    rs.getString("telefono"),
                    rs.getString("email") // Cambio realizado aquí
                );
                veterinarios.add(veterinario);
            }
        }
        return veterinarios;
    }

    // Leer veterinario por ID
    public Veterinario obtenerVeterinarioPorId(int id) throws SQLException {
        Veterinario veterinario = null;
        String sql = "SELECT * FROM veterinario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veterinario = new Veterinario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("email") // Cambio realizado aquí
                    );
                }
            }
        }
        return veterinario;
    }

    // Actualizar veterinario
    public void actualizarVeterinario(Veterinario veterinario) throws SQLException {
        String sql = "UPDATE veterinario SET nombre = ?, especialidad = ?, telefono = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail()); // Cambio realizado aquí
            stmt.setInt(5, veterinario.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar veterinario
    public void eliminarVeterinario(int id) throws SQLException {
        String sql = "DELETE FROM veterinario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

