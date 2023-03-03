package com.gjimenez.bank.entities;


import javax.persistence.*;

@Entity
@Table(name = "cliente")
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

    public ClienteEntity(Long id, String clienteId, String clave, Boolean estado, PersonaEntity personaEntity) {
        this.id = id;
        this.clienteId = clienteId;
        this.clave = clave;
        this.estado = estado;
        this.personaEntity = personaEntity;
    }

    public ClienteEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public PersonaEntity getPersonaEntity() {
        return personaEntity;
    }

    public void setPersonaEntity(PersonaEntity personaEntity) {
        this.personaEntity = personaEntity;
    }

}
