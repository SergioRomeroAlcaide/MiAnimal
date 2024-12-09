package Controlador;

import Modelo.Cita;
import dao.CitaDAO;
import java.util.List;

public class CitaController {

    private final CitaDAO citaDAO;

    public CitaController() {
        this.citaDAO = new CitaDAO();
    }

    public boolean insertarCita(Cita cita) {
        return citaDAO.insertar(cita);
    }

    public List<Cita> obtenerTodasLasCitas() {
        return citaDAO.obtenerTodas();
    }

    public Cita obtenerCitaPorId(int id) {
        return citaDAO.obtenerPorId(id);
    }

    public boolean actualizarCita(Cita cita) {
        return citaDAO.actualizar(cita);
    }

    public boolean eliminarCita(int id) {
        return citaDAO.eliminar(id);
    }
}
