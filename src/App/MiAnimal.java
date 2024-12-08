/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package App;

import Util.DatabaseSetup;
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
            // Verificar la base de datos antes de iniciar la aplicación
            DatabaseSetup.verificarBaseDeDatos();

            primaryStage = stage;
            cambiarVista("/Vista/login.fxml", "MiAnimal - Inicio de Sesión");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cambiarVista(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(MiAnimal.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle(titulo);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.err.println("Error al cargar la vista: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
