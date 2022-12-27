package com.project.controlfood.interfaces.http.controller;

import com.project.controlfood.application.product.CreateProduct;
import com.project.controlfood.application.product.FindProducts;
import com.project.controlfood.domain.entity.ProductPage;
import com.project.controlfood.interfaces.http.dto.CreateProductDTO;
import com.project.controlfood.interfaces.http.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final CreateProduct createProduct;
    private final FindProducts findProducts;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateProductDTO createProductDTO) {
        Long savedId = createProduct.invoke(createProductDTO.toEntity());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int limit
    ) {
        ProductPage productPage = findProducts.invoke(page, limit);
        log.info("Page: {} Total: {}", productPage.getPage(), productPage.getTotal());
        List<ProductDTO> productDTOList = productPage.getProducts().stream().map(ProductDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(productDTOList);
    }

}
