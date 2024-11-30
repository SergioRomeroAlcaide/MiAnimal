/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package dao;

import Modelo.Cita;
import Util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {

    private Connection connection;

    public CitaDAO(Connection connection) {
        if (connection == null) {
            throw new IllegalArgumentException("La conexión a la base de datos no puede ser nula.");
        }
        this.connection = connection;
    }

    // Crear una nueva cita
    public boolean createCita(Cita cita) {
        String query = "INSERT INTO citas (fecha_hora, motivo, mascota_id, veterinario_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFecha_hora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascotaId());
            stmt.setInt(4, cita.getVeterinarioId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear la cita: " + e.getMessage());
            return false;
        }
    }

    // Leer una cita por ID
    public Cita readCita(int id) {
        String query = "SELECT * FROM citas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cita(
                        rs.getInt("id"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("motivo"),
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al leer la cita: " + e.getMessage());
        }
        return null;
    }

    // Actualizar una cita existente
    public boolean updateCita(Cita cita) {
        String query = "UPDATE citas SET fecha_hora = ?, motivo = ?, mascota_id = ?, veterinario_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, Timestamp.valueOf(cita.getFecha_hora()));
            stmt.setString(2, cita.getMotivo());
            stmt.setInt(3, cita.getMascotaId());
            stmt.setInt(4, cita.getVeterinarioId());
            stmt.setInt(5, cita.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar la cita: " + e.getMessage());
            return false;
        }
    }

    // Eliminar una cita por ID
    public boolean deleteCita(int id) {
        String query = "DELETE FROM citas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar la cita: " + e.getMessage());
            return false;
        }
    }

    // Obtener todas las citas
    public List<Cita> getAllCitas() {
        List<Cita> citas = new ArrayList<>();
        String query = "SELECT * FROM citas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                citas.add(new Cita(
                        rs.getInt("id"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getString("motivo"),
                        rs.getInt("mascota_id"),
                        rs.getInt("veterinario_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las citas: " + e.getMessage());
        }
        return citas;
    }
}


