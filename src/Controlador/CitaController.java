package Controlador;

import Modelo.Cita;
import dao.CitaDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CitaController {
    private final CitaDAO citaDAO;

    public CitaController(Connection connection) {
        this.citaDAO = new CitaDAO(connection);
    }

    public void agregarCita(Cita cita) throws SQLException {
        citaDAO.createCita(cita);
    }

    public List<Cita> obtenerTodasLasCitas() throws SQLException {
        return citaDAO.readAllCitas();
    }

    public List<Cita> obtenerProximasCitas() throws SQLException {
        return citaDAO.readProximasCitas();
    }

    public void actualizarCita(Cita cita) throws SQLException {
        citaDAO.updateCita(cita);
    }

    public void eliminarCita(int citaId) throws SQLException {
        citaDAO.deleteCita(citaId);
    }

    public void marcarRecordatorioEnviado(int citaId) throws SQLException {
        citaDAO.marcarRecordatorioEnviado(citaId);
    }
}

