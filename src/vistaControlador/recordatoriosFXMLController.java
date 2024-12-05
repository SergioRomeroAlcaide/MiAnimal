/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package vistaControlador;

import Modelo.Recordatorio;
import Controlador.RecordatorioController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class recordatoriosFXMLController {

    @FXML
    private TableView<Recordatorio> tablaRecordatorios;

    @FXML
    private TableColumn<Recordatorio, Integer> colId;

    @FXML
    private TableColumn<Recordatorio, String> colClienteNombre;

    @FXML
    private TableColumn<Recordatorio, String> colMascotaNombre;

    @FXML
    private TableColumn<Recordatorio, String> colMotivo;

    @FXML
    private TableColumn<Recordatorio, LocalDateTime> colFechaHora;

    @FXML
    private TableColumn<Recordatorio, Boolean> colEnviado;

    @FXML
    private TextField txtClienteNombre;

    @FXML
    private TextField txtMascotaNombre;

    @FXML
    private TextField txtMotivo;

    @FXML
    private TextField txtFechaHora; // Formato: "yyyy-MM-dd HH:mm"

    @FXML
    private CheckBox chkEnviado;

    @FXML
    private Button btnAgregar;

    private final RecordatorioController recordatorioController = new RecordatorioController();
    private final ObservableList<Recordatorio> recordatoriosList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarTabla();
        cargarRecordatorios();
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClienteNombre.setCellValueFactory(new PropertyValueFactory<>("clienteNombre"));
        colMascotaNombre.setCellValueFactory(new PropertyValueFactory<>("mascotaNombre"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colFechaHora.setCellValueFactory(new PropertyValueFactory<>("fechaHora"));
        colEnviado.setCellValueFactory(new PropertyValueFactory<>("enviado"));
    }

    private void cargarRecordatorios() {
        try {
            List<Recordatorio> recordatorios = recordatorioController.obtenerRecordatorios();
            recordatoriosList.setAll(recordatorios);
            tablaRecordatorios.setItems(recordatoriosList);
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al cargar los recordatorios: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void agregarRecordatorio() {
        try {
            String clienteNombre = txtClienteNombre.getText();
            String mascotaNombre = txtMascotaNombre.getText();
            String motivo = txtMotivo.getText();
            String fechaHoraStr = txtFechaHora.getText();
            boolean enviado = chkEnviado.isSelected();

            // Convertir la fecha/hora de texto a LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);

            // Crear el nuevo recordatorio
            Recordatorio nuevoRecordatorio = new Recordatorio(0, clienteNombre, mascotaNombre, motivo, fechaHora, enviado);
            recordatorioController.agregarRecordatorio(nuevoRecordatorio);
            cargarRecordatorios();
            limpiarCampos();
            mostrarAlerta("Éxito", "Recordatorio agregado correctamente", Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al agregar el recordatorio: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtClienteNombre.clear();
        txtMascotaNombre.clear();
        txtMotivo.clear();
        txtFechaHora.clear();
        chkEnviado.setSelected(false);
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}
