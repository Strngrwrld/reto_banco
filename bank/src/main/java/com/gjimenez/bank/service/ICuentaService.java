package com.gjimenez.bank.service;

import com.gjimenez.bank.bean.CuentaBean;
import com.gjimenez.bank.bean.PersonaBean;
import com.gjimenez.bank.entities.CuentaEntity;
import com.gjimenez.bank.utils.ResponseDto;

import java.util.Map;

public interface ICuentaService {
    public ResponseDto  guardar(CuentaBean cuentaBean);
    public CuentaEntity buscarPorNroCuenta(String nroCuenta, boolean estado);
}
