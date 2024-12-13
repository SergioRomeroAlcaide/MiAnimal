package Modelo;

import java.util.Objects;

/**
 * Clase que representa una mascota en la aplicación MiAnimal.
 * Contiene información sobre el ID de la mascota, su nombre, especie, raza, edad y el cliente al que pertenece.
 */
public class Mascota {
    
    /**
     * Identificador único de la mascota.
     */
    private int id;
    
    /**
     * Nombre de la mascota.
     */
    private String nombre;
    
    /**
     * Especie de la mascota (Perro, Gato, Conejo, etc.).
     */
    private String especie;
    
    /**
     * Raza de la mascota.
     */
    private String raza;
    
    /**
     * Edad de la mascota.
     */
    private int edad;
    
    /**
     * Identificador del cliente al que pertenece la mascota.
     */
    private int cliente_id;

    /**
     * Constructor principal con todos los campos.
     * 
     * @param id Identificador de la mascota.
     * @param nombre Nombre de la mascota.
     * @param especie Especie de la mascota (Perro, Gato, Conejo, etc.).
     * @param raza Raza de la mascota.
     * @param edad Edad de la mascota.
     * @param cliente_id ID del cliente al que pertenece la mascota.
     */
    public Mascota(int id, String nombre, String especie, String raza, int edad, int cliente_id) {
        this.id = id;
        setNombre(nombre);
        setEspecie(especie);
        setRaza(raza);
        setEdad(edad);
        setCliente_id(cliente_id);
    }

    /**
     * Constructor alternativo que no recibe ID, ya que se generará automáticamente en la base de datos.
     * 
     * @param nombre Nombre de la mascota.
     * @param especie Especie de la mascota (Perro, Gato, Conejo, etc.).
     * @param raza Raza de la mascota.
     * @param edad Edad de la mascota.
     * @param cliente_id ID del cliente al que pertenece la mascota.
     */
    public Mascota(String nombre, String especie, String raza, int edad, int cliente_id) {
        this(0, nombre, especie, raza, edad, cliente_id);
    }

    /**
     * Obtiene el identificador de la mascota.
     * 
     * @return El ID de la mascota.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Establece el identificador de la mascota.
     * 
     * @param id El nuevo ID de la mascota.
     */
    public void setId(int id) { 
        if (id < 0) {
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        }
        this.id = id; 
    }

    /**
     * Obtiene el nombre de la mascota.
     * 
     * @return El nombre de la mascota.
     */
    public String getNombre() { 
        return nombre; 
    }

    /**
     * Establece el nombre de la mascota.
     * 
     * @param nombre El nuevo nombre de la mascota.
     */
    public void setNombre(String nombre) { 
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre; 
    }

    /**
     * Obtiene la especie de la mascota.
     * 
     * @return La especie de la mascota.
     */
    public String getEspecie() { 
        return especie; 
    }

    /**
     * Establece la especie de la mascota.
     * 
     * @param especie La nueva especie de la mascota.
     */
    public void setEspecie(String especie) { 
        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("La especie no puede estar vacía.");
        }
        this.especie = especie; 
    }

    /**
     * Obtiene la raza de la mascota.
     * 
     * @return La raza de la mascota.
     */
    public String getRaza() { 
        return raza; 
    }

    /**
     * Establece la raza de la mascota.
     * 
     * @param raza La nueva raza de la mascota.
     */
    public void setRaza(String raza) { 
        this.raza = raza; 
    }

    /**
     * Obtiene la edad de la mascota.
     * 
     * @return La edad de la mascota.
     */
    public int getEdad() { 
        return edad; 
    }

    /**
     * Establece la edad de la mascota.
     * 
     * @param edad La nueva edad de la mascota.
     */
    public void setEdad(int edad) { 
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.edad = edad; 
    }

    /**
     * Obtiene el identificador del cliente al que pertenece la mascota.
     * 
     * @return El ID del cliente.
     */
    public int getCliente_id() { 
        return cliente_id; 
    }

    /**
     * Establece el identificador del cliente al que pertenece la mascota.
     * 
     * @param cliente_id El nuevo ID del cliente.
     */
    public void setCliente_id(int cliente_id) { 
        if (cliente_id <= 0) {
            throw new IllegalArgumentException("El ID del cliente no puede ser cero o negativo.");
        }
        this.cliente_id = cliente_id; 
    }

    @Override
    public String toString() {
        return String.format(
            "Mascota{id=%d, nombre='%s', especie='%s', raza='%s', edad=%d, cliente_id=%d}", 
            id, nombre, especie, raza, edad, cliente_id
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mascota mascota = (Mascota) obj;
        return id == mascota.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
