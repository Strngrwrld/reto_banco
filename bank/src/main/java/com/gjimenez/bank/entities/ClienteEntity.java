package com.gjimenez.bank.entities;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Builder
@Getter
@ToString
@Entity
@Table(name = "Cliente")
public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clienteId", unique = true)
    private String clienteId;

    @Column(name = "clave")
    private String clave;

    @Column(name = "estado")
    private Boolean estado;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private PersonaEntity personaEntity;

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, String clienteId, String clave, Boolean estado, PersonaEntity personaEntity) {
        this.id = id;
        this.clienteId = clienteId;
        this.clave = clave;
        this.estado = estado;
        this.personaEntity = personaEntity;
    }
}
