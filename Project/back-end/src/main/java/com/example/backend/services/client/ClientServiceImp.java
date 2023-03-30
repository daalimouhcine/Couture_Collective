package com.example.backend.services.client;

import com.example.backend.dto.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImp implements ClientService{
    @Override
    public List<ClientDto> getAllClients() {
        return null;
    }

    @Override
    public ClientDto addClient(ClientDto clientDto) {
        return null;
    }

    @Override
    public ClientDto findClientByEmail(String email) {
        return null;
    }

    @Override
    public ClientDto findClientById(Long id) {
        return null;
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        return null;
    }

    @Override
    public Boolean deleteClient(Long id) {
        return null;
    }

    @Override
    public List<ClientDto> getAllClientsByTailorId(Long id) {
        return null;
    }
}
