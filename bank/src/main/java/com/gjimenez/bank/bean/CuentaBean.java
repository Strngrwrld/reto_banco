package com.gjimenez.bank.bean;

import com.gjimenez.bank.utils.EnumValidator;
import com.gjimenez.bank.utils.TipoCuenta;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Getter
public class CuentaBean {

    @NotBlank(message = "Número de cuenta es requerido")
    @Pattern(regexp = "^\\d{10}$", message = "Tiene que contener 10 digitos")
    private String nroCuenta;

    @EnumValidator(regexp = "AHORRO|CORRIENTE")
    private TipoCuenta tipoCuenta;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2)
    private BigDecimal saldoInicial;

    @Digits(integer = 15, fraction = 0)
    private Long clienteId;

}
