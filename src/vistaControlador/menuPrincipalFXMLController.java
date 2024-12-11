package vistaControlador;

import App.MiAnimal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class menuPrincipalFXMLController {

    /**
     * Abre la vista de Clientes
     * @param event Evento de la acción
     */
    @FXML
    private void abrirClientes(ActionEvent event) {
        MiAnimal.cambiarVista("/Vista/clientes.fxml", "Clientes");
    }

    /**
     * Abre la vista de Mascotas
     * @param event Evento de la acción
     */
    @FXML
    private void abrirMascotas(ActionEvent event) {
        MiAnimal.cambiarVista("/Vista/mascotas.fxml", "Mascotas");
    }

    /**
     * Abre la vista de Citas
     * @param event Evento de la acción
     */
    @FXML
    private void abrirCitas(ActionEvent event) {
        MiAnimal.cambiarVista("/Vista/citas.fxml", "Citas");
    }

    /**
     * Abre la vista de Veterinarios
     * @param event Evento de la acción
     */
    @FXML
    private void abrirVeterinarios(ActionEvent event) {
        MiAnimal.cambiarVista("/Vista/veterinarios.fxml", "Veterinarios");
    }

    /**
     * Cierra la sesión y regresa a la vista de inicio de sesión
     * @param event Evento de la acción
     */
    @FXML
    private void cerrarSesion(ActionEvent event) {
        System.out.println("🔹 Cerrando sesión y volviendo a la pantalla de inicio de sesión.");
        MiAnimal.cambiarVista("/Vista/login.fxml", "Iniciar sesión");
    }

    /**
     * Cierra la aplicación
     * @param event Evento de la acción
     */
    @FXML
    private void salirAplicacion(ActionEvent event) {
        System.out.println("🔹 Cerrando la aplicación.");
        System.exit(0);
    }
}
