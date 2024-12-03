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

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar la vista de login.fxml al iniciar la aplicación
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/login.fxml"));
            Parent root = loader.load();
            
            // Configurar la escena
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("MiAnimal - Login");
            primaryStage.setResizable(false); // Opcional: Bloquear el cambio de tamaño de la ventana
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

