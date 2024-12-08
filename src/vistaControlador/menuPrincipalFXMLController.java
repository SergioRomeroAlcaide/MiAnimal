/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package vistaControlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class menuPrincipalFXMLController {

    @FXML
    private void abrirClientes(ActionEvent event) {
        cargarVista("/Vista/clientes.fxml", "Clientes");
    }

    @FXML
    private void abrirMascotas(ActionEvent event) {
        cargarVista("/Vista/mascotas.fxml", "Mascotas");
    }

    @FXML
    private void abrirCitas(ActionEvent event) {
        cargarVista("/Vista/citas.fxml", "Citas");
    }

    @FXML
    private void abrirVeterinarios(ActionEvent event) {
        cargarVista("/Vista/veterinarios.fxml", "Veterinarios");
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        cargarVista("/Vista/login.fxml", "Iniciar sesión");
    }

    @FXML
    private void salirAplicacion(ActionEvent event) {
        System.exit(0);
    }

    private void cargarVista(String rutaFXML, String titulo) {
        // Aquí se implementa la lógica para cargar la nueva vista (FXML)
    }
}
