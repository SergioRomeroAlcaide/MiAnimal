
package Controlador;

import Modelo.Usuario;
import dao.UsuarioDAO;
import java.util.List;

/**
 * Clase LoginController que se encarga de la lógica de negocio relacionada con los usuarios.
 * Esta clase actúa como intermediaria entre la vista y la capa de acceso a datos.
 */
public class LoginController {

    /**
     * Instancia de UsuarioDAO para realizar operaciones de acceso a datos.
     */
    private final UsuarioDAO usuarioDAO;

    /**
     * Constructor que inicializa la instancia de UsuarioDAO.
     */
    public LoginController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * @param usuario El objeto Usuario que se desea insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarUsuario(Usuario usuario) {
        return usuarioDAO.insertar(usuario);
    }

    /**
     * Obtiene una lista de todos los usuarios registrados en la base de datos.
     * 
     * @return Una lista de objetos Usuario que representan a todos los usuarios.
     */
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioDAO.obtenerTodos();
    }

    /**
     * Obtiene un usuario específico por su ID.
     * 
     * @param id El identificador del usuario que se desea obtener.
     * @return El objeto Usuario correspondiente al ID proporcionado, o null si no se encuentra.
     */
    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioDAO.obtenerPorId(id);
    }

    /**
     * Actualiza la información de un usuario en la base de datos.
     * 
     * @param usuario El objeto Usuario que contiene la información actualizada.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    public boolean actualizarUsuario(Usuario usuario) {
        return usuarioDAO.actualizar(usuario);
    }

    /**
     * Elimina un usuario de la base de datos por su ID.
     * 
     * @param id El identificador del usuario que se desea eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     */
    public boolean eliminarUsuario(int id) {
        return usuarioDAO.eliminar(id);
    }

    /**
     * Valida si un usuario con las credenciales proporcionadas existe en la base de datos.
     * 
     * @param nombreUsuario El nombre de usuario proporcionado.
     * @param contraseña La contraseña proporcionada.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean validarUsuario(String nombreUsuario, String contraseña) {
        return usuarioDAO.validarUsuario(nombreUsuario, contraseña);
    }
}
