package com.example.backend.services.client;

import com.example.backend.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();
    ClientDto addClient(ClientDto clientDto);
    ClientDto findClientByEmail(String email);
    ClientDto findClientById(Long id);
    ClientDto updateClient(ClientDto clientDto);
    Boolean deleteClient(Long id);

    List<ClientDto> getAllClientsByTailorId(Long id);

}
