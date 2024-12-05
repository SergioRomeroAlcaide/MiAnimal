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

    private Connection conexion;

    // Método para configurar la conexión
    public void setConnection(Connection conexion) {
        this.conexion = conexion;
    }

    // Obtener todas las citas
    public List<Cita> getAllCitas() throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String query = "SELECT * FROM Cita";

        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getInt("id"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("motivo"),
                        null, // Placeholder para mascotaNombre, si no está en la tabla
                        null, // Placeholder para clienteNombre, si no está en la tabla
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id"),
                        rs.getBoolean("recordatorio_enviado")
                );
                citas.add(cita);
            }
        }

        return citas;
    }

    // Crear una nueva cita
    public void createCita(Cita cita) throws SQLException {
        String query = "INSERT INTO Cita (fecha_hora, motivo, mascota_id, veterinario_id, recordatorio_enviado) " +
                       "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascotaId());
            stmt.setInt(4, cita.getVeterinarioId());
            stmt.setBoolean(5, cita.isRecordatorioEnviado());
            stmt.executeUpdate();
        }
    }

    // Actualizar una cita existente
    public void updateCita(Cita cita) throws SQLException {
        String query = "UPDATE Cita SET fecha_hora = ?, motivo = ?, mascota_id = ?, veterinario_id = ?, recordatorio_enviado = ? " +
                       "WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascotaId());
            stmt.setInt(4, cita.getVeterinarioId());
            stmt.setBoolean(5, cita.isRecordatorioEnviado());
            stmt.setInt(6, cita.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar una cita
    public void deleteCita(int citaId) throws SQLException {
        String query = "DELETE FROM Cita WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, citaId);
            stmt.executeUpdate();
        }
    }

    // Obtener una cita por ID
    public Cita getCitaById(int id) throws SQLException {
        String query = "SELECT * FROM Cita WHERE id = ?";
        Cita cita = null;

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cita = new Cita(
                            rs.getInt("id"),
                            rs.getTimestamp("fecha_hora").toLocalDateTime(),
                            rs.getString("motivo"),
                            null,
                            null,
                            rs.getInt("mascota_id"),
                            rs.getInt("veterinario_id"),
                            rs.getBoolean("recordatorio_enviado")
                    );
                }
            }
        }

        return cita;
    }
}
