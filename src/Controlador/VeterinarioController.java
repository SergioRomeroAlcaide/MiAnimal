package Controlador;

import Modelo.Veterinario;
import dao.VeterinarioDAO;
import java.util.List;

public class VeterinarioController {

    private final VeterinarioDAO veterinarioDAO;

    /**
     * Constructor de la clase VeterinarioController
     * Se crea una instancia de VeterinarioDAO para acceder a la base de datos
     */
    public VeterinarioController() {
        this.veterinarioDAO = new VeterinarioDAO();
    }

    /**
     * Inserta un veterinario en la base de datos
     * @param veterinario Veterinario con los datos a insertar
     * @return true si se insertó correctamente, false de lo contrario
     */
    public boolean insertarVeterinario(Veterinario veterinario) {
        if (validarDatosVeterinario(veterinario)) {
            return veterinarioDAO.insertar(veterinario);
        } else {
            System.err.println("Los datos del veterinario no son válidos.");
            return false;
        }
    }

    /**
     * Obtiene todos los veterinarios de la base de datos
     * @return Lista de veterinarios
     */
    public List<Veterinario> obtenerTodosLosVeterinarios() {
        return veterinarioDAO.obtenerTodos();
    }

    /**
     * Obtiene un veterinario por su ID
     * @param id ID del veterinario
     * @return Veterinario con el ID especificado, o null si no se encuentra
     */
    public Veterinario obtenerVeterinarioPorId(int id) {
        return veterinarioDAO.obtenerPorId(id);
    }

    /**
     * Actualiza los datos de un veterinario en la base de datos
     * @param veterinario Veterinario con los nuevos datos a actualizar
     * @return true si se actualizó correctamente, false de lo contrario
     */
    public boolean actualizarVeterinario(Veterinario veterinario) {
        if (validarDatosVeterinario(veterinario)) {
            return veterinarioDAO.actualizar(veterinario);
        } else {
            System.err.println("Los datos del veterinario no son válidos.");
            return false;
        }
    }

    /**
     * Elimina un veterinario de la base de datos
     * @param id ID del veterinario a eliminar
     * @return true si se eliminó correctamente, false de lo contrario
     */
    public boolean eliminarVeterinario(int id) {
        return veterinarioDAO.eliminar(id);
    }

    /**
     * Busca veterinarios por nombre o especialidad
     * @param criterio Texto a buscar (puede ser nombre o especialidad)
     * @return Lista de veterinarios que coincidan con el criterio
     */
    public List<Veterinario> buscarVeterinarios(String criterio) {
        if (criterio != null && !criterio.trim().isEmpty()) {
            return veterinarioDAO.buscarVeterinarios(criterio);
        } else {
            System.err.println("El criterio de búsqueda está vacío.");
            return List.of();
        }
    }

    /**
     * Verifica si un veterinario con un ID específico existe en la base de datos
     * @param id ID del veterinario a verificar
     * @return true si el veterinario existe, false de lo contrario
     */
    public boolean existeVeterinario(int id) {
        return veterinarioDAO.obtenerPorId(id) != null;
    }

    /**
     * Valida los datos de un veterinario
     * @param veterinario Veterinario a validar
     * @return true si los datos son válidos, false de lo contrario
     */
    private boolean validarDatosVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            System.err.println("❌ El veterinario es nulo.");
            return false;
        }
        if (veterinario.getNombre() == null || veterinario.getNombre().trim().isEmpty()) {
            System.err.println("❌ El nombre del veterinario no puede estar vacío.");
            return false;
        }
        if (veterinario.getEspecialidad() == null || veterinario.getEspecialidad().trim().isEmpty()) {
            System.err.println("❌ La especialidad del veterinario no puede estar vacía.");
            return false;
        }
        if (veterinario.getTelefono() == null || !veterinario.getTelefono().matches("\\d{9,12}")) {
            System.err.println("❌ El teléfono debe tener entre 9 y 12 dígitos.");
            return false;
        }
        if (veterinario.getEmail() == null || !veterinario.getEmail().matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,3}$")) {
            System.err.println("❌ El email no tiene un formato válido.");
            return false;
        }
        return true;
    }
}
