package com.gjimenez.bank.controller;

import com.gjimenez.bank.bean.CuentaBean;
import com.gjimenez.bank.service.ICuentaService;
import com.gjimenez.bank.utils.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("cuenta")
public class CuentaController {
    @Autowired
    ICuentaService cuentaService;

    @PostMapping()
    ResponseEntity crear(@Valid @RequestBody CuentaBean cuentaBean){
        ResponseDto result = this.cuentaService.guardar(cuentaBean);
        return  new ResponseEntity<>(result, HttpStatus.valueOf(result.getCodigo()));
    }
}
