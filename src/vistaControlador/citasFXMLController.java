/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package vistaControlador;

import Controlador.CitaController;
import Modelo.Cita;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class citasFXMLController {

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, Integer> colId;

    @FXML
    private TableColumn<Cita, String> colFechaHora;

    @FXML
    private TableColumn<Cita, String> colMotivo;

    @FXML
    private TextField txtFechaHora, txtMotivo;

    private final CitaController citaController;

    public citasFXMLController() {
        this.citaController = new CitaController();
    }

    @FXML
    private void agregarCita(ActionEvent event) {
        Cita cita = new Cita(0, txtFechaHora.getText(), txtMotivo.getText(), 0, 0, false);
        if (citaController.insertarCita(cita)) {
            mostrarMensaje("Éxito", "Cita agregada con éxito.", AlertType.INFORMATION);
        } else {
            mostrarMensaje("Error", "No se pudo agregar la cita.", AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String mensaje, AlertType tipoAlerta) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
