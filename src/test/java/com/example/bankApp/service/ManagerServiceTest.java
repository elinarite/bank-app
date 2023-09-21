package com.example.bankApp.service;

import com.example.bankApp.entity.Manager;
import com.example.bankApp.repository.ManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManagerServiceTest {

    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private ManagerService managerService;

    @Mock
    private Manager manager1;

    @Mock
    private Manager manager2;

    @BeforeEach
    public void setUp() {
        manager1 = new Manager();
        manager1.setId(1L);
        manager1.setFirstName("John");
        manager1.setLastName("Doe");
        manager1.setEmail("john.doe@example.com");
        manager1.setActive(true);

        manager2 = new Manager();
        manager2.setId(2L);
        manager2.setFirstName("Jane");
        manager2.setLastName("Doe");
        manager2.setEmail("jane.doe@example.com");
        manager2.setActive(true);
    }

    @Test
    public void testFindAllWhenManagersExistThenReturnAll() {
        // Arrange
        List<Manager> expectedManagers = Arrays.asList(manager1, manager2);
        when(managerRepository.findAll()).thenReturn(expectedManagers);

        // Act
        ResponseEntity<List<Manager>> responseEntity = managerService.findAll();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedManagers, responseEntity.getBody());
        verify(managerRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllWhenNoManagersExistThenReturnNotFound() {
        // Arrange
        when(managerRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Manager>> responseEntity = managerService.findAll();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(managerRepository, times(1)).findAll();

    }

    @Test
    public void testFindAllInactiveManagersWhenRepositoryReturnsNonEmptyListThenReturnList() {
        // Arrange
        Manager manager1 = new Manager();
        Manager manager2 = new Manager();
        List<Manager> managers = Arrays.asList(manager1, manager2);
        when(managerRepository.findAllInactiveManagers()).thenReturn(managers);

        // Act
        ResponseEntity<List<Manager>> response = managerService.findAllInactiveManagers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(managers, response.getBody());
        verify(managerRepository, times(1)).findAllInactiveManagers();

    }

    @Test
    public void testFindAllInactiveManagersWhenRepositoryReturnsEmptyListThenReturnNotFound() {
        // Arrange
        when(managerRepository.findAllInactiveManagers()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Manager>> response = managerService.findAllInactiveManagers();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(managerRepository, times(1)).findAllInactiveManagers();


    }

    @Test
    public void testFindAllWhenRepositoryReturnsManagersThenReturnSameList() {
        // Arrange
        List<Manager> expectedManagers = Arrays.asList(manager1, manager2);
        when(managerRepository.findAll()).thenReturn(expectedManagers);

        // Act
        ResponseEntity<List<Manager>> responseEntity = managerService.findAll();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedManagers, responseEntity.getBody());
        verify(managerRepository, times(1)).findAll();

    }

    @Test
    public void testFindAllWhenRepositoryReturnsEmptyThenReturnNotFound() {
        // Arrange
        when(managerRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Manager>> responseEntity = managerService.findAll();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}