package com.gjimenez.bank.controller;

import com.gjimenez.bank.entities.PersonaEntity;
import com.gjimenez.bank.service.ClientService;
import com.gjimenez.bank.utils.ResponseDto;
import com.gjimenez.bank.bean.PersonaBean;
import com.gjimenez.bank.entities.ClienteEntity;
import com.gjimenez.bank.utils.CommonErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.Map;

@Controller
@RequestMapping("clientes")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientService clientService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Object> obtenerPorId(@PathVariable Long id) {
        ResponseDto<Object> cliente = this.clientService.obtenerPorId(id);
        return  ResponseEntity.ok(cliente);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Object> crear(@Valid @RequestBody PersonaBean personaBean, @RequestHeader Map<String, String> headers){
        String clientId = headers.get("user");
        String clave = headers.get("clave");

        ClienteEntity cliente = new ClienteEntity();
        cliente.setClienteId(clientId);
        cliente.setClave(clave);
        cliente.setEstado(true);


        PersonaEntity persona = new PersonaEntity();
        persona.setDireccion(personaBean.getDireccion());
        persona.setIdentificacion(persona.getIdentificacion());
        persona.setNombre(personaBean.getNombre());
        persona.setEdad(personaBean.getEdad());
        persona.setTelefono(personaBean.getTelefono());
        persona.setGenero(personaBean.getGenero());

        cliente.setPersonaEntity(persona);
        persona.setCliente(cliente);
        this.clientService.guardar(cliente);
        return  ResponseEntity.ok(new ResponseDto<>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo()));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> actualizar(@PathVariable Long id, @Valid @RequestBody PersonaBean personaBean, @RequestHeader Map<String, String> headers){
        String clientId = headers.get("user");
        String clave = headers.get("clave");
        ResponseDto<Object> result = this.clientService.actualizar(personaBean, clientId, clave, id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Object> eliminar(@PathVariable Long id){
        ResponseDto<Object> result = this.clientService.eliminarPorId(id);
        return  ResponseEntity.ok(result);
    }


}


