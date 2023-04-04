package com.example.backend.services.client;

import com.example.backend.dto.ClientDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();
    Boolean addClient(ClientDto clientDto);
    ClientDto findClientById(Long id);
    Boolean updateClient(Long clientId, ClientDto clientDto);
    Boolean deleteClient(Long id);

    List<ClientDto> getAllClientsByTailorId(Long id);

}
