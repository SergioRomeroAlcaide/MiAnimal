/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package vistaControlador;

import Controlador.MascotaController;
import Modelo.Mascota;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class mascotasFXMLController {

    @FXML
    private TableView<Mascota> tablaMascotas;

    @FXML
    private TableColumn<Mascota, Integer> colId;

    @FXML
    private TableColumn<Mascota, String> colNombre;

    @FXML
    private TableColumn<Mascota, String> colEspecie;

    @FXML
    private TableColumn<Mascota, String> colRaza;

    @FXML
    private TableColumn<Mascota, Integer> colEdad;

    @FXML
    private TextField txtNombre, txtEspecie, txtRaza, txtEdad;

    private final MascotaController mascotaController;

    public mascotasFXMLController() {
        this.mascotaController = new MascotaController();
    }

    @FXML
    private void agregarMascota(ActionEvent event) {
        Mascota mascota = new Mascota(0, txtNombre.getText(), txtEspecie.getText(), txtRaza.getText(), Integer.parseInt(txtEdad.getText()), 0);
        if (mascotaController.insertarMascota(mascota)) {
            mostrarMensaje("Éxito", "Mascota agregada con éxito.", AlertType.INFORMATION);
        } else {
            mostrarMensaje("Error", "No se pudo agregar la mascota.", AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String mensaje, AlertType tipoAlerta) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
