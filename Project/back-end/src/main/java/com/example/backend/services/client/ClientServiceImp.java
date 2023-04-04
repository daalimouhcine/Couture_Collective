package com.example.backend.services.client;

import com.example.backend.dto.ClientDto;
import com.example.backend.entities.ClientEntity;
import com.example.backend.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    private List<ClientDto> entityToDto(List<ClientEntity> clientEntities) {
        List<ClientDto> clientDtos = new ArrayList<>();
        for(ClientEntity clientEntity : clientEntities) {
            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(clientEntity, clientDto);
            clientDtos.add(clientDto);
        }
        return clientDtos;
    }

    @Override
    public List<ClientDto> getAllClients() {
        List<ClientDto> clientDtos;
        List<ClientEntity> clientEntities = (List<ClientEntity>) clientRepository.findAll();
        clientDtos = entityToDto(clientEntities);
        return clientDtos;
    }

    @Override
    public Boolean addClient(ClientDto clientDto) {
        ClientEntity clientEntity = new ClientEntity();
        BeanUtils.copyProperties(clientDto, clientEntity);
        try {
            clientRepository.save(clientEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ClientDto> getAllClientsByTailorId(Long id) {
        List<ClientDto> clientDtos;
        List<ClientEntity> clientEntities = clientRepository.findAllByTailorId(id);
        clientDtos = entityToDto(clientEntities);
        return clientDtos;
    }

    @Override
    public ClientDto findClientById(Long id) {
        return clientRepository.findById(id).map(clientEntity -> {
            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(clientEntity, clientDto);
            return clientDto;
        }).orElse(null);
    }

    @Override
    public Boolean updateClient(Long clientId, ClientDto clientDto) {
        ClientEntity clientEntity = clientRepository.findById(clientId).isPresent() ? clientRepository.findById(clientId).get() : null;
        if(clientEntity != null) {
            BeanUtils.copyProperties(clientDto, clientEntity);
            try {
                clientRepository.save(clientEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteClient(Long id) {
        try {
            clientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
