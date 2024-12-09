package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MiAnimal extends Application {

    // Variable para el Stage principal
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) {
        try {
            // Inicializar la ventana principal
            primaryStage = stage;

            // Cargar la vista de login.fxml
            cambiarVista("/Vista/login.fxml", "MiAnimal - Inicio de Sesión");
        } catch (Exception e) {
            System.err.println("❌ Error general al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método para cambiar la vista principal de la aplicación.
     * 
     * @param fxmlPath Ruta del archivo FXML (relativa a la carpeta resources)
     * @param titulo Título de la ventana
     */
    public static void cambiarVista(String fxmlPath, String titulo) {
        try {
            // Cargar la vista desde la ruta especificada
            FXMLLoader loader = new FXMLLoader(MiAnimal.class.getResource(fxmlPath));
            if (loader == null) {
                throw new RuntimeException("❌ No se encontró el archivo FXML en la ruta: " + fxmlPath);
            }

            Parent root = loader.load();
            
            // Configurar la escena principal
            Scene scene = new Scene(root);
            primaryStage.setTitle(titulo);
            primaryStage.setScene(scene);
            primaryStage.show();

            System.out.println("✅ Vista cargada correctamente: " + fxmlPath);
        } catch (Exception e) {
            System.err.println("❌ Error al cargar la vista: " + fxmlPath);
            e.printStackTrace();
        }
    }

    /**
     * Método principal de la aplicación.
     * 
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        System.out.println("🚀 Iniciando MiAnimal...");
        launch(args);
    }
}
