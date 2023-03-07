package com.gjimenez.bank.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Entity
@Table(name = "Movimiento")
public class MovimientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "tipoMovimiento")
    private String tipoMovimiento;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private CuentaEntity cuentaEntity;

    public MovimientoEntity() {
    }

    public MovimientoEntity(Long id, LocalDateTime fecha, String tipoMovimiento, BigDecimal valor, Boolean estado, BigDecimal saldo, CuentaEntity cuentaEntity) {
        this.id = id;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.estado = estado;
        this.saldo = saldo;
        this.cuentaEntity = cuentaEntity;
    }
}
