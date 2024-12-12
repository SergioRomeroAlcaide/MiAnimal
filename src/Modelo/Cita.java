package Modelo;

/**
 * Clase que representa una Cita en la veterinaria MiAnimal.
 */
public class Cita {

    // Atributos de la clase que corresponden a los campos de la base de datos
    private int id; // ID de la cita, clave primaria
    private String fechaHora; // Fecha y hora de la cita en formato 'yyyy-MM-dd HH:mm:ss'
    private String motivo; // Motivo de la cita
    private int mascota_id; // ID de la mascota asociada a la cita
    private int veterinario_id; // ID del veterinario asignado a la cita
    private boolean recordatorioEnviado; // Indica si se envió un recordatorio para la cita

    /**
     * Constructor completo para la clase Cita.
     * 
     * @param id Identificador único de la cita.
     * @param fechaHora Fecha y hora de la cita (formato 'yyyy-MM-dd HH:mm:ss').
     * @param motivo Motivo de la cita.
     * @param mascota_id ID de la mascota asociada a la cita.
     * @param veterinario_id ID del veterinario asignado a la cita.
     * @param recordatorioEnviado Indica si se envió un recordatorio para la cita.
     */
    public Cita(int id, String fechaHora, String motivo, int mascota_id, int veterinario_id, boolean recordatorioEnviado) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.mascota_id = mascota_id;
        this.veterinario_id = veterinario_id;
        this.recordatorioEnviado = recordatorioEnviado;
    }

    /**
     * Constructor sin ID, usado para crear una cita antes de insertarla en la base de datos.
     * El ID se asignará automáticamente por la base de datos.
     * 
     * @param fechaHora Fecha y hora de la cita (formato 'yyyy-MM-dd HH:mm:ss').
     * @param motivo Motivo de la cita.
     * @param mascota_id ID de la mascota asociada a la cita.
     * @param veterinario_id ID del veterinario asignado a la cita.
     * @param recordatorioEnviado Indica si se envió un recordatorio para la cita.
     */
    public Cita(String fechaHora, String motivo, int mascota_id, int veterinario_id, boolean recordatorioEnviado) {
        this(0, fechaHora, motivo, mascota_id, veterinario_id, recordatorioEnviado);
    }

    /**
     * Obtiene el ID de la cita.
     * 
     * @return El ID de la cita.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Establece el ID de la cita.
     * 
     * @param id El ID a asignar.
     */
    public void setId(int id) { 
        this.id = id; 
    }

    /**
     * Obtiene la fecha y hora de la cita.
     * 
     * @return La fecha y hora de la cita.
     */
    public String getFechaHora() { 
        return fechaHora; 
    }

    /**
     * Establece la fecha y hora de la cita.
     * 
     * @param fechaHora La fecha y hora a asignar (formato 'yyyy-MM-dd HH:mm:ss').
     */
    public void setFechaHora(String fechaHora) { 
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
     * @param motivo El motivo a asignar.
     */
    public void setMotivo(String motivo) { 
        this.motivo = motivo; 
    }

    /**
     * Obtiene el ID de la mascota asociada a la cita.
     * 
     * @return El ID de la mascota asociada.
     */
    public int getMascota_id() { 
        return mascota_id; 
    }

    /**
     * Establece el ID de la mascota asociada a la cita.
     * 
     * @param mascota_id El ID de la mascota.
     */
    public void setMascota_id(int mascota_id) { 
        this.mascota_id = mascota_id; 
    }

    /**
     * Obtiene el ID del veterinario asignado a la cita.
     * 
     * @return El ID del veterinario.
     */
    public int getVeterinario_id() { 
        return veterinario_id; 
    }

    /**
     * Establece el ID del veterinario asignado a la cita.
     * 
     * @param veterinario_id El ID del veterinario.
     */
    public void setVeterinario_id(int veterinario_id) { 
        this.veterinario_id = veterinario_id; 
    }

    /**
     * Verifica si se ha enviado un recordatorio para la cita.
     * 
     * @return true si se envió un recordatorio, false de lo contrario.
     */
    public boolean isRecordatorioEnviado() { 
        return recordatorioEnviado; 
    }

    /**
     * Establece el estado del recordatorio de la cita.
     * 
     * @param recordatorioEnviado true si se ha enviado el recordatorio, false de lo contrario.
     */
    public void setRecordatorioEnviado(boolean recordatorioEnviado) { 
        this.recordatorioEnviado = recordatorioEnviado; 
    }

    /**
     * Devuelve una representación en cadena de la cita.
     * 
     * @return Una cadena con los detalles de la cita.
     */
    @Override
    public String toString() {
        return String.format(
            "Cita{id=%d, fechaHora='%s', motivo='%s', mascota_id=%d, veterinario_id=%d, recordatorioEnviado=%b}", 
            id, fechaHora, motivo, mascota_id, veterinario_id, recordatorioEnviado
        );
    }
}
