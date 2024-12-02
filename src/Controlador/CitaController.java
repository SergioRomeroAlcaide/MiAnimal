package Controlador;

import Modelo.Cita;
import dao.CitaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class CitaController {

    private final CitaDAO citaDAO;

    public CitaController(Connection connection) {
        this.citaDAO = new CitaDAO(connection);
    }

    // Agregar una nueva cita
    public void agregarCita(LocalDateTime fechaHora, String motivo, int mascotaId, int veterinarioId) throws SQLException {
        Cita nuevaCita = new Cita(fechaHora, motivo, mascotaId, veterinarioId);
        citaDAO.createCita(nuevaCita);
    }

    // Obtener todas las citas
    public List<Cita> obtenerCitas() throws SQLException {
        return citaDAO.readCitas();
    }

    // Actualizar una cita existente
    public void actualizarCita(Cita cita) throws SQLException {
        citaDAO.updateCita(cita);
    }

    // Eliminar una cita por ID
    public void eliminarCita(int id) throws SQLException {
        citaDAO.deleteCita(id);
    }
}

