package com.gjimenez.bank.service;

import com.gjimenez.bank.bean.MovimientoBean;
import com.gjimenez.bank.utils.ResponseDto;

import java.util.Map;

public interface IMovimientoService {
    public ResponseDto  guardar(MovimientoBean movimientoBean);
}
