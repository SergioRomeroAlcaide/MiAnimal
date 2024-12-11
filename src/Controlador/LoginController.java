
package Controlador;

import Modelo.Usuario;
import dao.UsuarioDAO;
import java.util.List;

public class LoginController {

    private final UsuarioDAO usuarioDAO;

    public LoginController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean insertarUsuario(Usuario usuario) {
        return usuarioDAO.insertar(usuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.obtenerTodos();
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioDAO.obtenerPorId(id);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizar(usuario);
    }

    public boolean eliminarUsuario(int id) {
        return usuarioDAO.eliminar(id);
    }

    public boolean validarUsuario(String nombreUsuario, String contraseña) {
        return usuarioDAO.validarUsuario(nombreUsuario, contraseña);
    }
}

