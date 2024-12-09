/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Modelo.Usuario;
import Util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * @param usuario El usuario que se desea insertar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean insertar(Usuario usuario) {
        String query = "INSERT INTO Usuario (nombreUsuario, contraseña, rol) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContraseña());
            stmt.setString(3, usuario.getRol());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar el usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todos los usuarios almacenados en la base de datos.
     * 
     * @return Lista de objetos Usuario.
     */
    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM Usuario";
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
            System.err.println("Error al obtener todos los usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id El ID del usuario que se desea obtener.
     * @return El objeto Usuario correspondiente o null si no se encuentra.
     */
    public Usuario obtenerPorId(int id) {
        String query = "SELECT * FROM Usuario WHERE id = ?";
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
            System.err.println("Error al obtener el usuario con ID " + id + ": " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza la información de un usuario en la base de datos.
     * 
     * @param usuario El usuario con la información actualizada.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean actualizar(Usuario usuario) {
        String query = "UPDATE Usuario SET nombreUsuario = ?, contraseña = ?, rol = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getContraseña());
            stmt.setString(3, usuario.getRol());
            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el usuario con ID " + usuario.getId() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un usuario de la base de datos.
     * 
     * @param id El ID del usuario que se desea eliminar.
     * @return true si la operación se realiza con éxito, false en caso contrario.
     */
    public boolean eliminar(int id) {
        String query = "DELETE FROM Usuario WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el usuario con ID " + id + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Valida si el usuario y la contraseña son correctos.
     * 
     * @param nombreUsuario El nombre de usuario.
     * @param contraseña La contraseña del usuario.
     * @return true si el usuario y la contraseña coinciden, false en caso contrario.
     */
    public boolean validarUsuario(String nombreUsuario, String contraseña) {
        String query = "SELECT * FROM Usuario WHERE nombreUsuario = ? AND contraseña = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error al validar el usuario: " + e.getMessage());
            return false;
        }
    }
}
