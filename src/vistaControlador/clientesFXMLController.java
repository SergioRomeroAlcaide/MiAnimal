/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package vistaControlador;

import Controlador.ClienteController;
import Modelo.Cliente;
import javafx.fxml.FXML;
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
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colDireccion;

    @FXML
    private TableColumn<Cliente, String> colEmail;

    @FXML
    private TextField txtNombre, txtTelefono, txtDireccion, txtEmail;

    private final ClienteController clienteController;

    public clientesFXMLController() {
        this.clienteController = new ClienteController();
    }

    @FXML
    private void insertar(ActionEvent event) {
        Cliente cliente = new Cliente(0, txtNombre.getText(), txtTelefono.getText(), txtDireccion.getText(), txtEmail.getText());
        if (clienteController.insertarCliente(cliente)) {
            mostrarMensaje("Éxito", "Cliente agregado con éxito.", AlertType.INFORMATION);
        } else {
            mostrarMensaje("Error", "No se pudo agregar el cliente.", AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String mensaje, AlertType tipoAlerta) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
