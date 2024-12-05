package Controlador;

import Modelo.Cita;
import dao.CitaDAO;

import java.sql.SQLException;
import java.util.List;

public class CitaController {
    private final CitaDAO citaDAO;

    public CitaController() {
        this.citaDAO = new CitaDAO();
    }

    public void agregarCita(Cita cita) throws SQLException {
        citaDAO.createCita(cita);
    }

    public List<Cita> obtenerTodasLasCitas() throws SQLException {
        return citaDAO.getAllCitas();
    }

    public void actualizarCita(Cita cita) throws SQLException {
        citaDAO.updateCita(cita);
    }

    public void eliminarCita(int citaId) throws SQLException {
        citaDAO.deleteCita(citaId);
    }
}
