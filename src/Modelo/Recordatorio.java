/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Modelo;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Recordatorio {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty clienteNombre;
    private final SimpleStringProperty mascotaNombre;
    private final SimpleStringProperty motivo;
    private final SimpleObjectProperty<LocalDateTime> fechaHora;
    private final SimpleBooleanProperty enviado;

    public Recordatorio(int id, String clienteNombre, String mascotaNombre, String motivo,
                        LocalDateTime fechaHora, boolean enviado) {
        this.id = new SimpleIntegerProperty(id);
        this.clienteNombre = new SimpleStringProperty(clienteNombre);
        this.mascotaNombre = new SimpleStringProperty(mascotaNombre);
        this.motivo = new SimpleStringProperty(motivo);
        this.fechaHora = new SimpleObjectProperty<>(fechaHora);
        this.enviado = new SimpleBooleanProperty(enviado);
    }

    // Getters y Setters
    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getClienteNombre() {
        return clienteNombre.get();
    }

    public SimpleStringProperty clienteNombreProperty() {
        return clienteNombre;
    }

    public String getMascotaNombre() {
        return mascotaNombre.get();
    }

    public SimpleStringProperty mascotaNombreProperty() {
        return mascotaNombre;
    }

    public String getMotivo() {
        return motivo.get();
    }

    public SimpleStringProperty motivoProperty() {
        return motivo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora.get();
    }

    public SimpleObjectProperty<LocalDateTime> fechaHoraProperty() {
        return fechaHora;
    }

    public boolean isEnviado() {
        return enviado.get();
    }

    public SimpleBooleanProperty enviadoProperty() {
        return enviado;
    }
}
