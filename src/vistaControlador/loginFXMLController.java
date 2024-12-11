package vistaControlador;

import App.MiAnimal;
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

    /**
     * Constructor del controlador de la vista de Login
     */
    public loginFXMLController() {
        this.loginController = new LoginController();
    }

    /**
     * Método que se ejecuta al presionar el botón "Iniciar Sesión"
     * @param event Evento de la acción
     */
    @FXML
    private void iniciarSesion(ActionEvent event) {
        // 🚀 Imprimir para verificar que la acción se está ejecutando
        System.out.println("🔹 Método iniciarSesion ejecutado");

        // Obtener los datos de usuario y contraseña
        String usuario = txtUsuario.getText().trim();
        String contrasena = txtPassword.getText().trim();

        // Verificación de campos vacíos
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarMensaje("Error", "Por favor, complete todos los campos.", AlertType.ERROR);
            return;
        }

        try {
            // Validación de usuario con LoginController
            if (loginController.validarUsuario(usuario, contrasena)) {
                System.out.println("✅ Credenciales correctas. Cargando la vista de menú principal...");
                
                // 🔥 Cambio de vista 
                MiAnimal.cambiarVista("/Vista/menuPrincipal.fxml", "Menú Principal");

            } else {
                mostrarMensaje("Error", "Credenciales incorrectas.", AlertType.ERROR);
                System.out.println("❌ Credenciales incorrectas");
            }
        } catch (Exception e) {
            mostrarMensaje("Error", "Ocurrió un error al validar las credenciales.", AlertType.ERROR);
            System.err.println("❌ Error al validar las credenciales.");
            e.printStackTrace();
        }
    }

    /**
     * Método para mostrar mensajes de alerta en la pantalla
     * @param titulo Título del mensaje de alerta
     * @param mensaje Contenido del mensaje
     * @param tipoAlerta Tipo de alerta (ERROR, WARNING, INFORMATION)
     */
    private void mostrarMensaje(String titulo, String mensaje, AlertType tipoAlerta) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    /**
     * Método que se ejecuta al hacer clic en el enlace "¿Olvidaste la contraseña?"
     * @param event Evento de la acción
     */
    @FXML
    private void recuperarContrasena(ActionEvent event) {
        System.out.println("🔹 Se hizo clic en 'Recuperar contraseña'.");
        mostrarMensaje("Recuperar contraseña", "Funcionalidad no implementada aún.", AlertType.INFORMATION);
    }
}
