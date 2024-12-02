/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Modelo;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Cita {

    private final SimpleIntegerProperty id;
    private final SimpleObjectProperty<LocalDateTime> fechaHora;
    private final SimpleStringProperty motivo;
    private final SimpleIntegerProperty mascotaId;
    private final SimpleIntegerProperty veterinarioId;

    // Constructor para lectura desde la base de datos
    public Cita(int id, LocalDateTime fechaHora, String motivo, int mascotaId, int veterinarioId) {
        this.id = new SimpleIntegerProperty(id);
        this.fechaHora = new SimpleObjectProperty<>(fechaHora);
        this.motivo = new SimpleStringProperty(motivo);
        this.mascotaId = new SimpleIntegerProperty(mascotaId);
        this.veterinarioId = new SimpleIntegerProperty(veterinarioId);
    }

    // Constructor para creación (sin ID)
    public Cita(LocalDateTime fechaHora, String motivo, int mascotaId, int veterinarioId) {
        this.id = new SimpleIntegerProperty(0); // ID inicial como 0 hasta que se asigne en la base de datos
        this.fechaHora = new SimpleObjectProperty<>(fechaHora);
        this.motivo = new SimpleStringProperty(motivo);
        this.mascotaId = new SimpleIntegerProperty(mascotaId);
        this.veterinarioId = new SimpleIntegerProperty(veterinarioId);
    }

    // Getters y setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora.get();
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora.set(fechaHora);
    }

    public ObjectProperty<LocalDateTime> fechaHoraProperty() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo.get();
    }

    public void setMotivo(String motivo) {
        this.motivo.set(motivo);
    }

    public StringProperty motivoProperty() {
        return motivo;
    }

    public int getMascotaId() {
        return mascotaId.get();
    }

    public void setMascotaId(int mascotaId) {
        this.mascotaId.set(mascotaId);
    }

    public IntegerProperty mascotaIdProperty() {
        return mascotaId;
    }

    public int getVeterinarioId() {
        return veterinarioId.get();
    }

    public void setVeterinarioId(int veterinarioId) {
        this.veterinarioId.set(veterinarioId);
    }

    public IntegerProperty veterinarioIdProperty() {
        return veterinarioId;
    }
}


