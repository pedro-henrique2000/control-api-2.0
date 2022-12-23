package com.project.controlfood.application;

import com.project.controlfood.application.product.CreateProduct;
import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.ports.ICreateProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductTest {

    @InjectMocks
    private CreateProduct createProduct;

    @Mock
    private ICreateProductRepository createProductRepository;

    @Test
    void shouldCallCreateProductRepositoryWithCorrectParam() {
        Product product = mock(Product.class);
        Product savedProduct = mock(Product.class);
        when(createProductRepository.create(product)).thenReturn(savedProduct);
        createProduct.invoke(product);
        verify(createProductRepository, times(1)).create(product);
    }

    @Test
    void shouldReturnSavedId() {
        Product product = mock(Product.class);
        Product savedProduct = mock(Product.class);
        when(createProductRepository.create(product)).thenReturn(savedProduct);
        when(savedProduct.getId()).thenReturn(1L);

        Long id = createProduct.invoke(product);

        assertEquals(1L, id);
    }

}
