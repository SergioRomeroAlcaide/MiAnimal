package Controlador;

import Modelo.Veterinario;
import dao.VeterinarioDAO;
import java.util.List;

public class VeterinarioController {

    private final VeterinarioDAO veterinarioDAO;

    public VeterinarioController() {
        this.veterinarioDAO = new VeterinarioDAO();
    }

    public boolean insertarVeterinario(Veterinario veterinario) {
        return veterinarioDAO.insertar(veterinario);
    }

    public List<Veterinario> obtenerTodosLosVeterinarios() {
        return veterinarioDAO.obtenerTodos();
    }

    public Veterinario obtenerVeterinarioPorId(int id) {
        return veterinarioDAO.obtenerPorId(id);
    }

    public boolean actualizarVeterinario(Veterinario veterinario) {
        return veterinarioDAO.actualizar(veterinario);
    }

    public boolean eliminarVeterinario(int id) {
        return veterinarioDAO.eliminar(id);
    }
}


