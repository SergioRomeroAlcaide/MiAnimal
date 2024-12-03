/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package DAO;

import Modelo.Recordatorio;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecordatorioDAO {
    private final Connection connection;

    public RecordatorioDAO(Connection connection) {
        this.connection = connection;
    }

    // Leer todos los recordatorios
    public List<Recordatorio> obtenerRecordatorios() throws SQLException {
        List<Recordatorio> recordatorios = new ArrayList<>();
        String sql = "SELECT c.id, cl.nombre AS cliente_nombre, m.nombre AS mascota_nombre, " +
                     "c.motivo, c.fecha_hora, c.recordatorio_enviado " +
                     "FROM citas c " +
                     "JOIN mascotas m ON c.mascota_id = m.id " +
                     "JOIN clientes cl ON m.cliente_id = cl.id " +
                     "WHERE c.fecha_hora > NOW() " +
                     "ORDER BY c.fecha_hora ASC;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Recordatorio recordatorio = new Recordatorio(
                        rs.getInt("id"),
                        rs.getString("cliente_nombre"),
                        rs.getString("mascota_nombre"),
                        rs.getString("motivo"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getBoolean("recordatorio_enviado")
                );
                recordatorios.add(recordatorio);
            }
        }
        return recordatorios;
    }

    // Marcar recordatorio como enviado
    public void marcarRecordatorioEnviado(int recordatorioId) throws SQLException {
        String sql = "UPDATE citas SET recordatorio_enviado = TRUE WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, recordatorioId);
            stmt.executeUpdate();
        }
    }
}
