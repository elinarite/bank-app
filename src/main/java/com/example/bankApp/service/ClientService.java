package com.example.bankApp.service;

import com.example.bankApp.dto.ClientForManagerDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Client;
import com.example.bankApp.mapper.ClientMapper;
import com.example.bankApp.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ResponseEntity<Client> findById(UUID id) {
        Client client = clientRepository.findById(id).get();
        return ResponseEntity.ok(client);
    }

    public List<ClientForManagerDto> findAllShort() {
        List<Client> allClients = clientRepository.findAll();
        return allClients.stream()
                .map(clientMapper::toClientforManagerDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<List<Client>> findAll() {
        List<Client> allClients = clientRepository.findAll();
        if (allClients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(allClients);
    }

    public ResponseEntity<List<Account>> findAccountsByClientId(UUID clientId) {
        List<Account> accounts = clientRepository.findAccountsByClientId(clientId);
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(accounts);
    }

    public ResponseEntity<List<Client>> findClientByParam(String firstName, String lastName, String email) {
        List<Client> clientsByParam = clientRepository.findClientByParam(firstName, lastName, email);
        if (clientsByParam.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(clientsByParam);
    }

    @Transactional
    public Client add(Client client) {
        client.setId(null);
        return clientRepository.save(client);
    }

    @Transactional
    public void updateClientByParam(UUID id, String taxCode, String firstName, String lastName, String email, String address, String phone) {
        clientRepository.updateClientByParam(id, taxCode, firstName, lastName, email, address, phone);
    }

    @Transactional
    public void deleteById(UUID id) {
        clientRepository.deleteById(id);
    }
}