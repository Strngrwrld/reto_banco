package com.gjimenez.bank.repository;

import com.gjimenez.bank.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("SELECT c FROM ClienteEntity c WHERE c.id = ?1 and c.estado = ?2")
    Optional<ClienteEntity> findByIdAndStatus(long id, boolean estado);
}
