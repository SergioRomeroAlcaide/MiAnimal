/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Cita;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    /**
     * Inserta una nueva cita en la base de datos.
     * @param cita Cita con los datos a insertar.
     * @return true si se insertó correctamente, false de lo contrario.
     */
    public boolean insertar(Cita cita) {
        String sql = "INSERT INTO cita (fechaHora, motivo, mascota_id, veterinario_id, recordatorioEnviado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cita.getFechaHora());
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascota_id());
            stmt.setInt(4, cita.getVeterinario_id());
            stmt.setBoolean(5, cita.isRecordatorioEnviado());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar la cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todas las citas de la base de datos.
     * @return Lista de citas.
     */
    public List<Cita> obtenerTodas() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM cita";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                citas.add(new Cita(
                        rs.getInt("id"),
                        rs.getString("fechaHora"),
                        rs.getString("motivo"),
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id"),
                        rs.getBoolean("recordatorioEnviado")
                ));
            }
            System.out.println("📋 Citas obtenidas: " + citas);
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener las citas: " + e.getMessage());
            e.printStackTrace();
        }
        return citas;
    }

    /**
     * Obtiene una cita por su ID.
     * @param id ID de la cita.
     * @return Cita con el ID especificado o null si no se encuentra.
     */
    public Cita obtenerPorId(int id) {
        String sql = "SELECT * FROM cita WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cita(
                            rs.getInt("id"),
                            rs.getString("fechaHora"),
                            rs.getString("motivo"),
                            rs.getInt("mascota_id"),
                            rs.getInt("veterinario_id"),
                            rs.getBoolean("recordatorioEnviado")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener la cita por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Actualiza los datos de una cita en la base de datos.
     * @param cita Cita con los nuevos datos a actualizar.
     * @return true si se actualizó correctamente, false de lo contrario.
     */
    public boolean actualizar(Cita cita) {
        String sql = "UPDATE cita SET fechaHora = ?, motivo = ?, mascota_id = ?, veterinario_id = ?, recordatorioEnviado = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cita.getFechaHora());
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascota_id());
            stmt.setInt(4, cita.getVeterinario_id());
            stmt.setBoolean(5, cita.isRecordatorioEnviado());
            stmt.setInt(6, cita.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar la cita: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina una cita de la base de datos.
     * @param id ID de la cita a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM cita WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar la cita: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Busca citas por fecha, motivo o veterinario.
     * @param criterio Texto a buscar (puede ser en fecha, motivo o veterinario).
     * @return Lista de citas que coincidan con el criterio.
     */
    public List<Cita> buscarCitas(String criterio) {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM cita WHERE fechaHora LIKE ? OR motivo LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + criterio + "%");
            stmt.setString(2, "%" + criterio + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    citas.add(new Cita(
                            rs.getInt("id"),
                            rs.getString("fechaHora"),
                            rs.getString("motivo"),
                            rs.getInt("mascota_id"),
                            rs.getInt("veterinario_id"),
                            rs.getBoolean("recordatorioEnviado")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar citas: " + e.getMessage());
            e.printStackTrace();
        }
        return citas;
    }

    /**
     * Marca una cita como "Recordatorio Enviado" en la base de datos.
     * @param id ID de la cita a marcar.
     * @return true si se marcó correctamente, false de lo contrario.
     */
    public boolean marcarRecordatorioEnviado(int id) {
        String sql = "UPDATE cita SET recordatorioEnviado = 1 WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al marcar el recordatorio de la cita como enviado: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene las citas próximas en los próximos días.
     * @param dias Número de días para buscar las próximas citas.
     * @return Lista de citas próximas.
     */
    public List<Cita> obtenerCitasProximas(int dias) {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM cita WHERE fechaHora >= NOW() AND fechaHora <= DATE_ADD(NOW(), INTERVAL ? DAY)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dias);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    citas.add(new Cita(
                            rs.getInt("id"),
                            rs.getString("fechaHora"),
                            rs.getString("motivo"),
                            rs.getInt("mascota_id"),
                            rs.getInt("veterinario_id"),
                            rs.getBoolean("recordatorioEnviado")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener las citas próximas: " + e.getMessage());
            e.printStackTrace();
        }
        return citas;
    }
}
