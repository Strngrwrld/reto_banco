package com.gjimenez.test.service;

import com.gjimenez.test.bean.PersonaBean;
import com.gjimenez.test.entities.ClienteEntity;
import com.gjimenez.test.repository.IClienteRepository;
import com.gjimenez.test.utils.CommonErrors;
import com.gjimenez.test.utils.ResponseDto;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final IClienteRepository clienteRepository;

    public ClientService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteEntity> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public ResponseDto<Object> obtenerPorId(Long id) {
        Example<ClienteEntity> example = Example.of(new ClienteEntity(id, null, null, true, null));
        List<ClienteEntity> clientes = clienteRepository.findAll(example);
        if(clientes.isEmpty()){
            return new ResponseDto<Object>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo());
        }

        return new ResponseDto<Object>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo(), clientes.get(0));
    }

    public ClienteEntity guardar(ClienteEntity cliente) {
        return clienteRepository.save(cliente);
    }

    public ResponseDto<Object> actualizar(PersonaBean personaBean, String clientId, String clave, Long id) {
        ClienteEntity clienteExistente = this.clienteRepository.findById(id).orElse(null);
        if(clienteExistente == null){
            return new ResponseDto<Object>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo());
        }
        clienteExistente.getPersonaEntity().setDireccion(personaBean.getDireccion());
        clienteExistente.getPersonaEntity().setEdad(personaBean.getEdad());
        clienteExistente.getPersonaEntity().setGenero(personaBean.getGenero());
        clienteExistente.getPersonaEntity().setNombre(personaBean.getNombre());
        clienteExistente.getPersonaEntity().setIdentificacion(personaBean.getIdentificacion());
        clienteExistente.getPersonaEntity().setTelefono(personaBean.getTelefono());

        clienteExistente.setClienteId(clientId);
        clienteExistente.setClave(clave);
        this.clienteRepository.save(clienteExistente);
        return new ResponseDto<Object>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo(), clienteExistente);
    }

    public ResponseDto<Object> eliminarPorId(Long id) {
        ClienteEntity clienteExistente = this.clienteRepository.findById(id).orElse(null);

        if(clienteExistente == null){
            return new ResponseDto<Object>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo());
        }
        clienteExistente.setEstado(false);
        guardar(clienteExistente);
        return new ResponseDto<>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo());
    }

}
