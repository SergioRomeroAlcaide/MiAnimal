/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package vistaControlador;

import Controlador.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class loginFXMLController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    private final LoginController loginController;

    public loginFXMLController() {
        this.loginController = new LoginController();
    }

    @FXML
    private void ingresar(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtPassword.getText().trim();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarMensaje("Error", "Por favor, complete todos los campos.", AlertType.ERROR);
            return;
        }

        if (loginController.validarUsuario(usuario, contrasena)) {
            mostrarMensaje("Éxito", "Inicio de sesión exitoso.", AlertType.INFORMATION);
            // Aquí se puede añadir la lógica para cambiar de ventana.
        } else {
            mostrarMensaje("Error", "Credenciales incorrectas.", AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String mensaje, AlertType tipoAlerta) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
