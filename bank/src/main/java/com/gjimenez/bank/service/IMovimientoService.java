package com.gjimenez.bank.service;

import com.gjimenez.bank.bean.MovimientoBean;
import com.gjimenez.bank.dto.ReportDto;
import com.gjimenez.bank.entities.MovimientoEntity;
import com.gjimenez.bank.utils.ResponseDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IMovimientoService {
    public ResponseDto  guardar(MovimientoBean movimientoBean);

    ResponseDto<List<ReportDto>> obtenerReporte(Date desde, Date hasta, String clienteId);
}
