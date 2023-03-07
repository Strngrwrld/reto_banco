package com.gjimenez.bank.repository;

import com.gjimenez.bank.entities.MovimientoEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IMovimientoRepository extends JpaRepository<MovimientoEntity, Long> {
    @Query(value = "SELECT m FROM MovimientoEntity m WHERE m.cuentaEntity.nroCuenta = ?1 ORDER BY m.fecha DESC")
    List<MovimientoEntity> findLastByNroCuenta(String nroCuenta, Pageable pageable);

}
