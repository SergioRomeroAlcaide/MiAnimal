/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package vistaControlador;

import Controlador.RecordatorioController;
import Modelo.Recordatorio;
import Util.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class recordatoriosFXMLController {
    @FXML
    private TableView<Recordatorio> tablaRecordatorios;
    @FXML
    private TableColumn<Recordatorio, String> columnaCliente;
    @FXML
    private TableColumn<Recordatorio, String> columnaMascota;
    @FXML
    private TableColumn<Recordatorio, String> columnaMotivo;
    @FXML
    private TableColumn<Recordatorio, String> columnaFechaHora; // Cambiado a String
    @FXML
    private TableColumn<Recordatorio, Boolean> columnaEnviado;

    private ObservableList<Recordatorio> listaRecordatorios;
    private RecordatorioController recordatorioController;

    @FXML
    public void initialize() {
        try {
            Connection connection = DBConnection.getConnection();
            recordatorioController = new RecordatorioController(connection);
            listaRecordatorios = FXCollections.observableArrayList();

            // Configurar las columnas
            columnaCliente.setCellValueFactory(cellData -> cellData.getValue().clienteNombreProperty());
            columnaMascota.setCellValueFactory(cellData -> cellData.getValue().mascotaNombreProperty());
            columnaMotivo.setCellValueFactory(cellData -> cellData.getValue().motivoProperty());

            // Formatear columna de fecha y hora
            columnaFechaHora.setCellValueFactory(cellData -> {
                LocalDateTime fechaHora = cellData.getValue().getFechaHora();
                String fechaHoraFormateada = fechaHora != null
                        ? fechaHora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                        : "Sin Fecha";
                return new SimpleStringProperty(fechaHoraFormateada); // Devuelve un SimpleStringProperty
            });

            columnaEnviado.setCellValueFactory(cellData -> cellData.getValue().enviadoProperty().asObject());

            cargarRecordatorios();
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudieron cargar los datos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cargarRecordatorios() {
        try {
            listaRecordatorios.setAll(recordatorioController.obtenerRecordatorios());
            tablaRecordatorios.setItems(listaRecordatorios);
        } catch (SQLException e) {
            mostrarAlerta("Error", "Error al cargar los recordatorios: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void enviarRecordatorio(ActionEvent event) {
        Recordatorio recordatorioSeleccionado = tablaRecordatorios.getSelectionModel().getSelectedItem();
        if (recordatorioSeleccionado != null) {
            try {
                recordatorioController.marcarRecordatorioEnviado(recordatorioSeleccionado.getId());
                mostrarAlerta("Éxito", "Recordatorio enviado correctamente.", Alert.AlertType.INFORMATION);
                cargarRecordatorios();
            } catch (SQLException e) {
                mostrarAlerta("Error", "No se pudo enviar el recordatorio: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Advertencia", "Seleccione un recordatorio para enviar.", Alert.AlertType.WARNING);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
