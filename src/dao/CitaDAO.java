/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Cita;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    /**
     * Inserta una nueva cita en la base de datos.
     * 
     * @param cita La cita que se desea insertar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean insertar(Cita cita) {
        String query = "INSERT INTO Cita (fechaHora, motivo, mascota_id, veterinario_id, recordatorioEnviado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cita.getFechaHora());
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascotaId());
            stmt.setInt(4, cita.getVeterinarioId());
            stmt.setBoolean(5, cita.isRecordatorioEnviado());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar la cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todas las citas almacenadas en la base de datos.
     * 
     * @return Lista de objetos Cita.
     */
    public List<Cita> obtenerTodas() {
        List<Cita> citas = new ArrayList<>();
        String query = "SELECT * FROM Cita";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cita cita = new Cita(
                    rs.getInt("id"),
                    rs.getString("fechaHora"),
                    rs.getString("motivo"),
                    rs.getInt("mascota_id"),
                    rs.getInt("veterinario_id"),
                    rs.getBoolean("recordatorioEnviado")
                );
                citas.add(cita);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las citas: " + e.getMessage());
        }
        return citas;
    }

    /**
     * Obtiene una cita por su ID.
     * 
     * @param id El ID de la cita que se desea obtener.
     * @return El objeto Cita correspondiente o null si no se encuentra.
     */
    public Cita obtenerPorId(int id) {
        String query = "SELECT * FROM Cita WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

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
        } catch (SQLException e) {
            System.err.println("Error al obtener la cita con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza la información de una cita en la base de datos.
     * 
     * @param cita La cita con la información actualizada.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean actualizar(Cita cita) {
        String query = "UPDATE Cita SET fechaHora = ?, motivo = ?, mascota_id = ?, veterinario_id = ?, recordatorioEnviado = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cita.getFechaHora());
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascotaId());
            stmt.setInt(4, cita.getVeterinarioId());
            stmt.setBoolean(5, cita.isRecordatorioEnviado());
            stmt.setInt(6, cita.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar la cita con ID " + cita.getId() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una cita de la base de datos.
     * 
     * @param id El ID de la cita que se desea eliminar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean eliminar(int id) {
        String query = "DELETE FROM Cita WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar la cita con ID " + id + ": " + e.getMessage());
            return false;
        }
    }
}
