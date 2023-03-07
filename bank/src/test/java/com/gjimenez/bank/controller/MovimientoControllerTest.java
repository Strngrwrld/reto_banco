package com.gjimenez.bank.controller;


import com.gjimenez.bank.BankApplication;
import com.gjimenez.bank.bean.MovimientoBean;
import com.gjimenez.bank.service.MovimientoService;
import com.gjimenez.bank.utils.ResponseDto;
import com.gjimenez.bank.utils.TipoMovimiento;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest(classes = BankApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovimientoControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private MovimientoService movimientoService;

    @LocalServerPort
    private int port;

    @Test
    public void testCrearMovimiento() throws Exception {
        // Crear un objeto de tipo MovimientoBean para usar en la petición
        MovimientoBean movimiento = new MovimientoBean();
        movimiento.setNroCuenta("1000000006");
        movimiento.setTipoMovimiento(TipoMovimiento.RETIRO);
        movimiento.setValor(BigDecimal.valueOf(1000));

        // Configurar el comportamiento del servicio mock
        ResponseDto responseDto = new ResponseDto();
        responseDto.setCodigo(HttpStatus.OK.value());
        responseDto.setMensaje("Movimiento creado correctamente");
        Mockito.when(movimientoService.guardar(Mockito.any(MovimientoBean.class))).thenReturn(responseDto);

        // Hacer la petición POST al endpoint
        ResponseEntity<ResponseDto> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/movimiento",
                movimiento,
                ResponseDto.class);

        // Verificar que el código de respuesta sea 200 y que el mensaje del cuerpo de respuesta sea el esperado
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertEquals("Movimiento creado correctamente", response.getBody().getMensaje());

       }
}