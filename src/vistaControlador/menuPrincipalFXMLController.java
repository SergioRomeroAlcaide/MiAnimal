
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
        cargarVista("/Vista/clientes.fxml", "Clientes");
    }

    /**
     * Abre la vista de Mascotas
     * @param event Evento de la acción
     */
    @FXML
    private void abrirMascotas(ActionEvent event) {
        cargarVista("/Vista/mascotas.fxml", "Mascotas");
    }

    /**
     * Abre la vista de Citas
     * @param event Evento de la acción
     */
    @FXML
    private void abrirCitas(ActionEvent event) {
        cargarVista("/Vista/citas.fxml", "Citas");
    }

    /**
     * Abre la vista de Veterinarios
     * @param event Evento de la acción
     */
    @FXML
    private void abrirVeterinarios(ActionEvent event) {
        cargarVista("/Vista/veterinarios.fxml", "Veterinarios");
    }

    /**
     * Cierra la sesión y regresa a la vista de inicio de sesión
     * @param event Evento de la acción
     */
    @FXML
    private void cerrarSesion(ActionEvent event) {
        cargarVista("/Vista/login.fxml", "Iniciar sesión");
    }

    /**
     * Cierra la aplicación
     * @param event Evento de la acción
     */
    @FXML
    private void salirAplicacion(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Método para cargar una nueva vista en la ventana principal
     * @param rutaFXML Ruta de la vista FXML
     * @param titulo Título de la ventana
     */
    private void cargarVista(String rutaFXML, String titulo) {
        try {
            MiAnimal.cambiarVista(rutaFXML, titulo);
        } catch (Exception e) {
            System.err.println("❌ Error al cargar la vista: " + rutaFXML);
            e.printStackTrace();
        }
    }
}
