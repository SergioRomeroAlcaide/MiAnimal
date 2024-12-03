/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Modelo;

import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Cita {
    private SimpleIntegerProperty id;
    private SimpleObjectProperty<LocalDateTime> fechaHora;
    private SimpleStringProperty motivo;
    private SimpleStringProperty mascotaNombre;
    private SimpleStringProperty clienteNombre;
    private SimpleIntegerProperty mascotaId;
    private SimpleIntegerProperty veterinarioId;
    private SimpleBooleanProperty recordatorioEnviado;

    // Constructor para creación (sin ID)
    public Cita(LocalDateTime fechaHora, String motivo, String mascotaNombre, String clienteNombre, 
                int mascotaId, int veterinarioId, boolean recordatorioEnviado) {
        this.id = new SimpleIntegerProperty();
        this.fechaHora = new SimpleObjectProperty<>(fechaHora);
        this.motivo = new SimpleStringProperty(motivo);
        this.mascotaNombre = new SimpleStringProperty(mascotaNombre);
        this.clienteNombre = new SimpleStringProperty(clienteNombre);
        this.mascotaId = new SimpleIntegerProperty(mascotaId);
        this.veterinarioId = new SimpleIntegerProperty(veterinarioId);
        this.recordatorioEnviado = new SimpleBooleanProperty(recordatorioEnviado);
    }

    // Constructor para lectura (con ID)
    public Cita(int id, LocalDateTime fechaHora, String motivo, String mascotaNombre, String clienteNombre, 
                int mascotaId, int veterinarioId, boolean recordatorioEnviado) {
        this.id = new SimpleIntegerProperty(id);
        this.fechaHora = new SimpleObjectProperty<>(fechaHora);
        this.motivo = new SimpleStringProperty(motivo);
        this.mascotaNombre = new SimpleStringProperty(mascotaNombre);
        this.clienteNombre = new SimpleStringProperty(clienteNombre);
        this.mascotaId = new SimpleIntegerProperty(mascotaId);
        this.veterinarioId = new SimpleIntegerProperty(veterinarioId);
        this.recordatorioEnviado = new SimpleBooleanProperty(recordatorioEnviado);
    }

    // Getters y setters
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public SimpleIntegerProperty idProperty() { return id; }

    public LocalDateTime getFechaHora() { return fechaHora.get(); }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora.set(fechaHora); }
    public SimpleObjectProperty<LocalDateTime> fechaHoraProperty() { return fechaHora; }

    public String getMotivo() { return motivo.get(); }
    public void setMotivo(String motivo) { this.motivo.set(motivo); }
    public SimpleStringProperty motivoProperty() { return motivo; }

    public String getMascotaNombre() { return mascotaNombre.get(); }
    public void setMascotaNombre(String mascotaNombre) { this.mascotaNombre.set(mascotaNombre); }
    public SimpleStringProperty mascotaNombreProperty() { return mascotaNombre; }

    public String getClienteNombre() { return clienteNombre.get(); }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre.set(clienteNombre); }
    public SimpleStringProperty clienteNombreProperty() { return clienteNombre; }

    public int getMascotaId() { return mascotaId.get(); }
    public void setMascotaId(int mascotaId) { this.mascotaId.set(mascotaId); }
    public SimpleIntegerProperty mascotaIdProperty() { return mascotaId; }

    public int getVeterinarioId() { return veterinarioId.get(); }
    public void setVeterinarioId(int veterinarioId) { this.veterinarioId.set(veterinarioId); }
    public SimpleIntegerProperty veterinarioIdProperty() { return veterinarioId; }

    public boolean isRecordatorioEnviado() { return recordatorioEnviado.get(); }
    public void setRecordatorioEnviado(boolean recordatorioEnviado) { this.recordatorioEnviado.set(recordatorioEnviado); }
    public SimpleBooleanProperty recordatorioEnviadoProperty() { return recordatorioEnviado; }
}
