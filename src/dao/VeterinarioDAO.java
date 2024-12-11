/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Veterinario;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    /**
     * Inserta un nuevo veterinario en la base de datos.
     * 
     * @param veterinario El veterinario que se desea insertar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean insertar(Veterinario veterinario) {
        String query = "INSERT INTO Veterinario (nombre, especialidad, telefono, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar el veterinario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todos los veterinarios almacenados en la base de datos.
     * 
     * @return Lista de objetos Veterinario.
     */
    public List<Veterinario> obtenerTodos() {
        List<Veterinario> veterinarios = new ArrayList<>();
        String query = "SELECT * FROM Veterinario";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Veterinario veterinario = new Veterinario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad"),
                    rs.getString("telefono"),
                    rs.getString("email")
                );
                veterinarios.add(veterinario);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todos los veterinarios: " + e.getMessage());
        }
        return veterinarios;
    }

    /**
     * Obtiene un veterinario por su ID.
     * 
     * @param id El ID del veterinario que se desea obtener.
     * @return El objeto Veterinario correspondiente o null si no se encuentra.
     */
    public Veterinario obtenerPorId(int id) {
        String query = "SELECT * FROM Veterinario WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Veterinario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad"),
                    rs.getString("telefono"),
                    rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el veterinario con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza la información de un veterinario en la base de datos.
     * 
     * @param veterinario El veterinario con la información actualizada.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean actualizar(Veterinario veterinario) {
        String query = "UPDATE Veterinario SET nombre = ?, especialidad = ?, telefono = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());
            stmt.setInt(5, veterinario.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el veterinario con ID " + veterinario.getId() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un veterinario de la base de datos.
     * 
     * @param id El ID del veterinario que se desea eliminar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean eliminar(int id) {
        String query = "DELETE FROM Veterinario WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el veterinario con ID " + id + ": " + e.getMessage());
            return false;
        }
    }
}
