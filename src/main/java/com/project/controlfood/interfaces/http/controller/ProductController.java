package com.project.controlfood.interfaces.http.controller;

import com.project.controlfood.application.product.CreateProduct;
import com.project.controlfood.interfaces.http.dto.CreateProductDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final CreateProduct createProduct;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateProductDTO createProductDTO) {
        Long savedId = createProduct.invoke(createProductDTO.toEntity());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
