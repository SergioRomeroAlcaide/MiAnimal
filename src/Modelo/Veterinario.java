/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Veterinario {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty especialidad;
    private final SimpleStringProperty telefono;
    private final SimpleStringProperty email;

    // Constructor para lectura desde la base de datos
    public Veterinario(int id, String nombre, String especialidad, String telefono, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.especialidad = new SimpleStringProperty(especialidad);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
    }

    // Constructor para creación (sin ID)
    public Veterinario(String nombre, String especialidad, String telefono, String email) {
        this.id = new SimpleIntegerProperty(0); // ID inicial como 0 hasta que se asigne en la base de datos
        this.nombre = new SimpleStringProperty(nombre);
        this.especialidad = new SimpleStringProperty(especialidad);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
    }

    // Getters y Setters
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad.get();
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad.set(especialidad);
    }

    public SimpleStringProperty especialidadProperty() {
        return especialidad;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public SimpleStringProperty telefonoProperty() {
        return telefono;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }
}



