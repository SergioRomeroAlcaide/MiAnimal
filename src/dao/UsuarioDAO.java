package dao;

import Modelo.Usuario;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    /**
     * Inserta un nuevo usuario en la base de datos.
     */
    public boolean insertar(Usuario usuario) {
        String query = "INSERT INTO usuario (nombreUsuario, contraseña, rol) VALUES (?, SHA2(?, 256), ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContraseña());
            stmt.setString(3, usuario.getRol());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al insertar el usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     */
    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombreUsuario"),
                    rs.getString("contraseña"),
                    rs.getString("rol")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener todos los usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    /**
     * Obtiene un usuario por su ID.
     */
    public Usuario obtenerPorId(int id) {
        String query = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombreUsuario"),
                    rs.getString("contraseña"),
                    rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el usuario con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza la información de un usuario en la base de datos.
     */
    public boolean actualizar(Usuario usuario) {
        String query = "UPDATE usuario SET nombreUsuario = ?, contraseña = SHA2(?, 256), rol = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContraseña());
            stmt.setString(3, usuario.getRol());
            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar el usuario con ID " + usuario.getId() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un usuario de la base de datos.
     */
    public boolean eliminar(int id) {
        String query = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar el usuario con ID " + id + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Valida si el usuario y la contraseña son correctos.
     */
    public boolean validarUsuario(String nombreUsuario, String contraseña) {
        String query = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contraseña = SHA2(?, 256)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("❌ Error al validar el usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
