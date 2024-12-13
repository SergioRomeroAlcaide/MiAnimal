  package Modelo;

/**
 * Clase que representa a un cliente en la aplicación MiAnimal.
 * Contiene la información básica de un cliente, como su ID, nombre, teléfono, dirección y correo electrónico.
 */
public class Cliente {
    
    /**
     * Identificador único del cliente.
     */
    private int id;
    
    /**
     * Nombre completo del cliente.
     */
    private String nombre;
    
    /**
     * Número de teléfono de contacto del cliente.
     */
    private String telefono;
    
    /**
     * Dirección de residencia o contacto del cliente.
     */
    private String direccion;
    
    /**
     * Correo electrónico del cliente.
     */
    private String email;

    /**
     * Constructor que inicializa los atributos del cliente con los valores proporcionados.
     * 
     * @param id Identificador único del cliente.
     * @param nombre Nombre completo del cliente.
     * @param telefono Número de teléfono del cliente.
     * @param direccion Dirección del cliente.
     * @param email Correo electrónico del cliente.
     */
    public Cliente(int id, String nombre, String telefono, String direccion, String email) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    /**
     * Obtiene el identificador único del cliente.
     * 
     * @return El ID del cliente.
     */
    public int getId() { 
        return id; 
    }

    /**
     * Establece el identificador único del cliente.
     * 
     * @param id El nuevo ID del cliente.
     */
    public void setId(int id) { 
        this.id = id; 
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return El nombre del cliente.
     */
    public String getNombre() { 
        return nombre; 
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param nombre El nuevo nombre del cliente.
     */
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    /**
     * Obtiene el número de teléfono del cliente.
     * 
     * @return El teléfono del cliente.
     */
    public String getTelefono() { 
        return telefono; 
    }

    /**
     * Establece el número de teléfono del cliente.
     * 
     * @param telefono El nuevo teléfono del cliente.
     */
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }

    /**
     * Obtiene la dirección del cliente.
     * 
     * @return La dirección del cliente.
     */
    public String getDireccion() { 
        return direccion; 
    }

    /**
     * Establece la dirección del cliente.
     * 
     * @param direccion La nueva dirección del cliente.
     */
    public void setDireccion(String direccion) { 
        this.direccion = direccion; 
    }

    /**
     * Obtiene el correo electrónico del cliente.
     * 
     * @return El correo electrónico del cliente.
     */
    public String getEmail() { 
        return email; 
    }

    /**
     * Establece el correo electrónico del cliente.
     * 
     * @param email El nuevo correo electrónico del cliente.
     */
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    /**
     * Devuelve una representación en cadena de texto del cliente.
     * 
     * @return Una cadena de texto con los valores de los atributos del cliente.
     */
    @Override
    public String toString() {
        return String.format("Cliente{id=%d, nombre='%s', telefono='%s', direccion='%s', email='%s'}", 
                              id, nombre, telefono, direccion, email);
    }
}
