package com.gjimenez.bank.service;

import com.gjimenez.bank.bean.MovimientoBean;
import com.gjimenez.bank.controller.ClientController;
import com.gjimenez.bank.dto.ReportDto;
import com.gjimenez.bank.entities.CuentaEntity;
import com.gjimenez.bank.entities.MovimientoEntity;
import com.gjimenez.bank.repository.IMovimientoRepository;
import com.gjimenez.bank.utils.CommonErrors;
import com.gjimenez.bank.utils.ResponseDto;
import com.gjimenez.bank.utils.TipoMovimiento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoService implements  IMovimientoService {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final IMovimientoRepository movimientoRepository;
    private final ICuentaService cuentaService;

    public MovimientoService(ICuentaService cuentaService, IMovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaService = cuentaService;
    }

    @Override
    public ResponseDto guardar(MovimientoBean movimientoBean) {

        CuentaEntity cuenta = this.cuentaService.buscarPorNroCuenta(movimientoBean.getNroCuenta(), true);

        if(cuenta == null){
            logger.info("nro account not found");
            return new ResponseDto<>(CommonErrors.BAD_REQUEST.getMensaje(), CommonErrors.BAD_REQUEST.getCodigo());
        }

        List<MovimientoEntity> result = this.movimientoRepository.findLastByNroCuenta(movimientoBean.getNroCuenta(), Pageable.ofSize(1));

        MovimientoEntity lastMovimiento = result.isEmpty() ? null : result.get(0);

        if(lastMovimiento == null){
            logger.info("not movements not found");
            return procesaMovimiento(movimientoBean, cuenta.getId(), cuenta.getSaldoInicial(), movimientoBean.getValor());
        }

        return procesaMovimiento(movimientoBean, cuenta.getId(), lastMovimiento.getSaldo(), movimientoBean.getValor());

    }

    @Override
    public ResponseDto<List<ReportDto>> obtenerReporte(Date desde, Date hasta, String clienteId) {
        BigDecimal saldo = BigDecimal.valueOf(0);
        try{
            List<MovimientoEntity> movimiento = this.movimientoRepository.findByFechaBetweenAndCliente(desde, hasta, clienteId);

            List<ReportDto> report = movimiento.stream().map(x -> ReportDto.builder()
                    .fecha(x.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                    .cliente(x.getCuentaEntity().getCliente().getPersonaEntity().getNombre())
                    .numeroCuenta(x.getCuentaEntity().getNroCuenta())
                    .tipo(x.getCuentaEntity().getTipoCuenta())
                    .estado(x.getEstado())
                    .saldoInicial(x.getCuentaEntity().getSaldoInicial().toString())
                    .saldoDisponible(x.getSaldo().toString())
                    .movimiento(x.getValor().toString()).build()).collect(Collectors.toList());

            return new ResponseDto<>(CommonErrors.CREATED.getMensaje(), CommonErrors.CREATED.getCodigo(), report);
        }catch (Exception e){
            logger.info("request fail:  {}", e.getMessage());
            return new ResponseDto<>(CommonErrors.BAD_REQUEST.getMensaje(), CommonErrors.BAD_REQUEST.getCodigo());

        }
    }

    public ResponseDto procesaMovimiento(MovimientoBean movimientoBean,Long cuentaId, BigDecimal saldoInicial, BigDecimal valor){
        BigDecimal saldo = BigDecimal.valueOf(0);
        if(TipoMovimiento.DEPOSITO.equals(movimientoBean.getTipoMovimiento())){
                saldo = saldoInicial.add(valor);
        }else if(TipoMovimiento.RETIRO.equals(movimientoBean.getTipoMovimiento())){
            if(saldoInicial.compareTo(valor) == -1){
                logger.info("not movements not found");
                return new ResponseDto<>(CommonErrors.SALDO_INSUFICIENTE.getMensaje(), CommonErrors.SALDO_INSUFICIENTE.getCodigo());
            }
                saldo = saldoInicial.subtract(valor);
        }

        MovimientoEntity movimiento = MovimientoEntity.builder()
                .saldo(saldo)
                .tipoMovimiento(movimientoBean.getTipoMovimiento().name())
                .valor(valor)
                .fecha(LocalDateTime.now())
                .estado(true)
                .cuentaEntity(CuentaEntity.builder().id(cuentaId).build())
                .build();

        try{
            this.movimientoRepository.save(movimiento);
            return new ResponseDto<>(CommonErrors.CREATED.getMensaje(), CommonErrors.CREATED.getCodigo());
        }catch (Exception e){
            logger.info("save request fail:  {}", e.getMessage());
            return new ResponseDto<>(CommonErrors.BAD_REQUEST.getMensaje(), CommonErrors.BAD_REQUEST.getCodigo());

        }
    }
}
