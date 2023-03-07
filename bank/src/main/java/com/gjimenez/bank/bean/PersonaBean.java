package com.gjimenez.bank.bean;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
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
}
