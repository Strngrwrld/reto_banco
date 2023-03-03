package com.gjimenez.test.repository;

import com.gjimenez.test.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
