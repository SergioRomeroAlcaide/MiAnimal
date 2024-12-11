package Controlador;

import Modelo.Mascota;
import dao.MascotaDAO;
import java.util.List;

public class MascotaController {

    private final MascotaDAO mascotaDAO;

    /**
     * Constructor de la clase MascotaController
     * Se crea una instancia de MascotaDAO para acceder a la base de datos
     */
    public MascotaController() {
        this.mascotaDAO = new MascotaDAO();
    }

    /**
     * Inserta una mascota en la base de datos
     * @param mascota Mascota con los datos a insertar
     * @return true si se insertó correctamente, false de lo contrario
     */
    public boolean insertarMascota(Mascota mascota) {
        if (validarDatosMascota(mascota)) {
            return mascotaDAO.insertar(mascota);
        } else {
            System.err.println("❌ Los datos de la mascota no son válidos.");
            return false;
        }
    }

    /**
     * Obtiene todas las mascotas de la base de datos
     * @return Lista de mascotas
     */
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaDAO.obtenerTodas();
    }

    /**
     * Obtiene una mascota por su ID
     * @param id ID de la mascota
     * @return Mascota con el ID especificado, o null si no se encuentra
     */
    public Mascota obtenerMascotaPorId(int id) {
        return mascotaDAO.obtenerPorId(id);
    }

    /**
     * Actualiza los datos de una mascota en la base de datos
     * @param mascota Mascota con los nuevos datos a actualizar
     * @return true si se actualizó correctamente, false de lo contrario
     */
    public boolean actualizarMascota(Mascota mascota) {
        if (validarDatosMascota(mascota)) {
            return mascotaDAO.actualizar(mascota);
        } else {
            System.err.println("❌ Los datos de la mascota no son válidos.");
            return false;
        }
    }

    /**
     * Elimina una mascota de la base de datos
     * @param id ID de la mascota a eliminar
     * @return true si se eliminó correctamente, false de lo contrario
     */
    public boolean eliminarMascota(int id) {
        return mascotaDAO.eliminar(id);
    }

    /**
     * Busca mascotas por nombre o especie
     * @param criterio Texto a buscar (puede ser nombre o especie)
     * @return Lista de mascotas que coincidan con el criterio
     */
    public List<Mascota> buscarMascotas(String criterio) {
        if (criterio != null && !criterio.trim().isEmpty()) {
            return mascotaDAO.buscarMascotas(criterio);
        } else {
            System.err.println("❌ El criterio de búsqueda está vacío.");
            return List.of();
        }
    }

    /**
     * Valida los datos de una mascota
     * @param mascota Mascota a validar
     * @return true si los datos son válidos, false de lo contrario
     */
    private boolean validarDatosMascota(Mascota mascota) {
        if (mascota == null) {
            System.err.println("❌ La mascota es nula.");
            return false;
        }
        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            System.err.println("❌ El nombre de la mascota no puede estar vacío.");
            return false;
        }
        if (mascota.getEspecie() == null || mascota.getEspecie().trim().isEmpty()) {
            System.err.println("❌ La especie de la mascota no puede estar vacía.");
            return false;
        }
        if (mascota.getEdad() < 0) {
            System.err.println("❌ La edad de la mascota no puede ser negativa.");
            return false;
        }
        if (mascota.getCliente_id() <= 0) {
            System.err.println("❌ El ID del cliente debe ser un número positivo.");
            return false;
        }
        return true;
    }
}
