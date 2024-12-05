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

    private Connection conexion;

    public void setConnection(Connection conexion) {
        this.conexion = conexion;
    }

    public void createVeterinario(Veterinario veterinario) throws SQLException {
        String sql = "INSERT INTO veterinario (nombre, especialidad, telefono, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());
            stmt.executeUpdate();
        }
    }

    public List<Veterinario> getAllVeterinarios() throws SQLException {
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT id_veterinario, nombre, especialidad, telefono, email FROM veterinario";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                veterinarios.add(new Veterinario(
                        rs.getInt("id_veterinario"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("email")
                ));
            }
        }
        return veterinarios;
    }

    public void updateVeterinario(Veterinario veterinario) throws SQLException {
        String sql = "UPDATE veterinario SET nombre = ?, especialidad = ?, telefono = ?, email = ? WHERE id_veterinario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());
            stmt.setInt(5, veterinario.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteVeterinario(int idVeterinario) throws SQLException {
        String sql = "DELETE FROM veterinario WHERE id_veterinario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idVeterinario);
            stmt.executeUpdate();
        }
    }
}
