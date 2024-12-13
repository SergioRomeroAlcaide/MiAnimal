package Modelo;

import java.time.LocalDateTime;

public class Cita {
    private int id;
    private LocalDateTime fechaHora; // Cambio de String a LocalDateTime
    private String motivo;
    private int mascotaId;
    private int veterinarioId;
    private boolean recordatorioEnviado;

    // Constructor
    public Cita(int id, LocalDateTime fechaHora, String motivo, int mascotaId, int veterinarioId, boolean recordatorioEnviado) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.recordatorioEnviado = recordatorioEnviado;
    }

    // Getters y Setters
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }

    public LocalDateTime getFechaHora() { 
        return fechaHora; 
    }
    public void setFechaHora(LocalDateTime fechaHora) { 
        this.fechaHora = fechaHora; 
    }

    public String getMotivo() { 
        return motivo; 
    }
    public void setMotivo(String motivo) { 
        this.motivo = motivo; 
    }

    public int getMascotaId() { 
        return mascotaId; 
    }
    public void setMascotaId(int mascotaId) { 
        this.mascotaId = mascotaId; 
    }

    public int getVeterinarioId() { 
        return veterinarioId; 
    }
    public void setVeterinarioId(int veterinarioId) { 
        this.veterinarioId = veterinarioId; 
    }

    public boolean isRecordatorioEnviado() { 
        return recordatorioEnviado; 
    }
    public void setRecordatorioEnviado(boolean recordatorioEnviado) { 
        this.recordatorioEnviado = recordatorioEnviado; 
    }

    @Override
    public String toString() {
        return String.format(
            "Cita{id=%d, fechaHora='%s', motivo='%s', mascotaId=%d, veterinarioId=%d, recordatorioEnviado=%b}", 
            id, fechaHora.toString(), motivo, mascotaId, veterinarioId, recordatorioEnviado
        );
    }
}
