package com.gjimenez.bank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class ReportDto {
    String fecha;
    String cliente;
    String numeroCuenta;
    String tipo;
    String saldoInicial;
    boolean estado;
    String movimiento;
    String saldoDisponible;
}
