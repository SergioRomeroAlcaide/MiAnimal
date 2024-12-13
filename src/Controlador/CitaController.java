package Controlador;

import Modelo.Cita;
import dao.CitaDAO;
import java.util.List;

public class CitaController {

    private final CitaDAO citaDAO;

    /**
     * Constructor de la clase CitaController.
     * Se crea una instancia de CitaDAO para acceder a la base de datos.
     */
    public CitaController() {
        this.citaDAO = new CitaDAO();
    }

    /**
     * Inserta una nueva cita en la base de datos.
     * @param cita Cita con los datos a insertar.
     * @return true si se insertó correctamente, false de lo contrario.
     */
    public boolean insertarCita(Cita cita) {
        if (validarDatosCita(cita)) {
            return citaDAO.insertar(cita);
        } else {
            System.err.println("❌ Los datos de la cita no son válidos.");
            return false;
        }
    }

    /**
     * Obtiene todas las citas de la base de datos.
     * @return Lista de citas.
     */
    public List<Cita> obtenerTodasLasCitas() {
        return citaDAO.obtenerTodas();
    }

    /**
     * Obtiene una cita por su ID.
     * @param id ID de la cita.
     * @return Cita con el ID especificado o null si no se encuentra.
     */
    public Cita obtenerCitaPorId(int id) {
        return citaDAO.obtenerPorId(id);
    }

    /**
     * Actualiza los datos de una cita en la base de datos.
     * @param cita Cita con los nuevos datos a actualizar.
     * @return true si se actualizó correctamente, false de lo contrario.
     */
    public boolean actualizarCita(Cita cita) {
        if (validarDatosCita(cita)) {
            return citaDAO.actualizar(cita);
        } else {
            System.err.println("❌ Los datos de la cita no son válidos.");
            return false;
        }
    }

    /**
     * Elimina una cita de la base de datos.
     * @param id ID de la cita a eliminar.
     * @return true si se eliminó correctamente, false de lo contrario.
     */
    public boolean eliminarCita(int id) {
        return citaDAO.eliminar(id);
    }

    /**
     * Busca citas por el motivo de la cita.
     * @param criterio Texto a buscar (puede ser parte del motivo).
     * @return Lista de citas que coincidan con el criterio.
     */
    public List<Cita> buscarCitas(String criterio) {
        if (criterio != null && !criterio.trim().isEmpty()) {
            return citaDAO.buscarCitas(criterio);
        } else {
            System.err.println("❌ El criterio de búsqueda está vacío.");
            return List.of();
        }
    }

    /**
     * Valida los datos de una cita.
     * @param cita Cita a validar.
     * @return true si los datos son válidos, false de lo contrario.
     */
    private boolean validarDatosCita(Cita cita) {
        if (cita == null) {
            System.err.println("❌ La cita es nula.");
            return false;
        }
        if (cita.getFechaHora() == null) {
            System.err.println("❌ La fecha y hora de la cita no pueden estar vacías.");
            return false;
        }
        if (cita.getMotivo() == null || cita.getMotivo().trim().isEmpty()) {
            System.err.println("❌ El motivo de la cita no puede estar vacío.");
            return false;
        }
        if (cita.getMascotaId() <= 0) {
            System.err.println("❌ El ID de la mascota debe ser mayor a 0.");
            return false;
        }
        if (cita.getVeterinarioId() <= 0) {
            System.err.println("❌ El ID del veterinario debe ser mayor a 0.");
            return false;
        }
        return true;
    }
}
