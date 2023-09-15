package com.example.bankApp.controller;

import com.example.bankApp.dto.ManagerForClientDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.entity.Manager;
import com.example.bankApp.service.ManagerService;
import com.example.bankApp.validation.Email;
import com.example.bankApp.validation.Name;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;



    @GetMapping("/get/{id}")
    public ResponseEntity<Manager> getById(@PathVariable Long id){
        return managerService.findById(id);
    }

    @GetMapping("/id")
    public ResponseEntity<Manager> findById(@RequestBody Long id) {
        return managerService.findById(id);
    }

        @GetMapping("/all/short")
    public ResponseEntity<List<ManagerForClientDto>> findAllShort() {
        return ResponseEntity.ok(managerService.findAllShort());

    }

    @GetMapping("/all/full")
    public ResponseEntity<List<Manager>> findAllFull() {
        return managerService.findAll();
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> findAccountsByManagerId(@RequestBody Long managerId) {
        return managerService.findAccountsByManagerId(managerId);
    }


    /**
     * this method is used to display information about managers (manager id, name, Surname) and the number of clients
     */
    @GetMapping("/count-account")
    public ResponseEntity<List<Object[]>> findByManagerWithCountAccounts() {
        return managerService.findByManagerWithCountAccounts();
    }

    @GetMapping("/all-active")
    public ResponseEntity<List<Manager>> findAllActiveManagers() {
        return managerService.findAllActiveManagers();

    }

    @GetMapping("/all-inactive")
    public ResponseEntity<List<Manager>> findAllInactiveManagers() {
        return managerService.findAllInactiveManagers();
    }

    @PostMapping("/search/name")
    public ResponseEntity<List<Manager>> searchByName(@RequestParam(required = false) String firstName,
                                                      @RequestParam(required = false) String lastName) {
        return managerService.findManagerByParam(firstName, lastName);
    }

    @PostMapping("/add")
    public ResponseEntity<Manager> add (@RequestBody @Valid Manager manager){
        return new ResponseEntity<>(managerService.add(manager), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Manager> updateManagerByParam(@PathVariable Long id,
                                                        @Name @RequestParam(required = false) String firstName,
                                          @Name @RequestParam(required = false) String lastName,
                                          @Email @RequestParam(required = false) String email,
                                          @RequestParam(required = false) boolean isActive){

        managerService.updateManagerByParam(id, firstName, lastName, email, isActive );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}