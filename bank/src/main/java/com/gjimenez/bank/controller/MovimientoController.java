package com.gjimenez.bank.controller;

import com.gjimenez.bank.bean.MovimientoBean;
import com.gjimenez.bank.dto.ReportDto;
import com.gjimenez.bank.entities.MovimientoEntity;
import com.gjimenez.bank.service.IMovimientoService;
import com.gjimenez.bank.utils.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    @GetMapping()
    ResponseEntity obtenerReporte(@RequestParam @DateTimeFormat(pattern="ddMMyyyy") Date fechaInicio, @RequestParam @DateTimeFormat(pattern="ddMMyyyy") Date fechaFin, @RequestParam String clienteId){

        ResponseDto<List<ReportDto>> result = this.movimientoService.obtenerReporte(fechaInicio, fechaFin, clienteId);
        return  new ResponseEntity<>(result, HttpStatus.valueOf(result.getCodigo()));
    }
}
