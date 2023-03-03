package com.gjimenez.bank.service;

import com.gjimenez.bank.entities.ClienteEntity;
import com.gjimenez.bank.repository.IClienteRepository;
import com.gjimenez.bank.utils.CommonErrors;
import com.gjimenez.bank.utils.ResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {
    @Mock
    private IClienteRepository clienteRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerPorId_existente_retornaCliente() {
        Long id = 1L;
        ClienteEntity cliente = new ClienteEntity(id, null, null, true, null);
        when(clienteRepository.findAll(any(Example.class))).thenReturn(Collections.singletonList(cliente));
        ResponseDto<Object> response = clientService.obtenerPorId(id);
        assertEquals(CommonErrors.OK.getMensaje(), response.getMensaje());
        assertEquals(CommonErrors.OK.getCodigo(), response.getCodigo());
        assertEquals(cliente, response.getData());
    }

    @Test
    void obtenerPorId_noExistente_retornaNotFound() {
        Long id = 1L;
        when(clienteRepository.findAll(any(Example.class))).thenReturn(Collections.emptyList());
        ResponseDto<Object> response = clientService.obtenerPorId(id);
        assertEquals(CommonErrors.NOT_FOUND.getMensaje(), response.getMensaje());
        assertEquals(CommonErrors.NOT_FOUND.getCodigo(), response.getCodigo());
        assertEquals(null, response.getData());
    }
}
