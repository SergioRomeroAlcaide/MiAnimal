package Controlador;

import Modelo.Mascota;
import dao.MascotaDAO;

import java.sql.SQLException;
import java.util.List;

public class MascotaController {
    private final MascotaDAO mascotaDAO;

    public MascotaController() {
        this.mascotaDAO = new MascotaDAO();
    }

    public void agregarMascota(Mascota mascota) throws SQLException {
        mascotaDAO.createMascota(mascota);
    }

    public List<Mascota> obtenerMascotas() throws SQLException {
        return mascotaDAO.getAllMascotas();
    }

    public void actualizarMascota(Mascota mascota) throws SQLException {
        mascotaDAO.updateMascota(mascota);
    }

    public void eliminarMascota(int id) throws SQLException {
        mascotaDAO.deleteMascota(id);
    }
}
