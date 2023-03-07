package com.gjimenez.bank.service;

import com.gjimenez.bank.bean.PersonaBean;
import com.gjimenez.bank.entities.ClienteEntity;
import com.gjimenez.bank.utils.ResponseDto;

import java.util.Map;

public interface IClienteService {
    ResponseDto<ClienteEntity> obtenerPorId(Long id);
    ResponseDto<Object> actualizar(PersonaBean personaBean, Map<String, String> headers, Long id);
    ResponseDto<Object> eliminarPorId(Long id);
    public ResponseDto<Object>  guardar(PersonaBean personaBean, String user, String clave);
}
