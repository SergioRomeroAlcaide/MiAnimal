/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package dao;

import Modelo.Cita;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    private final Connection connection;

    public CitaDAO(Connection connection) {
        this.connection = connection;
    }

    // Crear una nueva cita
    public void createCita(Cita cita) throws SQLException {
        String sql = "INSERT INTO citas (fecha_hora, motivo, mascota_id, veterinario_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascotaId());
            stmt.setInt(4, cita.getVeterinarioId());
            stmt.executeUpdate();
        }
    }

    // Leer todas las citas
    public List<Cita> readCitas() throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                citas.add(new Cita(
                        rs.getInt("id"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("motivo"),
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id")
                ));
            }
        }
        return citas;
    }

    // Actualizar una cita existente
    public void updateCita(Cita cita) throws SQLException {
        String sql = "UPDATE citas SET fecha_hora = ?, motivo = ?, mascota_id = ?, veterinario_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascotaId());
            stmt.setInt(4, cita.getVeterinarioId());
            stmt.setInt(5, cita.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar una cita por ID
    public void deleteCita(int id) throws SQLException {
        String sql = "DELETE FROM citas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}




