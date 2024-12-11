package Controlador;

import Modelo.Cliente;
import dao.ClienteDAO;
import java.util.List;

public class ClienteController {

    private final ClienteDAO clienteDAO;

    /**
     * Constructor de la clase ClienteController
     * Se crea una instancia de ClienteDAO para acceder a la base de datos
     */
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Inserta un cliente en la base de datos
     * @param cliente Cliente con los datos a insertar
     * @return true si se insertó correctamente, false de lo contrario
     */
    public boolean insertarCliente(Cliente cliente) {
        if (validarDatosCliente(cliente)) {
            return clienteDAO.insertar(cliente);
        } else {
            System.err.println("❌ Los datos del cliente no son válidos.");
            return false;
        }
    }

    /**
     * Obtiene todos los clientes de la base de datos
     * @return Lista de clientes
     */
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteDAO.obtenerTodos();
    }

    /**
     * Obtiene un cliente por su ID
     * @param id ID del cliente
     * @return Cliente con el ID especificado, o null si no se encuentra
     */
    public Cliente obtenerClientePorId(int id) {
        return clienteDAO.obtenerPorId(id);
    }

    /**
     * Actualiza los datos de un cliente en la base de datos
     * @param cliente Cliente con los nuevos datos a actualizar
     * @return true si se actualizó correctamente, false de lo contrario
     */
    public boolean actualizarCliente(Cliente cliente) {
        if (validarDatosCliente(cliente)) {
            return clienteDAO.actualizar(cliente);
        } else {
            System.err.println("❌ Los datos del cliente no son válidos.");
            return false;
        }
    }

    /**
     * Elimina un cliente de la base de datos
     * @param id ID del cliente a eliminar
     * @return true si se eliminó correctamente, false de lo contrario
     */
    public boolean eliminarCliente(int id) {
        return clienteDAO.eliminar(id);
    }

    /**
     * Busca clientes por nombre o teléfono
     * @param criterio Texto a buscar (puede ser nombre o teléfono)
     * @return Lista de clientes que coincidan con el criterio
     */
    public List<Cliente> buscarClientes(String criterio) {
        if (criterio != null && !criterio.trim().isEmpty()) {
            return clienteDAO.buscarClientes(criterio);
        } else {
            System.err.println("❌ El criterio de búsqueda está vacío.");
            return List.of();
        }
    }

    /**
     * Valida los datos de un cliente
     * @param cliente Cliente a validar
     * @return true si los datos son válidos, false de lo contrario
     */
    private boolean validarDatosCliente(Cliente cliente) {
        if (cliente == null) {
            System.err.println("❌ El cliente es nulo.");
            return false;
        }
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            System.err.println("❌ El nombre del cliente no puede estar vacío.");
            return false;
        }
        if (cliente.getTelefono() == null || !cliente.getTelefono().matches("\\d{9,12}")) {
            System.err.println("❌ El teléfono debe tener entre 9 y 12 dígitos.");
            return false;
        }
        if (cliente.getDireccion() == null || cliente.getDireccion().trim().isEmpty()) {
            System.err.println("❌ La dirección no puede estar vacía.");
            return false;
        }
        if (cliente.getEmail() == null || !cliente.getEmail().matches("^[\\w-.]+@[\\w-]+\\.[a-z]{2,3}$")) {
            System.err.println("❌ El email no tiene un formato válido.");
            return false;
        }
        return true;
    }
}
