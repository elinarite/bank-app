package com.example.bankApp.controller;

import com.example.bankApp.dto.ManagerForClientDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Manager;
import com.example.bankApp.service.ManagerService;
import com.example.bankApp.util.ErrorMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/manager-account")
public class ManagerController {
    private final ManagerService managerService;


    @GetMapping("/manager/get/{id}")
    public ResponseEntity<Manager> getById(@PathVariable Long id) {
        return managerService.findById(id);
    }

    @GetMapping("/manager/id")
    public ResponseEntity<Manager> findById(@RequestBody Long id) {
        return managerService.findById(id);
    }

    @GetMapping("/user/all/short")
    public ResponseEntity<List<ManagerForClientDto>> findAllShort() {
        return ResponseEntity.ok(managerService.findAllShort());

    }

    @GetMapping("/manager/all/full")
    public ResponseEntity<List<Manager>> findAllFull() {
        return managerService.findAll();
    }

    @GetMapping("/manager/accounts")
    public ResponseEntity<List<Account>> findAccountsByManagerId(@RequestBody Long managerId) {
        return managerService.findAccountsByManagerId(managerId);
    }

    /**
     * this method is used to display information about managers (manager id, name, Surname) and the number of clients
     */
    @GetMapping("/manager/count-account")
    public ResponseEntity<List<Object[]>> findByManagerWithCountAccounts() {
        return managerService.findByManagerWithCountAccounts();
    }

    @GetMapping("/manager/all-active")
    public ResponseEntity<List<Manager>> findAllActiveManagers() {
        return managerService.findAllActiveManagers();

    }

    @GetMapping("/manager/all-inactive")
    public ResponseEntity<List<Manager>> findAllInactiveManagers() {
        return managerService.findAllInactiveManagers();
    }

    @PostMapping("/manager/search/name")
    public ResponseEntity<List<Manager>> searchByName(@RequestParam(required = false) String firstName,
                                                      @RequestParam(required = false) String lastName) {
        return managerService.findManagerByParam(firstName, lastName);
    }

    @PutMapping("/manager/add")
    public ResponseEntity<Manager> add(@RequestBody @Valid Manager manager) {
        return new ResponseEntity<>(managerService.add(manager), HttpStatus.CREATED);
    }

    //todo validation param and test
    @PutMapping("/manager/{id}/update")
    public ResponseEntity<Manager> updateManagerByParam(@PathVariable Long id,
                                                        @RequestParam(required = false) String firstName,
                                                        @RequestParam(required = false) String lastName,
                                                        @RequestParam(required = false) String email,
                                                        @RequestParam(required = false) boolean isActive) {

        managerService.updateManagerByParam(id, firstName, lastName, email, isActive);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/manager/delete/id")
    public ResponseEntity<String> delete(@RequestBody Long id) {
        managerService.deleteById(id);
        return new ResponseEntity<>(ErrorMessage.DELETE_BY_ID + id, HttpStatus.OK);
    }
}