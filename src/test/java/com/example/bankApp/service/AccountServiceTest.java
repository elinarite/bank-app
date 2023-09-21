package com.example.bankApp.service;

import com.example.bankApp.dto.AccountForManagerDto;
import com.example.bankApp.entity.Account;
import com.example.bankApp.mapper.AccountMapper;
import com.example.bankApp.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Mock
    private Account account;

    @InjectMocks
    private AccountService accountService;


    @BeforeEach
    public void setUp() {
        accountService = new AccountService(accountRepository, accountMapper);
    }

    @Test
    public void testFindAllByCurrencyCodeWhenGivenCurrencyCodeThenReturnListOfAccountForManagerDto() {
        // Arrange
        Account account = new Account();
        AccountForManagerDto dto = new AccountForManagerDto();
        when(accountRepository.findByCurrencyCode("USD")).thenReturn(Arrays.asList(account));
        when(accountMapper.toAccountforManagerDto(account)).thenReturn(dto);

        // Act
        List<AccountForManagerDto> result = accountService.findAlLByCurrencyCode("USD");

        // Assert
        assertThat(result).containsExactly(dto);
    }

    @Test
    public void testFindAllByCurrencyCodeWhenNoAccountsThenReturnEmptyList() {
        // Arrange
        when(accountRepository.findByCurrencyCode("USD")).thenReturn(Collections.emptyList());

        // Act
        List<AccountForManagerDto> result = accountService.findAlLByCurrencyCode("USD");

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    public void testFindByIdWhenIdDoesNotExistThenThrowException() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(accountRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> accountService.findById(nonExistentId))
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void testFindByIdForTransactionWhenIdIsProvidedThenRepositoryMethodIsCalled() {
        // Arrange
        UUID id = UUID.randomUUID();
        Account account = new Account();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        // Act
        accountService.findByIdForTransaction(id);

        // Assert
        verify(accountRepository).findById(id);
    }

    @Test
    public void testFindByIdForTransactionWhenIdIsProvidedThenReturnsCorrectOptionalAccount() {
        // Arrange
        UUID id = UUID.randomUUID();
        Account account = new Account();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));

        // Act
        Optional<Account> result = accountService.findByIdForTransaction(id);

        // Assert
        assertEquals(Optional.of(account), result);
    }
}