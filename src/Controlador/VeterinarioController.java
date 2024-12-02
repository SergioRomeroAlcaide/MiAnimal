package Controlador;

import Modelo.Veterinario;
import dao.VeterinarioDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VeterinarioController {

    private final VeterinarioDAO veterinarioDAO;

    public VeterinarioController(Connection connection) {
        this.veterinarioDAO = new VeterinarioDAO(connection);
    }

    public void agregarVeterinario(String nombre, String especialidad, String telefono, String email) throws SQLException {
        Veterinario nuevoVeterinario = new Veterinario(nombre, especialidad, telefono, email);
        veterinarioDAO.createVeterinario(nuevoVeterinario);
    }

    public List<Veterinario> obtenerVeterinarios() throws SQLException {
        return veterinarioDAO.readVeterinarios();
    }

    public void actualizarVeterinario(Veterinario veterinario) throws SQLException {
        veterinarioDAO.updateVeterinario(veterinario);
    }

    public void eliminarVeterinario(int id) throws SQLException {
        veterinarioDAO.deleteVeterinario(id);
    }
}
