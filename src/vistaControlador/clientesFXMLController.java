package vistaControlador;

import App.MiAnimal;
import Controlador.ClienteController;
import Modelo.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class clientesFXMLController {

    // Tabla para mostrar la lista de clientes
    @FXML
    private TableView<Cliente> tablaClientes;

    // Columnas de la tabla para mostrar los datos de cada cliente
    @FXML
    private TableColumn<Cliente, Integer> colId;

    @FXML
    private TableColumn<Cliente, String> colNombre, colTelefono, colDireccion, colEmail;

    // Campos de texto para ingresar los datos de un cliente
    @FXML
    private TextField txtNombre, txtTelefono, txtDireccion, txtEmail, txtBuscar;

    // Botones para realizar las distintas acciones
    @FXML
    private Button btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnVolver, btnBuscar; 

    // Controlador para manejar la lógica de negocio de los clientes
    private final ClienteController clienteController;

    /**
     * Constructor de la clase. Se inicializa el controlador de cliente.
     */
    public clientesFXMLController() {
        this.clienteController = new ClienteController();
    }

    /**
     * Método que se ejecuta al cargar la vista.
     * Se inicializan los botones y se llena la tabla con los clientes.
     */
    @FXML
    public void initialize() {
        // Deshabilitar los botones de Actualizar, Eliminar y Buscar inicialmente
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnBuscar.setDisable(true);

        // Llenar la tabla con los clientes de la base de datos
        llenarTablaClientes();

        // Detectar la selección de filas en la tabla para habilitar los botones de Actualizar y Eliminar
        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean seleccion = newSelection != null;
            btnActualizar.setDisable(!seleccion);
            btnEliminar.setDisable(!seleccion);
        });

        // Detectar si hay texto en el campo de búsqueda para habilitar el botón de Buscar
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            btnBuscar.setDisable(newValue.trim().isEmpty());
        });
    }

    /**
     * Llena la tabla de clientes con los datos obtenidos de la base de datos.
     */
    private void llenarTablaClientes() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteController.obtenerTodosLosClientes());
        tablaClientes.setItems(clientes);
    }

    /**
     * Realiza la búsqueda de clientes con base en el criterio ingresado.
     * @param event Evento de la acción.
     */
    @FXML
    private void buscarClientes(ActionEvent event) {
        String criterio = txtBuscar.getText().trim();
        if (!criterio.isEmpty()) {
            ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteController.buscarClientes(criterio));
            if (clientes.isEmpty()) {
                mostrarMensaje("Sin resultados", "No se encontraron clientes con ese criterio.", AlertType.INFORMATION);
            }
            tablaClientes.setItems(clientes);
        } else {
            mostrarMensaje("Advertencia", "Ingrese un criterio de búsqueda.", AlertType.WARNING);
        }
    }

    /**
     * Inserta un nuevo cliente con los datos ingresados en los campos de texto.
     * @param event Evento de la acción.
     */
    @FXML
    private void insertar(ActionEvent event) {
        Cliente cliente = new Cliente(0, txtNombre.getText(), txtTelefono.getText(), txtDireccion.getText(), txtEmail.getText());
        if (clienteController.insertarCliente(cliente)) {
            mostrarMensaje("Éxito", "Cliente agregado con éxito.", AlertType.INFORMATION);
            llenarTablaClientes();
            limpiarCampos();
        } else {
            mostrarMensaje("Error", "No se pudo agregar el cliente.", AlertType.ERROR);
        }
    }

    /**
     * Limpia los campos de texto de entrada de datos.
     */
    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtEmail.clear();
        txtBuscar.clear();
    }

    /**
     * Muestra un mensaje de alerta en la pantalla.
     * @param titulo El título de la ventana de la alerta.
     * @param mensaje El mensaje a mostrar.
     * @param tipoAlerta El tipo de alerta (ERROR, INFORMATION, WARNING, etc.).
     */
    private void mostrarMensaje(String titulo, String mensaje, AlertType tipoAlerta) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Actualiza los datos de un cliente seleccionado en la tabla.
     * @param event Evento de la acción.
     */
    @FXML
    private void actualizar(ActionEvent event) {
        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        clienteSeleccionado.setNombre(txtNombre.getText());
        clienteSeleccionado.setTelefono(txtTelefono.getText());
        clienteSeleccionado.setDireccion(txtDireccion.getText());
        clienteSeleccionado.setEmail(txtEmail.getText());

        if (clienteController.actualizarCliente(clienteSeleccionado)) {
            mostrarMensaje("Éxito", "Cliente actualizado con éxito.", AlertType.INFORMATION);
            llenarTablaClientes();
        } else {
            mostrarMensaje("Error", "No se pudo actualizar el cliente.", AlertType.ERROR);
        }
    }

    /**
     * Elimina el cliente seleccionado en la tabla.
     * @param event Evento de la acción.
     */
    @FXML
    private void eliminar(ActionEvent event) {
        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (clienteController.eliminarCliente(clienteSeleccionado.getId())) {
            mostrarMensaje("Éxito", "Cliente eliminado con éxito.", AlertType.INFORMATION);
            llenarTablaClientes();
        } else {
            mostrarMensaje("Error", "No se pudo eliminar el cliente.", AlertType.ERROR);
        }
    }

    /**
     * Vuelve a la vista principal del menú.
     * @param event Evento de la acción.
     */
    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        try {
            MiAnimal.cambiarVista("/Vista/menuPrincipal.fxml", "Menú Principal");
        } catch (Exception e) {
            mostrarMensaje("Error", "No se pudo volver al menú principal.", AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
