package com.example.bankApp.controller;

import com.example.bankApp.dto.ClientForManagerDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Client;
import com.example.bankApp.service.ClientService;
import com.example.bankApp.util.ErrorMessage;
import com.example.bankApp.validation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class ClientController {
    private final ClientService clientservice;

    @GetMapping("/user/client/id")
    public ResponseEntity<Client> findById(@RequestBody UUID id) {
        return clientservice.findById(id);
    }

    @GetMapping("/admin/all/short")
    public ResponseEntity<List<ClientForManagerDto>> findAllShort() {
        return ResponseEntity.ok(clientservice.findAllShort());

    }

    @GetMapping("/admin/all/full")
    public ResponseEntity<List<Client>> findAllFull() {
        return clientservice.findAll();
    }

    //
    @GetMapping("/user/accounts")
    public ResponseEntity<List<Account>> findAccountsByClientId(@RequestBody UUID clientId) {
        return clientservice.findAccountsByClientId(clientId);
    }

    @PostMapping("/admin/search")
    public ResponseEntity<List<Client>> searchByParam(@RequestParam(required = false) String firstName,
                                                      @RequestParam(required = false) String lastName,
                                                      @RequestParam(required = false) String email) {
        return clientservice.findClientByParam(firstName, lastName, email);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<Client> add(@RequestBody @Valid Client client) {
        return new ResponseEntity<>(clientservice.add(client), HttpStatus.CREATED);
    }

    @PostMapping("/admin/update")
    public ResponseEntity<Client> updateClientByParam(@RequestBody UUID id,
                                                      @TaxCode @RequestParam(required = false) String taxCode,
                                                      @Name @RequestParam(required = false) String firstName,
                                                      @Name @RequestParam(required = false) String lastName,
                                                      @Email @RequestParam(required = false) String email,
                                                      @Address @RequestParam(required = false) String address,
                                                      @Phone @RequestParam(required = false) String phone) {

        clientservice.updateClientByParam(id, taxCode, firstName, lastName, email, address, phone);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/admin/delete")
    public ResponseEntity<String> delete(@RequestBody UUID id) {
        clientservice.deleteById(id);
        return new ResponseEntity<>(ErrorMessage.DELETE_BY_ID + id, HttpStatus.OK);
    }
}