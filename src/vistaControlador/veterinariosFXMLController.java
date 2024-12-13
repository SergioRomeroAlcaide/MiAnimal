package vistaControlador;

import App.MiAnimal;
import Controlador.VeterinarioController;
import Modelo.Veterinario;
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
import javafx.scene.control.cell.PropertyValueFactory;

public class veterinariosFXMLController {

    // Tabla para mostrar la lista de veterinarios
    @FXML
    private TableView<Veterinario> tablaVeterinarios;

    // Columnas de la tabla para mostrar los datos de cada veterinario
    @FXML
    private TableColumn<Veterinario, Integer> colId;

    @FXML
    private TableColumn<Veterinario, String> colNombre, colEspecialidad, colTelefono, colEmail;

    // Campos de texto para ingresar los datos de un veterinario
    @FXML
    private TextField txtNombre, txtEspecialidad, txtTelefono, txtEmail, txtBuscar;

    // Botones para realizar las distintas acciones
    @FXML
    private Button btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnVolver, btnBuscar; 

    // Controlador para manejar la lógica de negocio de los veterinarios
    private final VeterinarioController veterinarioController;

    /**
     * Constructor de la clase. Se inicializa el controlador de veterinario.
     */
    public veterinariosFXMLController() {
        this.veterinarioController = new VeterinarioController();
    }

    /**
     * Método que se ejecuta al cargar la vista.
     * Se inicializan los botones y se llena la tabla con los veterinarios.
     */
    @FXML
    public void initialize() {
        // Deshabilitar los botones de Actualizar, Eliminar y Buscar inicialmente
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnBuscar.setDisable(true);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        // Llenar la tabla con los veterinarios de la base de datos
        llenarTablaVeterinarios();

        // Detectar la selección de filas en la tabla para habilitar los botones de Actualizar y Eliminar
        tablaVeterinarios.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
     * Llena la tabla de veterinarios con los datos obtenidos de la base de datos.
     */
    private void llenarTablaVeterinarios() {
        ObservableList<Veterinario> veterinarios = FXCollections.observableArrayList(veterinarioController.obtenerTodosLosVeterinarios());
        tablaVeterinarios.setItems(veterinarios);
    }

    /**
     * Realiza la búsqueda de veterinarios con base en el criterio ingresado.
     * @param event Evento de la acción.
     */
    @FXML
    private void buscarVeterinarios(ActionEvent event) {
        String criterio = txtBuscar.getText().trim();
        if (!criterio.isEmpty()) {
            ObservableList<Veterinario> veterinarios = FXCollections.observableArrayList(veterinarioController.buscarVeterinarios(criterio));
            if (veterinarios.isEmpty()) {
                mostrarMensaje("Sin resultados", "No se encontraron veterinarios con ese criterio.", AlertType.INFORMATION);
            }
            tablaVeterinarios.setItems(veterinarios);
        } else {
            mostrarMensaje("Advertencia", "Ingrese un criterio de búsqueda.", AlertType.WARNING);
        }
    }

    /**
     * Inserta un nuevo veterinario con los datos ingresados en los campos de texto.
     * @param event Evento de la acción.
     */
    @FXML
    private void insertar(ActionEvent event) {
        Veterinario veterinario = new Veterinario(0, txtNombre.getText(), txtEspecialidad.getText(), txtTelefono.getText(), txtEmail.getText());
        if (veterinarioController.insertarVeterinario(veterinario)) {
            mostrarMensaje("Éxito", "Veterinario agregado con éxito.", AlertType.INFORMATION);
            llenarTablaVeterinarios();
            limpiarCampos();
        } else {
            mostrarMensaje("Error", "No se pudo agregar el veterinario.", AlertType.ERROR);
        }
    }

    /**
     * Limpia los campos de texto de entrada de datos.
     */
    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtEspecialidad.clear();
        txtTelefono.clear();
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
     * Actualiza los datos de un veterinario seleccionado en la tabla.
     * @param event Evento de la acción.
     */
    @FXML
    private void actualizar(ActionEvent event) {
        Veterinario veterinarioSeleccionado = tablaVeterinarios.getSelectionModel().getSelectedItem();
        veterinarioSeleccionado.setNombre(txtNombre.getText());
        veterinarioSeleccionado.setEspecialidad(txtEspecialidad.getText());
        veterinarioSeleccionado.setTelefono(txtTelefono.getText());
        veterinarioSeleccionado.setEmail(txtEmail.getText());

        if (veterinarioController.actualizarVeterinario(veterinarioSeleccionado)) {
            mostrarMensaje("Éxito", "Veterinario actualizado con éxito.", AlertType.INFORMATION);
            llenarTablaVeterinarios();
        } else {
            mostrarMensaje("Error", "No se pudo actualizar el veterinario.", AlertType.ERROR);
        }
    }

    /**
     * Elimina el veterinario seleccionado en la tabla.
     * @param event Evento de la acción.
     */
    @FXML
    private void eliminar(ActionEvent event) {
        Veterinario veterinarioSeleccionado = tablaVeterinarios.getSelectionModel().getSelectedItem();
        if (veterinarioController.eliminarVeterinario(veterinarioSeleccionado.getId())) {
            mostrarMensaje("Éxito", "Veterinario eliminado con éxito.", AlertType.INFORMATION);
            llenarTablaVeterinarios();
        } else {
            mostrarMensaje("Error", "No se pudo eliminar el veterinario.", AlertType.ERROR);
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
