package com.gjimenez.bank.repository;

import com.gjimenez.bank.entities.ClienteEntity;
import com.gjimenez.bank.entities.MovimientoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IMovimientoRepository extends JpaRepository<MovimientoEntity, Long> {
    @Query(value = "SELECT * FROM movimiento m " +
            "INNER JOIN cuenta cu ON m.cuenta_id = cu.id " +
            "INNER JOIN cliente cli ON cli.id = cu.cliente_id " +
            "INNER JOIN persona per ON cli.id = per.cliente_id " +
            "WHERE m.fecha BETWEEN ?1 AND ?2 AND cli.cliente_id = ?3 ORDER BY m.fecha DESC", nativeQuery = true)
    List<MovimientoEntity> findByFechaBetweenAndCliente(Date fechaInicio, Date fechaFin, String clienteId);
    @Query(value = "SELECT m FROM MovimientoEntity m WHERE m.cuentaEntity.nroCuenta = ?1 ORDER BY m.fecha DESC")
    List<MovimientoEntity> findLastByNroCuenta(String nroCuenta, Pageable pageable);
}
