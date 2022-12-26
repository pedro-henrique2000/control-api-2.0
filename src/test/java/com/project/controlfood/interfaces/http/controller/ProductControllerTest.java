package com.project.controlfood.interfaces.http.controller;

import com.project.controlfood.application.product.CreateProduct;
import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.interfaces.http.dto.CreateProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private CreateProduct createProduct;

    @Test
    void shouldReturn201OnSuccess() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        CreateProductDTO createProductDTO = mock(CreateProductDTO.class);
        Product product = mock(Product.class);
        when(createProductDTO.toEntity()).thenReturn(product);

        ResponseEntity<Void> responseEntity = this.productController.create(createProductDTO);

        assertEquals(201, responseEntity.getStatusCodeValue());
    }

}