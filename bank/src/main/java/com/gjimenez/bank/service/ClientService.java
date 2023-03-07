package com.gjimenez.bank.service;

import com.gjimenez.bank.controller.ClientController;
import com.gjimenez.bank.entities.PersonaEntity;
import com.gjimenez.bank.utils.ResponseDto;
import com.gjimenez.bank.bean.PersonaBean;
import com.gjimenez.bank.entities.ClienteEntity;
import com.gjimenez.bank.repository.IClienteRepository;
import com.gjimenez.bank.utils.CommonErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService implements  IClienteService {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final IClienteRepository clienteRepository;

    public ClientService(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteEntity> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public ResponseDto<Object> obtenerPorId(Long id) {
        try {

            Optional<ClienteEntity> clientes = clienteRepository.findByIdAndStatus(id, true);

            if(!clientes.isPresent()){
                logger.info("Client {} not found", id);
                return new ResponseDto<Object>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo());
            }

            return new ResponseDto<Object>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo(), clientes.get());

        }catch (Exception e){

            logger.error("get request fail:  {}", e.getMessage());
            return new ResponseDto<>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo());
        }
    }

    @Override
    public ResponseDto<Object> actualizar(PersonaBean personaBean, Map<String, String> headers, Long id) {
        String clientId = headers.get("user");
        String clave = headers.get("clave");

        ClienteEntity clienteExistente = this.clienteRepository.findById(id).orElse(null);

        if(clienteExistente == null){
            logger.info("Client {} not found", id);
            return new ResponseDto<Object>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo());
        }

        PersonaEntity persona = PersonaEntity.builder()
                .id(clienteExistente.getPersonaEntity().getId())
                .direccion(personaBean.getDireccion())
                .identificacion(personaBean.getIdentificacion())
                .nombre(personaBean.getNombre())
                .edad(personaBean.getEdad())
                .telefono(personaBean.getGenero())
                .genero(personaBean.getTelefono())
                .build();

        ClienteEntity clienteActualizado =  ClienteEntity.builder().id(clienteExistente.getId()).clienteId(clientId).clave(clave).personaEntity(persona).build();

        try{
            ClienteEntity result = this.clienteRepository.save(clienteActualizado);
            return new ResponseDto<>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo(), result);
        }catch (Exception e){

            logger.error("update request fail:  {}", e.getMessage());
            return new ResponseDto<>(CommonErrors.BAD_REQUEST.getMensaje(), CommonErrors.BAD_REQUEST.getCodigo());

        }
    }


    @Override
    public ResponseDto<Object> eliminarPorId(Long id) {
        ClienteEntity clienteExistente = this.clienteRepository.findById(id).orElse(null);

        if(clienteExistente == null){
            logger.info("client {} not found", id);
            return new ResponseDto<Object>(CommonErrors.NOT_FOUND.getMensaje(), CommonErrors.NOT_FOUND.getCodigo());
        }

        this.clienteRepository.save(ClienteEntity.builder().id(clienteExistente.getId()).estado(false).build());
        return new ResponseDto<>(CommonErrors.OK.getMensaje(), CommonErrors.OK.getCodigo());
    }

    @Override
    public ResponseDto<Object> guardar(PersonaBean personaBean, String clientId, String clave) {

            PersonaEntity persona = PersonaEntity.builder()
                    .direccion(personaBean.getDireccion())
                    .identificacion(personaBean.getIdentificacion())
                    .nombre(personaBean.getNombre())
                    .edad(personaBean.getEdad())
                    .telefono(personaBean.getTelefono())
                    .genero(personaBean.getGenero())
                    .build();

            ClienteEntity cliente = ClienteEntity.builder().clienteId(clientId)
                .clave(clave)
                .estado(true)
                .personaEntity(persona)
                .build();

        try{
                ClienteEntity result = this.clienteRepository.save(cliente);
                return new ResponseDto<>(CommonErrors.CREATED.getMensaje(), CommonErrors.CREATED.getCodigo(), result);
            }catch (Exception e){
            logger.info("save request fail:  {}", e.getMessage());
                return new ResponseDto<>(CommonErrors.BAD_REQUEST.getMensaje(), CommonErrors.BAD_REQUEST.getCodigo());

            }
    }
}
