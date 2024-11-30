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
        if (connection == null) {
            throw new IllegalArgumentException("La conexión a la base de datos no puede ser nula.");
        }
        this.connection = connection;
    }

    // Método para agregar una nueva mascota
    public void agregarMascota(Mascota mascota) throws SQLException {
        String sql = "INSERT INTO mascota (nombre, especie, raza, edad, cliente_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setInt(5, mascota.getClienteId());
            stmt.executeUpdate();

            // Obtener el ID generado y asignarlo a la mascota
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    mascota.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Método para obtener todas las mascotas
    public List<Mascota> obtenerMascotas() throws SQLException {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascota";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
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

    // Método para obtener una mascota por ID
    public Mascota obtenerMascotaPorId(int id) throws SQLException {
        Mascota mascota = null;
        String sql = "SELECT * FROM mascota WHERE id = ?";
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
                        rs.getInt("cliente_id")
                    );
                }
            }
        }
        return mascota;
    }

    // Método para actualizar una mascota
    public void actualizarMascota(Mascota mascota) throws SQLException {
        String sql = "UPDATE mascota SET nombre = ?, especie = ?, raza = ?, edad = ?, cliente_id = ? WHERE id = ?";
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

    // Método para eliminar una mascota por ID
    public void eliminarMascota(int id) throws SQLException {
        String sql = "DELETE FROM mascota WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}




