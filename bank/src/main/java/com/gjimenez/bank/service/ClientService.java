package com.gjimenez.bank.service;

import com.gjimenez.bank.entities.PersonaEntity;
import com.gjimenez.bank.utils.ResponseDto;
import com.gjimenez.bank.bean.PersonaBean;
import com.gjimenez.bank.entities.ClienteEntity;
import com.gjimenez.bank.repository.IClienteRepository;
import com.gjimenez.bank.utils.CommonErrors;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientService implements  IClienteService {
    private final IClienteRepository clienteRepository;

    public ClientService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteEntity> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public ResponseDto<Object> obtenerPorId(Long id) {
        Example<ClienteEntity> example = Example.of(new ClienteEntity(id, null, null, true, null));
        List<ClienteEntity> clientes = clienteRepository.findAll(example);
        if(clientes.isEmpty()){
            return new ResponseDto<Object>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo());
        }

        return new ResponseDto<Object>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo(), clientes.get(0));
    }

    @Override
    public ResponseDto<Object> actualizar(PersonaBean personaBean, Map<String, String> headers, Long id) {
        String clientId = headers.get("user");
        String clave = headers.get("clave");

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

        try{
            ClienteEntity result = this.clienteRepository.save(clienteExistente);
            return new ResponseDto<>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo(), result);
        }catch (Exception e){
            return new ResponseDto<>(CommonErrors.BAD_REQUEST.getMensaje(), CommonErrors.BAD_REQUEST.getCodigo());

        }
    }


    @Override
    public ResponseDto<Object> eliminarPorId(Long id) {
        ClienteEntity clienteExistente = this.clienteRepository.findById(id).orElse(null);

        if(clienteExistente == null){
            return new ResponseDto<Object>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo());
        }
        clienteExistente.setEstado(false);
        this.clienteRepository.save(clienteExistente);
        return new ResponseDto<>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo());
    }

    @Override
    public ResponseDto<Object> guardar(PersonaBean personaBean, String clientId, String clave) {

            ClienteEntity cliente = new ClienteEntity();
            cliente.setClienteId(clientId);
            cliente.setClave(clave);
            cliente.setEstado(true);

            PersonaEntity persona = new PersonaEntity();
            persona.setDireccion(personaBean.getDireccion());
            persona.setIdentificacion(persona.getIdentificacion());
            persona.setNombre(personaBean.getNombre());
            persona.setEdad(personaBean.getEdad());
            persona.setTelefono(personaBean.getTelefono());
            persona.setGenero(personaBean.getGenero());

            cliente.setPersonaEntity(persona);
            persona.setCliente(cliente);
            try{
                ClienteEntity result = this.clienteRepository.save(cliente);
                return new ResponseDto<>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo(), result);
            }catch (Exception e){
                return new ResponseDto<>(CommonErrors.BAD_REQUEST.getMensaje(), CommonErrors.BAD_REQUEST.getCodigo());

            }
    }
}
