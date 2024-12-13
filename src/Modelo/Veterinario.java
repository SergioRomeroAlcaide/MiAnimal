package Modelo;

public class Veterinario {
    private int id;
    private String nombre;
    private String especialidad;
    private String telefono;
    private String email;

    // Constructor
    public Veterinario(int id, String nombre, String especialidad, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public String toString() {
        return String.format("Veterinario{id=%d, nombre='%s', especialidad='%s', telefono='%s', email='%s'}", 
                              id, nombre, especialidad, telefono, email);
    }
}
