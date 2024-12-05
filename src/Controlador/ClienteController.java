package Controlador;

import Modelo.Cliente;
import dao.ClienteDAO;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private final ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public void agregarCliente(Cliente cliente) throws SQLException {
        clienteDAO.createCliente(cliente);
    }

    public List<Cliente> obtenerClientes() throws SQLException {
        return clienteDAO.getAllClientes();
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        clienteDAO.updateCliente(cliente);
    }

    public void eliminarCliente(int id) throws SQLException {
        clienteDAO.deleteCliente(id);
    }
}

