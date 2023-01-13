package com.project.controlfood.interfaces.http.controller;

import com.project.controlfood.application.product.*;
import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.entity.ProductPage;
import com.project.controlfood.interfaces.http.dto.CreateProductDTO;
import com.project.controlfood.interfaces.http.dto.ProductDTO;
import com.project.controlfood.interfaces.http.dto.UpdateProductDTO;
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

    private final FindProductById findProductById;
    private final CreateProduct createProduct;
    private final FindProducts findProducts;
    private final DeleteProduct deleteProduct;
    private final UpdateProduct updateProduct;

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

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> findProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int limit
    ) {
        ProductPage productPage = findProducts.invoke(page, limit);
        log.info("Page: {} Total: {}", productPage.getPage(), productPage.getTotal());
        List<ProductDTO> productDTOList = productPage.getProducts().stream().map(ProductDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(productDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(
            @PathVariable Long id
    ) {
        Product product = findProductById.invoke(id);
        return ResponseEntity.ok(ProductDTO.from(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id
    ) {
        deleteProduct.invoke(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @Valid @RequestBody UpdateProductDTO updateProductDTO,
            @PathVariable Long id
            ) {
        updateProduct.invoke(id, updateProductDTO.toEntity());
        return ResponseEntity.noContent().build();
    }

}
