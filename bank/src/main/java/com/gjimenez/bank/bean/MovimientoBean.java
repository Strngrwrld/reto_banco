package com.gjimenez.bank.bean;

import com.gjimenez.bank.utils.EnumValidator;
import com.gjimenez.bank.utils.TipoMovimiento;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class MovimientoBean {

    @NotBlank(message = "Número de cuenta es requerido")
    private String nroCuenta;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=10, fraction=2)
    private BigDecimal valor;
    @EnumValidator(regexp = "RETIRO|DEPOSITO")
    private TipoMovimiento tipoMovimiento;
}
