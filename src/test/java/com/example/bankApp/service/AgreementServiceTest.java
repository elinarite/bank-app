package com.example.bankApp.service;

import com.example.bankApp.entity.Agreement;
import com.example.bankApp.repository.AgreementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgreementServiceTest {

    @Mock
    private AgreementRepository agreementRepository;

    @InjectMocks
    private AgreementService agreementService;

    @Mock
    private Agreement agreement;

    @BeforeEach
    public void setUp() {
        agreement = new Agreement();
        agreement.setId(1L);
    }

    @Test
    public void testFindByIdWhenAgreementExistsThenReturnsOkResponse() {
        // Arrange
        when(agreementRepository.findById(1L)).thenReturn(Optional.of(agreement));

        // Act
        ResponseEntity<Agreement> response = agreementService.findById(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(agreement, response.getBody());
        verify(agreementRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindByIdWhenAgreementDoesNotExistThenThrowsException() {
        // Arrange
        when(agreementRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> agreementService.findById(1L));
        verify(agreementRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindAllWhenRepositoryReturnsNonEmptyListThenReturnList() {
        // Arrange
        Agreement agreement = new Agreement();
        List<Agreement> agreementList = Arrays.asList(agreement);
        when(agreementRepository.findAll()).thenReturn(agreementList);

        // Act
        ResponseEntity<List<Agreement>> response = agreementService.findAll();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(agreementList);
        verify(agreementRepository, times(1)).findAll();

    }

    @Test
    public void testFindAllWhenRepositoryReturnsEmptyListThenReturnNotFound() {
        // Arrange
        when(agreementRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Agreement>> response = agreementService.findAll();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(agreementRepository, times(1)).findAll();

    }

    @Test
    public void testAddWhenGivenAgreementThenSaveAndReturnAgreement() {
        // Arrange
        Agreement agreement = new Agreement();
        AgreementService agreementService = new AgreementService(agreementRepository);
        when(agreementRepository.save(agreement)).thenReturn(agreement);

        // Act
        Agreement result = agreementService.add(agreement);

        // Assert
        assertThat(result).isEqualTo(agreement);
        verify(agreementRepository, times(1)).save(agreement);

    }

    @Test
    public void testAddWhenGivenAgreementWithIdThenSetIdToNullAndSave() {
        // Arrange
        Agreement agreement = new Agreement();
        agreement.setId(1L);
        AgreementService agreementService = new AgreementService(agreementRepository);
        when(agreementRepository.save(agreement)).thenReturn(agreement);

        // Act
        Agreement result = agreementService.add(agreement);

        // Assert
        assertThat(result.getId()).isNull();
        verify(agreementRepository, times(1)).save(agreement);

    }
}