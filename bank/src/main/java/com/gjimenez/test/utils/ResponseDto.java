package com.gjimenez.test.utils;

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
}
