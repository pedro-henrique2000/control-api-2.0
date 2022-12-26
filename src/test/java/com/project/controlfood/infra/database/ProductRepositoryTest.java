package com.project.controlfood.infra.database;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.exception.InternalErrorException;
import com.project.controlfood.infra.database.jpa.ProductRepositoryJPA;
import com.project.controlfood.infra.database.mapper.ProductModelMapper;
import com.project.controlfood.infra.database.model.ProductModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    private ProductRepository productRepository;

    @Mock
    private ProductRepositoryJPA productRepositoryJPA;

    @Mock
    private ProductModelMapper productModelMapper;

    @Test
    void shouldCallRepositoryWithCorrectValue() {
        Product product = mock(Product.class);
        Product mappedEntity = mock(Product.class);
        ProductModel mapped = mock(ProductModel.class);
        ProductModel saved = mock(ProductModel.class);
        when(productModelMapper.toModel(product)).thenReturn(mapped);
        when(productRepositoryJPA.save(mapped)).thenReturn(saved);
        when(productModelMapper.toEntity(saved)).thenReturn(mappedEntity);

        Product response = assertDoesNotThrow(() -> productRepository.create(product));

        assertEquals(mappedEntity, response);
    }

    @Test
    void shouldThrowInternalErrorExceptionWhenErrorOccurs() {
        Product product = mock(Product.class);
        when(productModelMapper.toModel(product)).thenThrow(new RuntimeException());

        InternalErrorException internalErrorException = assertThrows(InternalErrorException.class, () -> productRepository.create(product));

        assertEquals("error occurred while saving new product", internalErrorException.getMessage());
    }
}