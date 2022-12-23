package com.project.controlfood.application.product;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.ports.ICreateProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateProduct {

    private final ICreateProductRepository createProductRepository;

    public Long invoke(Product product) {
        Product savedProduct = this.createProductRepository.create(product);
        return savedProduct.getId();
    }

}
