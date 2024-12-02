package Controlador;

import Modelo.Mascota;
import dao.MascotaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MascotaController {
    private MascotaDAO mascotaDAO;

    public MascotaController(Connection connection) {
        this.mascotaDAO = new MascotaDAO(connection);
    }

    /**
     * Agregar una nueva mascota.
     */
    public void agregarMascota(String nombre, String especie, String raza, int edad, int clienteId) throws SQLException {
        Mascota mascota = new Mascota(nombre, especie, raza, edad, clienteId);
        mascotaDAO.createMascota(mascota);
    }

    /**
     * Actualizar una mascota existente.
     */
    public void actualizarMascota(Mascota mascota) throws SQLException {
        mascotaDAO.updateMascota(mascota);
    }

    /**
     * Eliminar una mascota por su ID.
     */
    public void eliminarMascota(int id) throws SQLException {
        mascotaDAO.deleteMascota(id);
    }

    /**
     * Obtener todas las mascotas de la base de datos.
     */
    public List<Mascota> obtenerMascotas() throws SQLException {
        return mascotaDAO.getAllMascotas();
    }
}
