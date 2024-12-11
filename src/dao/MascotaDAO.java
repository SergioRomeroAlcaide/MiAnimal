package dao;

import Modelo.Mascota;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {

    /**
     * Inserta una nueva mascota en la base de datos.
     * @param mascota Mascota con los datos a insertar.
     * @return true si se insertó correctamente, false de lo contrario.
     */
    public boolean insertar(Mascota mascota) {
        String sql = "INSERT INTO mascota (nombre, especie, raza, edad, cliente_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setInt(5, mascota.getCliente_id());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar mascota: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todas las mascotas de la base de datos.
     * @return Lista de mascotas.
     */
    public List<Mascota> obtenerTodas() {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascota";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                mascotas.add(new Mascota(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especie"),
                    rs.getString("raza"),
                    rs.getInt("edad"),
                    rs.getInt("cliente_id")
                ));
            }
            System.out.println("📋 Mascotas obtenidas: " + mascotas); // Verifica la lista
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener las mascotas: " + e.getMessage());
            e.printStackTrace();
        }
        return mascotas;
    }

    /**
     * Obtiene una mascota por su ID.
     * @param id ID de la mascota.
     * @return Mascota con el ID especificado o null si no se encuentra.
     */
    public Mascota obtenerPorId(int id) {
        String sql = "SELECT * FROM mascota WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener la mascota por ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza los datos de una mascota en la base de datos.
     * @param mascota Mascota con los nuevos datos a actualizar.
     * @return true si se actualizó correctamente, false de lo contrario.
     */
    public boolean actualizar(Mascota mascota) {
        String sql = "UPDATE mascota SET nombre = ?, especie = ?, raza = ?, edad = ?, cliente_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setInt(5, mascota.getCliente_id());
            stmt.setInt(6, mascota.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar la mascota: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina una mascota de la base de datos.
     * @param id ID de la mascota a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM mascota WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar la mascota: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca mascotas por nombre o especie.
     * @param criterio Texto a buscar (puede ser nombre o especie).
     * @return Lista de mascotas que coincidan con el criterio.
     */
    public List<Mascota> buscarMascotas(String criterio) {
        List<Mascota> mascotas = new ArrayList<>();
        String sql = "SELECT * FROM mascota WHERE nombre LIKE ? OR especie LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + criterio + "%");
            stmt.setString(2, "%" + criterio + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    mascotas.add(new Mascota(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especie"),
                        rs.getString("raza"),
                        rs.getInt("edad"),
                        rs.getInt("cliente_id")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar mascotas: " + e.getMessage());
        }
        return mascotas;
    }
}
