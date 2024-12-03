/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package vistaControlador;

import Modelo.Cita;
import Controlador.CitaController;
import Util.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class recordatoriosFXMLController {

    @FXML private TableView<Cita> tablaRecordatorios;
    @FXML private TableColumn<Cita, String> columnaCliente;
    @FXML private TableColumn<Cita, String> columnaMascota;
    @FXML private TableColumn<Cita, String> columnaFechaHora;
    @FXML private TableColumn<Cita, String> columnaMotivo;

    private ObservableList<Cita> listaRecordatorios;
    private CitaController citaController;

    @FXML
public void initialize() {
    try {
        Connection connection = DBConnection.getConnection();
        citaController = new CitaController(connection);
        listaRecordatorios = FXCollections.observableArrayList();

        // Configurar columnas de la tabla
        columnaCliente.setCellValueFactory(cellData -> cellData.getValue().clienteNombreProperty());
        columnaMascota.setCellValueFactory(cellData -> cellData.getValue().mascotaNombreProperty());
        columnaFechaHora.setCellValueFactory(cellData -> cellData.getValue().fechaHoraProperty().asString()); // Mostrar como String
        columnaMotivo.setCellValueFactory(cellData -> cellData.getValue().motivoProperty());

        cargarRecordatorios();
    } catch (SQLException e) {
        mostrarAlerta("Error", "Error al conectar con la base de datos: " + e.getMessage(), Alert.AlertType.ERROR);
    }
}


    @FXML
    private void cargarRecordatorios() {
        try {
            listaRecordatorios.setAll(citaController.obtenerProximasCitas());
            tablaRecordatorios.setItems(listaRecordatorios);
        } catch (SQLException e) {
            mostrarAlerta("Error", "Error al cargar los recordatorios: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
private void enviarRecordatorio(ActionEvent event) {
    Cita citaSeleccionada = tablaRecordatorios.getSelectionModel().getSelectedItem();
    if (citaSeleccionada != null) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION, "¿Desea enviar un recordatorio para esta cita?");
        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        if (respuesta.isPresent() && respuesta.get() == ButtonType.OK) {
            try {
                // Lógica para enviar recordatorio (correo o teléfono)
                System.out.println("Enviando recordatorio a: " + citaSeleccionada.getClienteEmail());

                // Actualizar estado en la base de datos
                citaController.marcarRecordatorioEnviado(citaSeleccionada.getId());

                mostrarAlerta("Éxito", "Recordatorio enviado correctamente.", Alert.AlertType.INFORMATION);
                cargarRecordatorios(); // Refresca la tabla
            } catch (SQLException e) {
                mostrarAlerta("Error", "Error al enviar recordatorio: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    } else {
        mostrarAlerta("Advertencia", "Seleccione una cita para enviar un recordatorio.", Alert.AlertType.WARNING);
    }
}


    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/menu_principal.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) tablaRecordatorios.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            mostrarAlerta("Error", "Error al volver al menú principal: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}


