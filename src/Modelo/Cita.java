package Modelo;

import java.time.LocalDateTime;

/**
 * Clase que representa una cita en la aplicación MiAnimal.
 * Contiene información sobre la fecha, hora, motivo de la cita, mascota, veterinario asignado y el estado del recordatorio.
 */
public class Cita {
    
    /**
     * Identificador único de la cita.
     */
    private int id;
    
    /**
     * Fecha y hora de la cita.
     */
    private LocalDateTime fechaHora;
    
    /**
     * Motivo de la cita.
     */
    private String motivo;
    
    /**
     * Identificador de la mascota asociada a la cita.
     */
    private int mascotaId;
    
    /**
     * Identificador del veterinario asignado a la cita.
     */
    private int veterinarioId;
    
    /**
     * Indica si se ha enviado el recordatorio de la cita.
     */
    private boolean recordatorioEnviado;

    /**
     * Constructor que inicializa los atributos de la cita con los valores proporcionados.
     * 
     * @param id Identificador único de la cita.
     * @param fechaHora Fecha y hora de la cita.
     * @param motivo Motivo de la cita.
     * @param mascotaId Identificador de la mascota asociada a la cita.
     * @param veterinarioId Identificador del veterinario asignado a la cita.
     * @param recordatorioEnviado Indica si se ha enviado el recordatorio de la cita.
     */
    public Cita(int id, LocalDateTime fechaHora, String motivo, int mascotaId, int veterinarioId, boolean recordatorioEnviado) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.mascotaId = mascotaId;
        this.veterinarioId = veterinarioId;
        this.recordatorioEnviado = recordatorioEnviado;
    }

    /**
     * Obtiene el identificador único de la cita.
     * 
     * @return El ID de la cita.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Establece el identificador único de la cita.
     * 
     * @param id El nuevo ID de la cita.
     */
    public void setId(int id) { 
        this.id = id; 
    }

    /**
     * Obtiene la fecha y hora de la cita.
     * 
     * @return La fecha y hora de la cita.
     */
    public LocalDateTime getFechaHora() { 
        return fechaHora; 
    }

    /**
     * Establece la fecha y hora de la cita.
     * 
     * @param fechaHora La nueva fecha y hora de la cita.
     */
    public void setFechaHora(LocalDateTime fechaHora) { 
        this.fechaHora = fechaHora; 
    }

    /**
     * Obtiene el motivo de la cita.
     * 
     * @return El motivo de la cita.
     */
    public String getMotivo() { 
        return motivo; 
    }

    /**
     * Establece el motivo de la cita.
     * 
     * @param motivo El nuevo motivo de la cita.
     */
    public void setMotivo(String motivo) { 
        this.motivo = motivo; 
    }

    /**
     * Obtiene el identificador de la mascota asociada a la cita.
     * 
     * @return El ID de la mascota.
     */
    public int getMascotaId() { 
        return mascotaId; 
    }

    /**
     * Establece el identificador de la mascota asociada a la cita.
     * 
     * @param mascotaId El nuevo ID de la mascota.
     */
    public void setMascotaId(int mascotaId) { 
        this.mascotaId = mascotaId; 
    }

    /**
     * Obtiene el identificador del veterinario asignado a la cita.
     * 
     * @return El ID del veterinario.
     */
    public int getVeterinarioId() { 
        return veterinarioId; 
    }

    /**
     * Establece el identificador del veterinario asignado a la cita.
     * 
     * @param veterinarioId El nuevo ID del veterinario.
     */
    public void setVeterinarioId(int veterinarioId) { 
        this.veterinarioId = veterinarioId; 
    }

    /**
     * Verifica si se ha enviado el recordatorio de la cita.
     * 
     * @return {@code true} si el recordatorio ha sido enviado, de lo contrario {@code false}.
     */
    public boolean isRecordatorioEnviado() { 
        return recordatorioEnviado; 
    }

    /**
     * Establece si se ha enviado el recordatorio de la cita.
     * 
     * @param recordatorioEnviado Indica si el recordatorio de la cita ha sido enviado.
     */
    public void setRecordatorioEnviado(boolean recordatorioEnviado) { 
        this.recordatorioEnviado = recordatorioEnviado; 
    }

    /**
     * Devuelve una representación en cadena de texto de la cita.
     * 
     * @return Una cadena de texto con los valores de los atributos de la cita.
     */
    @Override
    public String toString() {
        return String.format(
            "Cita{id=%d, fechaHora='%s', motivo='%s', mascotaId=%d, veterinarioId=%d, recordatorioEnviado=%b}", 
            id, fechaHora.toString(), motivo, mascotaId, veterinarioId, recordatorioEnviado
        );
    }
}

