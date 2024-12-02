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
    private Connection connection;

    public MascotaDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Crear una nueva mascota en la base de datos.
     */
    public void createMascota(Mascota mascota) throws SQLException {
        String sql = "INSERT INTO mascotas (nombre, especie, raza, edad, clienteId) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setInt(5, mascota.getClienteId());
            stmt.executeUpdate();
        }
    }

    /**
     * Actualizar una mascota existente en la base de datos.
     */
    public void updateMascota(Mascota mascota) throws SQLException {
        String sql = "UPDATE mascotas SET nombre = ?, especie = ?, raza = ?, edad = ?, clienteId = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setInt(5, mascota.getClienteId());
            stmt.setInt(6, mascota.getId());
            stmt.executeUpdate();
        }
    }

    /**
     * Eliminar una mascota de la base de datos por su ID.
     */
    public void deleteMascota(int id) throws SQLException {
        String sql = "DELETE FROM mascotas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    /**
     * Obtener todas las mascotas de la base de datos.
     */
    public List<Mascota> getAllMascotas() throws SQLException {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascotas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mascota mascota = new Mascota(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("raza"),
                        rs.getInt("edad"),
                        rs.getInt("clienteId")
                );
                mascotas.add(mascota);
            }
        }
        return mascotas;
    }

    /**
     * Obtener una mascota por su ID.
     */
    public Mascota getMascotaById(int id) throws SQLException {
        String sql = "SELECT * FROM mascotas WHERE id = ?";
        Mascota mascota = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mascota = new Mascota(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("especie"),
                            rs.getString("raza"),
                            rs.getInt("edad"),
                            rs.getInt("clienteId")
                    );
                }
            }
        }
        return mascota;
    }
}





