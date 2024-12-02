/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Modelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Mascota {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nombre;
    private SimpleStringProperty especie;
    private SimpleStringProperty raza;
    private SimpleIntegerProperty edad;
    private SimpleIntegerProperty clienteId;

    // Constructor para creación (sin ID)
    public Mascota(String nombre, String especie, String raza, int edad, int clienteId) {
        this.id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty(nombre);
        this.especie = new SimpleStringProperty(especie);
        this.raza = new SimpleStringProperty(raza);
        this.edad = new SimpleIntegerProperty(edad);
        this.clienteId = new SimpleIntegerProperty(clienteId);
    }

    // Constructor para lectura (con ID)
    public Mascota(int id, String nombre, String especie, String raza, int edad, int clienteId) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.especie = new SimpleStringProperty(especie);
        this.raza = new SimpleStringProperty(raza);
        this.edad = new SimpleIntegerProperty(edad);
        this.clienteId = new SimpleIntegerProperty(clienteId);
    }

    // Getters y Setters para JavaFX
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

    public String getEspecie() {
        return especie.get();
    }

    public void setEspecie(String especie) {
        this.especie.set(especie);
    }

    public SimpleStringProperty especieProperty() {
        return especie;
    }

    public String getRaza() {
        return raza.get();
    }

    public void setRaza(String raza) {
        this.raza.set(raza);
    }

    public SimpleStringProperty razaProperty() {
        return raza;
    }

    public int getEdad() {
        return edad.get();
    }

    public void setEdad(int edad) {
        this.edad.set(edad);
    }

    public SimpleIntegerProperty edadProperty() {
        return edad;
    }

    public int getClienteId() {
        return clienteId.get();
    }

    public void setClienteId(int clienteId) {
        this.clienteId.set(clienteId);
    }

    public SimpleIntegerProperty clienteIdProperty() {
        return clienteId;
    }
}


