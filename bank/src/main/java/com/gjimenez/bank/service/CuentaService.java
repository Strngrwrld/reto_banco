package com.gjimenez.bank.service;

import com.gjimenez.bank.bean.CuentaBean;
import com.gjimenez.bank.bean.PersonaBean;
import com.gjimenez.bank.controller.ClientController;
import com.gjimenez.bank.entities.ClienteEntity;
import com.gjimenez.bank.entities.CuentaEntity;
import com.gjimenez.bank.entities.PersonaEntity;
import com.gjimenez.bank.repository.ICuentaRepository;
import com.gjimenez.bank.utils.CommonErrors;
import com.gjimenez.bank.utils.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CuentaService implements  ICuentaService {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ICuentaRepository cuentaRepository;

    public CuentaService(ICuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public ResponseDto guardar(CuentaBean cuentaBean) {

            CuentaEntity cuenta = CuentaEntity.builder()
                    .nroCuenta(cuentaBean.getNroCuenta())
                    .tipoCuenta(cuentaBean.getTipoCuenta().name())
                    .saldoInicial(cuentaBean.getSaldoInicial())
                    .estado(true)
                    .cliente(ClienteEntity.builder().id(cuentaBean.getClienteId()).build())
                    .build();

        try{
                this.cuentaRepository.save(cuenta);
                return new ResponseDto<>(CommonErrors.CREATED.getMensaje(), CommonErrors.CREATED.getCodigo());
            }catch (Exception e){
            logger.info("save request fail:  {}", e.getMessage());
                return new ResponseDto<>(CommonErrors.BAD_REQUEST.getMensaje(), CommonErrors.BAD_REQUEST.getCodigo());

            }
    }

    @Override
    public CuentaEntity buscarPorNroCuenta(String nroCuenta, boolean estado)    {
        try{
        return this.cuentaRepository.findByNroCuentaAndStatus(nroCuenta, true).orElse(null);
    }catch (Exception e){
        logger.info("save request fail:  {}", e.getMessage());
        throw e;
    }
    }
}
