package vistaControlador;

import App.MiAnimal;
import Controlador.CitaController;
import Modelo.Cita;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class citasFXMLController {

    // Tabla para mostrar la lista de citas
    @FXML
    private TableView<Cita> tablaCitas;

    // Columnas de la tabla para mostrar los datos de cada cita
    @FXML
    private TableColumn<Cita, Integer> colId;

    @FXML
    private TableColumn<Cita, String> colFechaHora, colMotivo;

    @FXML
    private TableColumn<Cita, Integer> colMascotaId, colVeterinarioId;

    @FXML
    private TableColumn<Cita, Boolean> colRecordatorioEnviado;

    // Campos de texto para ingresar los datos de una cita
    @FXML
    private TextField txtFechaHora, txtMotivo, txtMascotaId, txtVeterinarioId, txtBuscar;

    // Botones para realizar las distintas acciones
    @FXML
    private Button btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnVolver, btnBuscar; 

    // Controlador para manejar la lógica de negocio de las citas
    private final CitaController citaController;

    /**
     * Constructor de la clase. Se inicializa el controlador de citas.
     */
    public citasFXMLController() {
        this.citaController = new CitaController();
    }

    /**
     * Método que se ejecuta al cargar la vista.
     * Se inicializan los botones y se llena la tabla con las citas.
     */
    @FXML
    public void initialize() {
        // Deshabilitar los botones de Actualizar, Eliminar y Buscar inicialmente
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnBuscar.setDisable(true);

        // Configurar las columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFechaHora.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colMascotaId.setCellValueFactory(new PropertyValueFactory<>("mascota_id"));
        colVeterinarioId.setCellValueFactory(new PropertyValueFactory<>("veterinario_id"));
        colRecordatorioEnviado.setCellValueFactory(new PropertyValueFactory<>("recordatorioEnviado"));

        // Llenar la tabla con las citas de la base de datos
        llenarTablaCitas();

        // Detectar la selección de filas en la tabla para habilitar los botones de Actualizar y Eliminar
        tablaCitas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
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
     * Llena la tabla de citas con los datos obtenidos de la base de datos.
     */
    private void llenarTablaCitas() {
        ObservableList<Cita> citas = FXCollections.observableArrayList(citaController.obtenerTodasLasCitas());
        tablaCitas.setItems(citas);
    }

    /**
     * Realiza la búsqueda de citas con base en el criterio ingresado.
     * @param event Evento de la acción.
     */
    @FXML
    private void buscarCitas(ActionEvent event) {
        String criterio = txtBuscar.getText().trim();
        if (!criterio.isEmpty()) {
            ObservableList<Cita> citas = FXCollections.observableArrayList(citaController.buscarCitas(criterio));
            if (citas.isEmpty()) {
                mostrarMensaje("Sin resultados", "No se encontraron citas con ese criterio.", AlertType.INFORMATION);
            }
            tablaCitas.setItems(citas);
        } else {
            mostrarMensaje("Advertencia", "Ingrese un criterio de búsqueda.", AlertType.WARNING);
        }
    }

    /**
     * Inserta una nueva cita con los datos ingresados en los campos de texto.
     * @param event Evento de la acción.
     */
    @FXML
    private void insertar(ActionEvent event) {
        try {
            int mascotaId = Integer.parseInt(txtMascotaId.getText());
            int veterinarioId = Integer.parseInt(txtVeterinarioId.getText());
            Cita cita = new Cita(0, txtFechaHora.getText(), txtMotivo.getText(), mascotaId, veterinarioId, false);
            if (citaController.insertarCita(cita)) {
                mostrarMensaje("Éxito", "Cita agregada con éxito.", AlertType.INFORMATION);
                llenarTablaCitas();
                limpiarCampos();
            } else {
                mostrarMensaje("Error", "No se pudo agregar la cita.", AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Los campos de Mascota ID y Veterinario ID deben ser numéricos.", AlertType.ERROR);
        }
    }

    /**
     * Limpia los campos de texto de entrada de datos.
     */
    @FXML
    private void limpiarCampos() {
        txtFechaHora.clear();
        txtMotivo.clear();
        txtMascotaId.clear();
        txtVeterinarioId.clear();
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
     * Actualiza los datos de una cita seleccionada en la tabla.
     * @param event Evento de la acción.
     */
    @FXML
    private void actualizar(ActionEvent event) {
        try {
            Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
            int mascotaId = Integer.parseInt(txtMascotaId.getText());
            int veterinarioId = Integer.parseInt(txtVeterinarioId.getText());

            citaSeleccionada.setFechaHora(txtFechaHora.getText());
            citaSeleccionada.setMotivo(txtMotivo.getText());
            citaSeleccionada.setMascota_id(mascotaId);
            citaSeleccionada.setVeterinario_id(veterinarioId);

            if (citaController.actualizarCita(citaSeleccionada)) {
                mostrarMensaje("Éxito", "Cita actualizada con éxito.", AlertType.INFORMATION);
                llenarTablaCitas();
            } else {
                mostrarMensaje("Error", "No se pudo actualizar la cita.", AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Los campos de Mascota ID y Veterinario ID deben ser numéricos.", AlertType.ERROR);
        }
    }

    /**
     * Elimina la cita seleccionada en la tabla.
     * @param event Evento de la acción.
     */
    @FXML
    private void eliminar(ActionEvent event) {
        Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaController.eliminarCita(citaSeleccionada.getId())) {
            mostrarMensaje("Éxito", "Cita eliminada con éxito.", AlertType.INFORMATION);
            llenarTablaCitas();
        } else {
            mostrarMensaje("Error", "No se pudo eliminar la cita.", AlertType.ERROR);
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
