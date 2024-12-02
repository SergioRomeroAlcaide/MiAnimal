package Controlador;

import Modelo.Cliente;
import dao.ClienteDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController(Connection connection) {
        this.clienteDAO = new ClienteDAO(connection);
    }

    public void agregarCliente(String nombre, String telefono, String direccion, String email) throws SQLException {
        Cliente cliente = new Cliente(nombre, telefono, direccion, email);
        clienteDAO.createCliente(cliente);
    }

    public void actualizarCliente(Cliente cliente) throws SQLException {
        clienteDAO.updateCliente(cliente);
    }

    public void eliminarCliente(int id) throws SQLException {
        clienteDAO.deleteCliente(id);
    }

    public List<Cliente> obtenerClientes() throws SQLException {
        return clienteDAO.getAllClientes();
    }
}
