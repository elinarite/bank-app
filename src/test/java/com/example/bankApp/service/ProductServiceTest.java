package com.example.bankApp.service;

import com.example.bankApp.entity.Product;
import com.example.bankApp.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setId(1L);
    }

    @Test
    public void testFindByIdWhenProductExistsThenReturnProduct() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<Product> response = productService.findById(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
        verify(productRepository, times(1)).findById(1L);

    }

    @Test
    public void testFindByIdWhenProductDoesNotExistThenThrowException() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> productService.findById(1L));
        verify(productRepository, times(1)).findById(1L);

    }
}