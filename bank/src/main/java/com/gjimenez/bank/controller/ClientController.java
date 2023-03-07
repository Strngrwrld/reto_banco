package com.gjimenez.bank.controller;

import com.gjimenez.bank.entities.ClienteEntity;
import com.gjimenez.bank.service.IClienteService;
import com.gjimenez.bank.utils.ResponseDto;
import com.gjimenez.bank.bean.PersonaBean;
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

    @Autowired
    IClienteService clientService;

    @GetMapping("/{id}")
    ResponseEntity<Object> obtenerPorId(@PathVariable Long id) {
        ResponseDto<ClienteEntity> result = this.clientService.obtenerPorId(id);
        return  new ResponseEntity<>(result, HttpStatus.valueOf(result.getCodigo()));
    }

    @PostMapping()
    ResponseEntity<Object> crear(@Valid @RequestBody PersonaBean personaBean,
                                 @Valid @RequestHeader(required = true) String user,
                                 @Valid @RequestHeader(required = true) String clave){
        ResponseDto<Object> result = this.clientService.guardar(personaBean, user, clave);
        return  new ResponseEntity<>(result, HttpStatus.valueOf(result.getCodigo()));
    }

    @PutMapping("/{id}")
    ResponseEntity<String> actualizar(@PathVariable Long id, @Valid @RequestBody PersonaBean personaBean, @RequestHeader Map<String, String> headers){
        ResponseDto<Object> result = this.clientService.actualizar(personaBean,headers, id);
        return new ResponseEntity(result, HttpStatus.valueOf(result.getCodigo()));
    }

    @DeleteMapping("/{id}")
    ResponseEntity eliminar(@PathVariable Long id){
        this.clientService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }


}


