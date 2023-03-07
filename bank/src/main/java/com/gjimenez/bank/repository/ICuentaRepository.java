package com.gjimenez.bank.repository;

import com.gjimenez.bank.entities.ClienteEntity;
import com.gjimenez.bank.entities.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICuentaRepository extends JpaRepository<CuentaEntity, Long> {

    @Query(value = "SELECT c FROM CuentaEntity c WHERE c.nroCuenta = ?1 and c.estado = ?2")
    Optional<CuentaEntity> findByNroCuentaAndStatus(String nroCuenta, boolean estado);
}
