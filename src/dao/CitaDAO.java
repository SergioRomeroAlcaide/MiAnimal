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
        String sql = "INSERT INTO citas (fecha_hora, motivo, mascota_nombre, cliente_nombre, mascota_id, veterinario_id, recordatorio_enviado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setString(3, cita.getMascotaNombre());
            stmt.setString(4, cita.getClienteNombre());
            stmt.setInt(5, cita.getMascotaId());
            stmt.setInt(6, cita.getVeterinarioId());
            stmt.setBoolean(7, cita.isRecordatorioEnviado());
            stmt.executeUpdate();
        }
    }

    // Leer todas las citas
    public List<Cita> readAllCitas() throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT idPrimaria, fecha_hora, motivo, mascota_nombre, cliente_nombre, mascota_id, veterinario_id, recordatorio_enviado "
                   + "FROM citas;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getInt("idPrimaria"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("motivo"),
                        rs.getString("mascota_nombre"),
                        rs.getString("cliente_nombre"),
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id"),
                        rs.getBoolean("recordatorio_enviado")
                );
                citas.add(cita);
            }
        }
        return citas;
    }

    // Leer próximas citas
    public List<Cita> readProximasCitas() throws SQLException {
        List<Cita> proximasCitas = new ArrayList<>();
        String sql = "SELECT idPrimaria, fecha_hora, motivo, mascota_nombre, cliente_nombre, mascota_id, veterinario_id, recordatorio_enviado "
                   + "FROM citas "
                   + "WHERE fecha_hora > NOW() "
                   + "ORDER BY fecha_hora ASC;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cita cita = new Cita(
                        rs.getInt("idPrimaria"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("motivo"),
                        rs.getString("mascota_nombre"),
                        rs.getString("cliente_nombre"),
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id"),
                        rs.getBoolean("recordatorio_enviado")
                );
                proximasCitas.add(cita);
            }
        }
        return proximasCitas;
    }

    // Actualizar una cita
    public void updateCita(Cita cita) throws SQLException {
        String sql = "UPDATE cita SET fecha_hora = ?, motivo = ?, mascota_nombre = ?, cliente_nombre = ?, "
                   + "mascota_id = ?, veterinario_id = ?, recordatorio_enviado = ? WHERE idPrimaria = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFechaHora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setString(3, cita.getMascotaNombre());
            stmt.setString(4, cita.getClienteNombre());
            stmt.setInt(5, cita.getMascotaId());
            stmt.setInt(6, cita.getVeterinarioId());
            stmt.setBoolean(7, cita.isRecordatorioEnviado());
            stmt.setInt(8, cita.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar una cita
    public void deleteCita(int citaId) throws SQLException {
        String sql = "DELETE FROM citasWHERE idPrimaria = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citaId);
            stmt.executeUpdate();
        }
    }

    // Marcar recordatorio como enviado
    public void marcarRecordatorioEnviado(int citaId) throws SQLException {
        String sql = "UPDATE cita SET recordatorio_enviado = TRUE WHERE idPrimaria = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, citaId);
            stmt.executeUpdate();
        }
    }
}
