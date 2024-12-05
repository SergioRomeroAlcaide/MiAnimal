/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package dao;

import Modelo.Recordatorio;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordatorioDAO {

    // Crear un recordatorio
    public void createRecordatorio(Recordatorio recordatorio) throws SQLException {
        String sql = "INSERT INTO recordatorio (cliente_nombre, mascota_nombre, motivo, fecha_hora, enviado) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, recordatorio.getClienteNombre());
            stmt.setString(2, recordatorio.getMascotaNombre());
            stmt.setString(3, recordatorio.getMotivo());
            stmt.setTimestamp(4, Timestamp.valueOf(recordatorio.getFechaHora()));
            stmt.setBoolean(5, recordatorio.isEnviado());
            stmt.executeUpdate();
        }
    }

    // Leer todos los recordatorios
    public List<Recordatorio> getAllRecordatorios() throws SQLException {
        List<Recordatorio> recordatorios = new ArrayList<>();
        String sql = "SELECT id_recordatorio, cliente_nombre, mascota_nombre, motivo, fecha_hora, enviado FROM recordatorio";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                recordatorios.add(new Recordatorio(
                        rs.getInt("id_recordatorio"),
                        rs.getString("cliente_nombre"),
                        rs.getString("mascota_nombre"),
                        rs.getString("motivo"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getBoolean("enviado")
                ));
            }
        }
        return recordatorios;
    }

    // Actualizar un recordatorio
    public void updateRecordatorio(Recordatorio recordatorio) throws SQLException {
        String sql = "UPDATE recordatorio SET cliente_nombre = ?, mascota_nombre = ?, motivo = ?, fecha_hora = ?, enviado = ? WHERE id_recordatorio = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, recordatorio.getClienteNombre());
            stmt.setString(2, recordatorio.getMascotaNombre());
            stmt.setString(3, recordatorio.getMotivo());
            stmt.setTimestamp(4, Timestamp.valueOf(recordatorio.getFechaHora()));
            stmt.setBoolean(5, recordatorio.isEnviado());
            stmt.setInt(6, recordatorio.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar un recordatorio
    public void deleteRecordatorio(int idRecordatorio) throws SQLException {
        String sql = "DELETE FROM recordatorio WHERE id_recordatorio = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idRecordatorio);
            stmt.executeUpdate();
        }
    }
}
