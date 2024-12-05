package Controlador;

import Modelo.Veterinario;
import dao.VeterinarioDAO;

import java.sql.SQLException;
import java.util.List;

public class VeterinarioController {
    private final VeterinarioDAO veterinarioDAO;

    public VeterinarioController() {
        this.veterinarioDAO = new VeterinarioDAO();
    }

    public void agregarVeterinario(Veterinario veterinario) throws SQLException {
        veterinarioDAO.createVeterinario(veterinario);
    }

    public List<Veterinario> obtenerVeterinarios() throws SQLException {
        return veterinarioDAO.getAllVeterinarios();
    }

    public void actualizarVeterinario(Veterinario veterinario) throws SQLException {
        veterinarioDAO.updateVeterinario(veterinario);
    }

    public void eliminarVeterinario(int id) throws SQLException {
        veterinarioDAO.deleteVeterinario(id);
    }
}

