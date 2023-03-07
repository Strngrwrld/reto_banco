package com.gjimenez.bank.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<D> {
    public int codigo;
    public String mensaje;

    public D data;


    public ResponseDto(String mensaje, int codigo) {
        this.mensaje = mensaje;
        this.codigo = codigo;
    }


    public ResponseDto(String mensaje, int codigo, D data) {
        this.mensaje = mensaje;
        this.codigo = codigo;
        this.data = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }
}
