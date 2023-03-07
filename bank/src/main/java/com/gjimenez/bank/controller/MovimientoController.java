package com.gjimenez.bank.controller;

import com.gjimenez.bank.bean.MovimientoBean;
import com.gjimenez.bank.service.IMovimientoService;
import com.gjimenez.bank.utils.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("movimiento")
public class MovimientoController {
    @Autowired
    IMovimientoService movimientoService;

    @PostMapping()
    ResponseEntity crear(@Valid @RequestBody MovimientoBean movimientoBean){
        ResponseDto result = this.movimientoService.guardar(movimientoBean);
        return  new ResponseEntity<>(result, HttpStatus.valueOf(result.getCodigo()));
    }
}
