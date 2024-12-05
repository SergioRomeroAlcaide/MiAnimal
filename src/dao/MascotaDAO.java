/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package dao;

import Modelo.Mascota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {

    private Connection conexion;

    // Configurar la conexión
    public void setConnection(Connection conexion) {
        this.conexion = conexion;
    }

    // Obtener todas las mascotas
    public List<Mascota> getAllMascotas() throws SQLException {
        List<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM Mascota";

        try (PreparedStatement stmt = conexion.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

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
        }
        return mascotas;
    }

    // Crear una nueva mascota
    public void createMascota(Mascota mascota) throws SQLException {
        String query = "INSERT INTO Mascota (nombre, especie, raza, edad, cliente_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setInt(5, mascota.getClienteId());
            stmt.executeUpdate();
        }
    }

    // Actualizar una mascota
    public void updateMascota(Mascota mascota) throws SQLException {
        String query = "UPDATE Mascota SET nombre = ?, especie = ?, raza = ?, edad = ?, cliente_id = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setInt(5, mascota.getClienteId());
            stmt.setInt(6, mascota.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar una mascota
    public void deleteMascota(int id) throws SQLException {
        String query = "DELETE FROM Mascota WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Obtener una mascota por ID
    public Mascota getMascotaById(int id) throws SQLException {
        String query = "SELECT * FROM Mascota WHERE id = ?";
        Mascota mascota = null;

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mascota = new Mascota(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("especie"),
                            rs.getString("raza"),
                            rs.getInt("edad"),
                            rs.getInt("cliente_id")
                    );
                }
            }
        }
        return mascota;
    }
}
