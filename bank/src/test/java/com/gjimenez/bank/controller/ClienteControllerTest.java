package com.gjimenez.bank.controller;

import com.gjimenez.bank.bean.PersonaBean;
import com.gjimenez.bank.entities.PersonaEntity;
import com.gjimenez.bank.service.IClienteService;
import com.gjimenez.bank.utils.CommonErrors;
import com.gjimenez.bank.utils.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ClienteControllerTest {
    @Mock
    private IClienteService clientService;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void obtenerPorId_debeRetornarCliente() {
//        Long id = 1L;
//
//        ResponseDto<Object> expected = new ResponseDto<>(new PersonaEntity());
//
//        when(clientService.obtenerPorId(id)).thenReturn(expected);
//
//        ResponseEntity<Object> response = clientController.obtenerPorId(id);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expected, response.getBody());
//
//        verify(clientService).obtenerPorId(id);
//    }

    @Test
    void obtenerPorId_debeRetornarErrorCuandoElClienteNoExiste() {
        Long id = 1L;

        when(clientService.obtenerPorId(id)).thenReturn(new ResponseDto<>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo()));

        ResponseEntity<Object> response = clientController.obtenerPorId(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(clientService).obtenerPorId(id);
    }

//    @Test
//    void crear_debeRetornarClienteCreado() {
//        PersonaBean personaBean = new PersonaBean();
//        String user = "user";
//        String clave = "clave";
//
//        ResponseDto<Object> expected = new ResponseDto<>(new ClienteEntity());
//
//        when(clientService.guardar(personaBean, user, clave)).thenReturn(expected);
//
//        ResponseEntity<Object> response = clientController.crear(personaBean, user, clave);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(expected, response.getBody());
//
//        verify(clientService).guardar(personaBean, user, clave);
//    }
/*

    @Test
    void crear_debeRetornarErrorCuandoHayErroresDeValidacion() {
        PersonaBean personaBean = new PersonaBean();
        String user = "user";
        String clave = "clave";

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<PersonaBean>> violations = validator.validate(personaBean);
        String expected = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        when(clientService.guardar(personaBean, user, clave)).thenReturn(new ResponseDto<>(CommonErrors.BAD_REQUEST.getMensaje(), CommonErrors.BAD_REQUEST.getCodigo()));

        ResponseEntity<Object> response = clientController.crear(personaBean, user, clave);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().toString().contains(expected));

        verify(clientService, never()).guardar(personaBean, user, clave);
    }
*/

}
