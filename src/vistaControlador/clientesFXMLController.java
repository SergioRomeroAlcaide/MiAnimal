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

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, Integer> colId;

    @FXML
    private TableColumn<Cliente, String> colNombre, colTelefono, colDireccion, colEmail;

    @FXML
    private TextField txtNombre, txtTelefono, txtDireccion, txtEmail, txtBuscar;

    @FXML
    private Button btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnVolver, btnBuscar;

    private final ClienteController clienteController;

    public clientesFXMLController() {
        this.clienteController = new ClienteController();
    }

    @FXML
    public void initialize() {
        // Deshabilitar los botones de Actualizar y Eliminar inicialmente
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);

        // Deshabilitar el botón de buscar inicialmente
        btnBuscar.setDisable(true);

        // Llenar la tabla con todos los clientes al cargar la vista
        llenarTablaClientes();

        // Detectar la selección de filas en la tabla
        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean seleccion = newSelection != null;
            btnActualizar.setDisable(!seleccion);
            btnEliminar.setDisable(!seleccion);
        });

        // Detectar si hay texto en el campo de búsqueda
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            btnBuscar.setDisable(newValue.trim().isEmpty());
        });
    }

    private void llenarTablaClientes() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteController.obtenerTodosLosClientes());
        tablaClientes.setItems(clientes);
    }

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

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtEmail.clear();
        txtBuscar.clear();
    }

    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        try {
            MiAnimal.cambiarVista("/Vista/menuPrincipal.fxml", "Menú Principal");
        } catch (Exception e) {
            mostrarMensaje("Error", "No se pudo volver al menú principal.", AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarMensaje(String titulo, String mensaje, AlertType tipoAlerta) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
