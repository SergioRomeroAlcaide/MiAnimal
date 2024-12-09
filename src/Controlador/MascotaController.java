package Controlador;

import Modelo.Mascota;
import dao.MascotaDAO;
import java.util.List;

public class MascotaController {

    private final MascotaDAO mascotaDAO;

    public MascotaController() {
        this.mascotaDAO = new MascotaDAO();
    }

    public boolean insertarMascota(Mascota mascota) {
        return mascotaDAO.insertar(mascota);
    }

    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaDAO.obtenerTodas();
    }

    public Mascota obtenerMascotaPorId(int id) {
        return mascotaDAO.obtenerPorId(id);
    }

    public boolean actualizarMascota(Mascota mascota) {
        return mascotaDAO.actualizar(mascota);
    }

    public boolean eliminarMascota(int id) {
        return mascotaDAO.eliminar(id);
    }
}

