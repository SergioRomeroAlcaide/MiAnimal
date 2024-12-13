package dao;

import Modelo.Veterinario;
import Util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    /**
     * Inserta un nuevo veterinario en la base de datos.
     * @param veterinario Veterinario con los datos a insertar.
     * @return true si se insertó correctamente, false de lo contrario.
     */
    public boolean insertar(Veterinario veterinario) {
        String sql = "INSERT INTO veterinario (nombre, especialidad, telefono, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar veterinario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todos los veterinarios de la base de datos.
     * @return Lista de veterinarios.
     */
    public List<Veterinario> obtenerTodos() {
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM veterinario";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                veterinarios.add(new Veterinario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad"),
                    rs.getString("telefono"),
                    rs.getString("email")
                ));
            }
            System.out.println("📋 Veterinarios obtenidos: " + veterinarios);
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener los veterinarios: " + e.getMessage());
            e.printStackTrace();
        }
        return veterinarios;
    }

    /**
     * Obtiene un veterinario por su ID.
     * @param id ID del veterinario.
     * @return Veterinario con el ID especificado o null si no se encuentra.
     */
    public Veterinario obtenerPorId(int id) {
        String sql = "SELECT * FROM veterinario WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Veterinario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el veterinario por ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza los datos de un veterinario en la base de datos.
     * @param veterinario Veterinario con los nuevos datos a actualizar.
     * @return true si se actualizó correctamente, false de lo contrario.
     */
    public boolean actualizar(Veterinario veterinario) {
        String sql = "UPDATE veterinario SET nombre = ?, especialidad = ?, telefono = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veterinario.getNombre());
            stmt.setString(2, veterinario.getEspecialidad());
            stmt.setString(3, veterinario.getTelefono());
            stmt.setString(4, veterinario.getEmail());
            stmt.setInt(5, veterinario.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar veterinario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un veterinario de la base de datos.
     * @param id ID del veterinario a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminar(int id) {
        String sql = "DELETE FROM veterinario WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar veterinario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Busca veterinarios por nombre o especialidad.
     * @param criterio Texto a buscar (puede ser nombre o especialidad).
     * @return Lista de veterinarios que coincidan con el criterio.
     */
    public List<Veterinario> buscarVeterinarios(String criterio) {
        List<Veterinario> veterinarios = new ArrayList<>();
        String sql = "SELECT * FROM veterinario WHERE nombre LIKE ? OR especialidad LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + criterio + "%");
            stmt.setString(2, "%" + criterio + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    veterinarios.add(new Veterinario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("especialidad"),
                        rs.getString("telefono"),
                        rs.getString("email")
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al buscar veterinarios: " + e.getMessage());
        }
        return veterinarios;
    }
}
