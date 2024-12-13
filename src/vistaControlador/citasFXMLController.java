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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.scene.control.CheckBox;

public class citasFXMLController {

    // Elementos de la tabla
    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, Integer> colId;

    @FXML
    private TableColumn<Cita, LocalDateTime> colFechaHora;

    @FXML
    private TableColumn<Cita, String> colMotivo;

    @FXML
    private TableColumn<Cita, Integer> colMascotaId;

    @FXML
    private TableColumn<Cita, Integer> colVeterinarioId;

    @FXML
    private TableColumn<Cita, Boolean> colRecordatorioEnviado;

    // Campos de entrada
    @FXML
    private DatePicker datePickerFecha;

    @FXML
    private ComboBox<String> comboHora;

    @FXML
    private TextField txtMotivo, txtMascotaId, txtVeterinarioId, txtBuscar;
    
    @FXML 
    private CheckBox checkRecordatorioEnviado;

    // Botones de acción
    @FXML
    private Button btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnVolver, btnBuscar;

    private final CitaController citaController;

    /**
     * Constructor de la clase. Se inicializa el controlador de citas.
     */
    public citasFXMLController() {
        this.citaController = new CitaController();
    }

    /**
     * Método que se ejecuta al cargar la vista.
     * Se inicializan los botones, el ComboBox de horas y se llena la tabla con las citas.
     */
    @FXML
    public void initialize() {
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnBuscar.setDisable(true);
        
        comboHora.getItems().addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00");
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFechaHora.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colMascotaId.setCellValueFactory(new PropertyValueFactory<>("mascotaId"));
        colVeterinarioId.setCellValueFactory(new PropertyValueFactory<>("veterinarioId"));
        colRecordatorioEnviado.setCellValueFactory(new PropertyValueFactory<>("recordatorioEnviado"));

        llenarTablaCitas();

        tablaCitas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean seleccion = newSelection != null;
            btnActualizar.setDisable(!seleccion);
            btnEliminar.setDisable(!seleccion);
        });

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
     * 
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
        }
    }

    /**
     * Inserta una nueva cita con los datos ingresados en los campos de texto.
     * 
     * @param event Evento de la acción.
     */
    @FXML
    private void insertar(ActionEvent event) {
        try {
            LocalDate fecha = datePickerFecha.getValue();
            LocalTime hora = LocalTime.parse(comboHora.getValue());
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
            Cita cita = new Cita(0, fechaHora, txtMotivo.getText(), 
                                Integer.parseInt(txtMascotaId.getText()), 
                                Integer.parseInt(txtVeterinarioId.getText()), 
                                checkRecordatorioEnviado.isSelected());
            if (citaController.insertarCita(cita)) {
                mostrarMensaje("Éxito", "Cita agregada con éxito.", AlertType.INFORMATION);
                llenarTablaCitas();
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Los campos de ID de mascota y veterinario deben ser números.", AlertType.ERROR);
        }
    }

    /**
     * Limpia los campos de texto de entrada de datos.
     */
    @FXML
    private void limpiarCampos() {
        datePickerFecha.setValue(null);
        comboHora.setValue(null);
        txtMotivo.clear();
        txtMascotaId.clear();
        txtVeterinarioId.clear();
        txtBuscar.clear();
        
        // Llenar la tabla con todos los registros de la base de datos
        llenarTablaCitas();
    }

    /**
     * Actualiza los datos de una cita seleccionada en la tabla.
     * 
     * @param event Evento de la acción.
     */
    @FXML
    private void actualizar(ActionEvent event) {
        try {
            Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
            LocalDate fecha = datePickerFecha.getValue();
            LocalTime hora = LocalTime.parse(comboHora.getValue());
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            citaSeleccionada.setFechaHora(fechaHora);
            citaSeleccionada.setMotivo(txtMotivo.getText());
            citaSeleccionada.setMascotaId(Integer.parseInt(txtMascotaId.getText()));
            citaSeleccionada.setVeterinarioId(Integer.parseInt(txtVeterinarioId.getText()));

            if (citaController.actualizarCita(citaSeleccionada)) {
                mostrarMensaje("Éxito", "Cita actualizada con éxito.", AlertType.INFORMATION);
                llenarTablaCitas();
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Los campos de ID de mascota y veterinario deben ser números.", AlertType.ERROR);
        }
    }

    /**
     * Elimina la cita seleccionada en la tabla.
     * 
     * @param event Evento de la acción.
     */
    @FXML
    private void eliminar(ActionEvent event) {
        Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada != null && citaController.eliminarCita(citaSeleccionada.getId())) {
            mostrarMensaje("Éxito", "Cita eliminada con éxito.", AlertType.INFORMATION);
            llenarTablaCitas();
        }
    }

    /**
     * Vuelve a la vista principal del menú.
     * 
     * @param event Evento de la acción.
     */
    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        try {
            MiAnimal.cambiarVista("/Vista/menuPrincipal.fxml", "Menú Principal");
        } catch (Exception e) {
            mostrarMensaje("Error", "No se pudo volver al menú principal.", AlertType.ERROR);
        }
    }

    /**
     * Muestra un mensaje de alerta en la pantalla.
     * 
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
}
