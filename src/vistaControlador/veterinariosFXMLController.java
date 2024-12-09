/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package vistaControlador;

import Controlador.VeterinarioController;
import Modelo.Veterinario;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;

public class veterinariosFXMLController {

    @FXML
    private TableView<Veterinario> tablaVeterinarios;

    @FXML
    private TextField txtNombre, txtEspecialidad;

    private final VeterinarioController veterinarioController;

    public veterinariosFXMLController() {
        this.veterinarioController = new VeterinarioController();
    }

    @FXML
    private void agregarVeterinario(ActionEvent event) {
        Veterinario veterinario = new Veterinario(0, txtNombre.getText(), txtEspecialidad.getText(), "", "");
        veterinarioController.insertarVeterinario(veterinario);
    }
}

