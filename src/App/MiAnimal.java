/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MiAnimal extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        try {
            primaryStage = stage;
            cambiarVista("/Vista/login.fxml", "MiAnimal - Inicio de Sesión");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cambiarVista(String fxmlPath, String titulo) {
    try {
        // Cargar archivo FXML desde la ruta
        FXMLLoader loader = new FXMLLoader(MiAnimal.class.getResource(fxmlPath));
        if (loader.getLocation() == null) {
            throw new IllegalStateException("No se encontró el archivo FXML en la ruta: " + fxmlPath);
        }

        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setTitle(titulo);
        primaryStage.setScene(scene);
        primaryStage.show();
    } catch (IllegalStateException e) {
        System.err.println("Error al cargar la vista: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("Ocurrió un error inesperado al cargar la vista: " + fxmlPath);
        e.printStackTrace();
    }
}


    public static void main(String[] args) {
        launch(args);
    }
}
