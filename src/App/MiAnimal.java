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
            System.err.println(" Error general al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void cambiarVista(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(MiAnimal.class.getResource(fxmlPath));
            if (loader == null) {
                throw new RuntimeException(" No se encontró el archivo FXML en la ruta: " + fxmlPath);
            }

            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            // Aplicar el archivo CSS a la escena
            scene.getStylesheets().add(MiAnimal.class.getResource("/Util/estilos_Mianimal.css").toExternalForm());
            
            primaryStage.setTitle(titulo);
            primaryStage.setScene(scene);
            primaryStage.show();

            System.out.println(" Vista cargada correctamente: " + fxmlPath);
        } catch (Exception e) {
            System.err.println(" Error al cargar la vista: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(" Iniciando MiAnimal...");
        launch(args);
    }
}
