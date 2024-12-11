package vistaControlador;

import App.MiAnimal;
import Controlador.MascotaController;
import Modelo.Mascota;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class mascotasFXMLController {

    // Tabla para mostrar la lista de mascotas
    @FXML
    private TableView<Mascota> tablaMascotas;

    // Columnas de la tabla para mostrar los datos de cada mascota
    @FXML
    private TableColumn<Mascota, Integer> colId, colEdad, colClienteId;

    @FXML
    private TableColumn<Mascota, String> colNombre, colEspecie, colRaza;

    // Campos de texto para ingresar los datos de una mascota
    @FXML
    private TextField txtNombre, txtEspecie, txtRaza, txtEdad, txtClienteId, txtBuscar;

    // Botones para realizar las distintas acciones
    @FXML
    private Button btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnVolver, btnBuscar; 

    // Controlador para manejar la lógica de negocio de las mascotas
    private final MascotaController mascotaController;

    /**
     * Constructor de la clase. Se inicializa el controlador de mascota.
     */
    public mascotasFXMLController() {
        this.mascotaController = new MascotaController();
    }

    /**
     * Método que se ejecuta al cargar la vista.
     * Se inicializan los botones y se llena la tabla con las mascotas.
     */
    @FXML
    public void initialize() {
        // Deshabilitar los botones de Actualizar, Eliminar y Buscar inicialmente
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        btnBuscar.setDisable(true);

        // Configurar las columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colClienteId.setCellValueFactory(new PropertyValueFactory<>("cliente_id"));

        // Llenar la tabla con las mascotas de la base de datos
        llenarTablaMascotas();

        // Detectar la selección de filas en la tabla para habilitar los botones de Actualizar y Eliminar
        tablaMascotas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean seleccion = newSelection != null;
            btnActualizar.setDisable(!seleccion);
            btnEliminar.setDisable(!seleccion);
        });

        // Detectar si hay texto en el campo de búsqueda para habilitar el botón de Buscar
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            btnBuscar.setDisable(newValue.trim().isEmpty());
        });
    }

    /**
     * Llena la tabla de mascotas con los datos obtenidos de la base de datos.
     */
    private void llenarTablaMascotas() {
        ObservableList<Mascota> mascotas = FXCollections.observableArrayList(mascotaController.obtenerTodasLasMascotas());
        tablaMascotas.setItems(mascotas);
    }

    /**
     * Realiza la búsqueda de mascotas con base en el criterio ingresado.
     * @param event Evento de la acción.
     */
    @FXML
    private void buscarMascotas(ActionEvent event) {
        String criterio = txtBuscar.getText().trim();
        if (!criterio.isEmpty()) {
            ObservableList<Mascota> mascotas = FXCollections.observableArrayList(mascotaController.buscarMascotas(criterio));
            if (mascotas.isEmpty()) {
                mostrarMensaje("Sin resultados", "No se encontraron mascotas con ese criterio.", AlertType.INFORMATION);
            }
            tablaMascotas.setItems(mascotas);
        } else {
            mostrarMensaje("Advertencia", "Ingrese un criterio de búsqueda.", AlertType.WARNING);
        }
    }

    /**
     * Inserta una nueva mascota con los datos ingresados en los campos de texto.
     * @param event Evento de la acción.
     */
    @FXML
    private void insertar(ActionEvent event) {
        try {
            int edad = Integer.parseInt(txtEdad.getText());
            int clienteId = Integer.parseInt(txtClienteId.getText());
            Mascota mascota = new Mascota(0, txtNombre.getText(), txtEspecie.getText(), txtRaza.getText(), edad, clienteId);
            if (mascotaController.insertarMascota(mascota)) {
                mostrarMensaje("Éxito", "Mascota agregada con éxito.", AlertType.INFORMATION);
                llenarTablaMascotas();
                limpiarCampos();
            } else {
                mostrarMensaje("Error", "No se pudo agregar la mascota.", AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Edad e ID Cliente deben ser valores numéricos.", AlertType.ERROR);
        }
    }

    /**
     * Limpia los campos de texto de entrada de datos.
     */
    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtEspecie.clear();
        txtRaza.clear();
        txtEdad.clear();
        txtClienteId.clear();
        txtBuscar.clear();
    }

    /**
     * Muestra un mensaje de alerta en la pantalla.
     * @param titulo El título de la ventana de la alerta.
     * @param mensaje El mensaje a mostrar.
     * @param tipoAlerta El tipo de alerta (ERROR, INFORMATION, WARNING, etc.).
     */
    private void mostrarMensaje(String titulo, String mensaje, AlertType tipoAlerta) {
        Alert alerta = new Alert(tipoAlerta);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
     * Actualiza los datos de una mascota seleccionada en la tabla.
     * @param event Evento de la acción.
     */
    @FXML
    private void actualizar(ActionEvent event) {
        try {
            Mascota mascotaSeleccionada = tablaMascotas.getSelectionModel().getSelectedItem();
            int edad = Integer.parseInt(txtEdad.getText());
            int clienteId = Integer.parseInt(txtClienteId.getText());

            mascotaSeleccionada.setNombre(txtNombre.getText());
            mascotaSeleccionada.setEspecie(txtEspecie.getText());
            mascotaSeleccionada.setRaza(txtRaza.getText());
            mascotaSeleccionada.setEdad(edad);
            mascotaSeleccionada.setCliente_id(clienteId);

            if (mascotaController.actualizarMascota(mascotaSeleccionada)) {
                mostrarMensaje("Éxito", "Mascota actualizada con éxito.", AlertType.INFORMATION);
                llenarTablaMascotas();
            } else {
                mostrarMensaje("Error", "No se pudo actualizar la mascota.", AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Edad e ID Cliente deben ser valores numéricos.", AlertType.ERROR);
        }
    }

    /**
     * Elimina la mascota seleccionada en la tabla.
     * @param event Evento de la acción.
     */
    @FXML
    private void eliminar(ActionEvent event) {
        Mascota mascotaSeleccionada = tablaMascotas.getSelectionModel().getSelectedItem();
        if (mascotaController.eliminarMascota(mascotaSeleccionada.getId())) {
            mostrarMensaje("Éxito", "Mascota eliminada con éxito.", AlertType.INFORMATION);
            llenarTablaMascotas();
        } else {
            mostrarMensaje("Error", "No se pudo eliminar la mascota.", AlertType.ERROR);
        }
    }

    /**
     * Vuelve a la vista principal del menú.
     * @param event Evento de la acción.
     */
    @FXML
    private void volverMenuPrincipal(ActionEvent event) {
        try {
            MiAnimal.cambiarVista("/Vista/menuPrincipal.fxml", "Menú Principal");
        } catch (Exception e) {
            mostrarMensaje("Error", "No se pudo volver al menú principal.", AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
