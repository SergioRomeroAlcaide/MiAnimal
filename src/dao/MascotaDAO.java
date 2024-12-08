/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Mascota;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {

    /**
     * Inserta una nueva mascota en la base de datos.
     * 
     * @param mascota La mascota que se desea insertar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean insertar(Mascota mascota) {
        String query = "INSERT INTO Mascota (nombre, especie, raza, edad, cliente_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setInt(5, mascota.getCliente_id());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar la mascota: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todas las mascotas almacenadas en la base de datos.
     * 
     * @return Lista de objetos Mascota.
     */
    public List<Mascota> obtenerTodas() {
        List<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM Mascota";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Mascota mascota = new Mascota(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getInt("edad"),
                    rs.getInt("cliente_id")
                );
                mascotas.add(mascota);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener todas las mascotas: " + e.getMessage());
        }
        return mascotas;
    }

    /**
     * Obtiene una mascota por su ID.
     * 
     * @param id El ID de la mascota que se desea obtener.
     * @return El objeto Mascota correspondiente o null si no se encuentra.
     */
    public Mascota obtenerPorId(int id) {
        String query = "SELECT * FROM Mascota WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Mascota(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getInt("edad"),
                    rs.getInt("cliente_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la mascota con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza la información de una mascota en la base de datos.
     * 
     * @param mascota La mascota con la información actualizada.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean actualizar(Mascota mascota) {
        String query = "UPDATE Mascota SET nombre = ?, especie = ?, raza = ?, edad = ?, cliente_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setInt(5, mascota.getCliente_id());
            stmt.setInt(6, mascota.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar la mascota con ID " + mascota.getId() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una mascota de la base de datos.
     * 
     * @param id El ID de la mascota que se desea eliminar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean eliminar(int id) {
        String query = "DELETE FROM Mascota WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar la mascota con ID " + id + ": " + e.getMessage());
            return false;
        }
    }
}
