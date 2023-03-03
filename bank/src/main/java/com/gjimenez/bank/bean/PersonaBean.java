package com.gjimenez.bank.bean;

import javax.validation.constraints.NotBlank;

public class PersonaBean {
    @NotBlank(message = "Nombre es requerido")
    private String nombre;
    @NotBlank(message = "Genero es requerido")
    private String genero;
    @NotBlank(message = "Edad es requerido")
    private String edad;
    @NotBlank(message = "Identificacion es requerido")
    private String identificacion;
    @NotBlank(message = "Direccion es requerido")
    private String direccion;
    @NotBlank(message = "Telefono es requerido")
    private String telefono;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
