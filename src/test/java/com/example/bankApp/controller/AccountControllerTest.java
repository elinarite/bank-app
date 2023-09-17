//package com.example.bankApp.controller;
//
//import com.example.bankApp.dto.AccountForClientDto;
//import com.example.bankApp.service.AccountService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.UUID;
//
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(AccountController.class)
//public class AccountControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AccountService accountService;
//
//    private UUID ownerId;
//    private AccountForClientDto accountDto;
//
//    @BeforeEach
//    public void setUp() {
//        ownerId = UUID.randomUUID();
//        accountDto = new AccountForClientDto();
//        accountDto.setClientFirstName("John");
//        accountDto.setClientLastName("Doe");
//        accountDto.setCardAccountNumber("1234567890");
//        accountDto.setCurrencyCode("USD");
//        accountDto.setBalance(new BigDecimal("1000.00"));
//        accountDto.setManagerFirstName("Manager");
//        accountDto.setManagerLastName("Manager");
//    }
//
//    @Test
//    public void testGetAllByClientIdWhenOwnerIdIsValidThenReturnAccountListAndStatusOk() throws Exception {
//        Mockito.when(accountService.findAllByClientId(ownerId)).thenReturn(Arrays.asList(accountDto));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + ownerId + "/accounts"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json("[{\"clientFirstName\":\"John\",\"clientLastName\":\"Doe\",\"cardAccountNumber\":\"1234567890\",\"currencyCode\":\"USD\",\"balance\":1000.00,\"managerFirstName\":\"Manager\",\"managerLastName\":\"Manager\"}]"));
//
//        Mockito.verify(accountService, Mockito.times(1)).findAllByClientId(ownerId);
//    }
//
//    @Test
//    public void testGetAllByClientIdWhenOwnerIdDoesNotExistThenReturnStatusNotFound() throws Exception {
//        Mockito.when(accountService.findAllByClientId(ownerId)).thenReturn(null);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + ownerId + "/accounts"))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//
//        Mockito.verify(accountService, Mockito.times(1)).findAllByClientId(ownerId);
//    }
//
//    @BeforeEach
//    public void setup() {
//        ownerId = UUID.randomUUID();
//        accountDto = new AccountForClientDto();
//        Mockito.when(accountService.findAllByClientId(ownerId)).thenReturn(Collections.singletonList(accountDto));
//    }
//
//    @Test
//    public void testGetAllByClientIdWhenOwnerIdIsValidThenReturnsCorrectResponse() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + ownerId + "/accounts")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json("[{}]"));
//
//        Mockito.verify(accountService, Mockito.times(1)).findAllByClientId(ownerId);
//    }
//
//    @Test
//    public void testGetAllByClientIdWhenOwnerIdDoesNotExistThenReturnsNotFound() throws Exception {
//        UUID nonExistentOwnerId = UUID.randomUUID();
//        Mockito.when(accountService.findAllByClientId(nonExistentOwnerId)).thenReturn(Collections.emptyList());
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + nonExistentOwnerId + "/accounts")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//
//        Mockito.verify(accountService, Mockito.times(1)).findAllByClientId(nonExistentOwnerId);
//    }
//
//    @Test
//    public void testGetAllByClientIdWhenOwnerIdIsValidThenReturnsAccountList() throws Exception {
//        // Arrange
//        when(accountService.findAllByClientId(ownerId)).thenReturn(Arrays.asList(accountDto));
//
//        // Act & Assert
//        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + ownerId + "/accounts")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json("[{\"clientFirstName\":null,\"clientLastName\":null,\"cardAccountNumber\":null,\"currencyCode\":null,\"balance\":null,\"managerFirstName\":null,\"managerLastName\":null}]"));
//    }
//
//    @Test
//    public void testGetAllByClientIdWhenOwnerIdDoesNotExistThenReturnsEmptyList() throws Exception {
//        // Arrange
//        when(accountService.findAllByClientId(ownerId)).thenReturn(Collections.emptyList());
//
//        // Act & Assert
//        mockMvc.perform(MockMvcRequestBuilders.get("/account/" + ownerId + "/accounts")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json("[]"));
//    }
//}