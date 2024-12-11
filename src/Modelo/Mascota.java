package Modelo;

import java.util.Objects;

public class Mascota {
    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private int cliente_id;

    /**
     * Constructor principal con todos los campos
     * 
     * @param id        Identificador de la mascota
     * @param nombre    Nombre de la mascota
     * @param especie   Especie de la mascota (Perro, Gato, Conejo, etc.)
     * @param raza      Raza de la mascota
     * @param edad      Edad de la mascota
     * @param cliente_id ID del cliente al que pertenece la mascota
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
     * Constructor alternativo que no recibe ID, ya que se generará automáticamente en la base de datos
     * 
     * @param nombre    Nombre de la mascota
     * @param especie   Especie de la mascota (Perro, Gato, Conejo, etc.)
     * @param raza      Raza de la mascota
     * @param edad      Edad de la mascota
     * @param cliente_id ID del cliente al que pertenece la mascota
     */
    public Mascota(String nombre, String especie, String raza, int edad, int cliente_id) {
        this(0, nombre, especie, raza, edad, cliente_id);
    }

    // Getters y Setters
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        if (id < 0) {
            throw new IllegalArgumentException("El ID no puede ser negativo.");
        }
        this.id = id; 
    }

    public String getNombre() { 
        return nombre; 
    }

    public void setNombre(String nombre) { 
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre; 
    }

    public String getEspecie() { 
        return especie; 
    }

    public void setEspecie(String especie) { 
        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("La especie no puede estar vacía.");
        }
        this.especie = especie; 
    }

    public String getRaza() { 
        return raza; 
    }

    public void setRaza(String raza) { 
        this.raza = raza; 
    }

    public int getEdad() { 
        return edad; 
    }

    public void setEdad(int edad) { 
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.edad = edad; 
    }

    public int getCliente_id() { 
        return cliente_id; 
    }

    public void setCliente_id(int cliente_id) { 
        if (cliente_id <= 0) {
            throw new IllegalArgumentException("El ID del cliente no puede ser cero o negativo.");
        }
        this.cliente_id = cliente_id; 
    }

    /**
     * Sobrescritura del método toString para mostrar la información de la mascota de forma legible
     * 
     * @return Una cadena de texto con los datos de la mascota
     */
    @Override
    public String toString() {
        return String.format(
            "Mascota{id=%d, nombre='%s', especie='%s', raza='%s', edad=%d, cliente_id=%d}", 
            id, nombre, especie, raza, edad, cliente_id
        );
    }

    /**
     * Sobrescritura del método equals para comparar si dos objetos Mascota son iguales
     * 
     * @param obj Objeto a comparar con la mascota actual
     * @return true si son iguales, false de lo contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mascota mascota = (Mascota) obj;
        return id == mascota.id;
    }

    /**
     * Sobrescritura del método hashCode para generar un código hash único para cada mascota
     * 
     * @return Código hash de la mascota
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

