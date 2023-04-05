package com.example.backend.controllers;

import com.example.backend.dto.ClientDto;
import com.example.backend.dto.TailorDto;
import com.example.backend.requests.ClientRequest;
import com.example.backend.responses.ClientResponse;
import com.example.backend.responses.SimpleResponse;
import com.example.backend.services.client.ClientService;
import com.example.backend.services.tailor.TailorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private TailorService tailorService;

    private List<ClientResponse> dtoToResponse(List<ClientDto> clientDtos) {
        List<ClientResponse> clientResponses = new ArrayList<>();
        for(ClientDto clientDto : clientDtos) {
            ClientResponse clientResponse = new ClientResponse();
            BeanUtils.copyProperties(clientDto, clientResponse);
            clientResponses.add(clientResponse);
        }
        return clientResponses;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<ClientDto> clientDtos = clientService.getAllClients();
        List<ClientResponse> clientResponses = dtoToResponse(clientDtos);
        return ResponseEntity.ok(clientResponses);
    }

    @GetMapping("/byId/{clientId}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable Long clientId) {
        ClientDto clientDto = clientService.findClientById(clientId);
        ClientResponse clientResponse = new ClientResponse();
        BeanUtils.copyProperties(clientDto, clientResponse);
        return ResponseEntity.ok(clientResponse);
    }

    @GetMapping("/byTailor/{tailorId}")
    public ResponseEntity<List<ClientResponse>> getAllClientsByTailorId(@PathVariable Long tailorId) {
        List<ClientDto> clientDtos = clientService.getAllClientsByTailorId(tailorId);
        List<ClientResponse> clientResponses = dtoToResponse(clientDtos);
        return ResponseEntity.ok(clientResponses);
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleResponse> createClient(@RequestBody ClientRequest clientRequest) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(clientRequest, clientDto);

        TailorDto tailorDto = tailorService.findTailorByEmail(clientRequest.getTailorEmail());
        clientDto.setTailorDto(tailorDto);

        Boolean creatClientStatus = clientService.addClient(clientDto);
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage(creatClientStatus ? "Client created successfully" : "Client creation failed");
        simpleResponse.setSuccess(creatClientStatus);
        return ResponseEntity.ok(simpleResponse);
    }

    @PutMapping("/update/{clientId}")
    public ResponseEntity<SimpleResponse> updateClient(@PathVariable Long clientId, @RequestBody ClientRequest clientRequest) {
        ClientDto clientDto = new ClientDto();
        BeanUtils.copyProperties(clientRequest, clientDto);
        Boolean updatedClient = clientService.updateClient(clientId, clientDto);
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage(updatedClient ? "Client updated successfully" : "Client updation failed");
        simpleResponse.setSuccess(updatedClient);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<SimpleResponse> deleteClient(@PathVariable Long clientId) {
        Boolean deletedClient = clientService.deleteClient(clientId);
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage(deletedClient ? "Client deleted successfully" : "Client deletion failed");
        simpleResponse.setSuccess(deletedClient);
        return ResponseEntity.ok(simpleResponse);
    }

}
