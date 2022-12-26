package com.project.controlfood.infra.database;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.exception.InternalErrorException;
import com.project.controlfood.domain.ports.ICreateProductRepository;
import com.project.controlfood.infra.database.jpa.ProductRepositoryJPA;
import com.project.controlfood.infra.database.mapper.ProductModelMapper;
import com.project.controlfood.infra.database.model.ProductModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductRepository implements ICreateProductRepository {

    private final ProductRepositoryJPA productRepositoryJPA;
    private final ProductModelMapper productModelMapper;

    @Override
    public Product create(Product product) {
        try {
            ProductModel productModel = productModelMapper.toModel(product);
            ProductModel save = this.productRepositoryJPA.save(productModel);
            return productModelMapper.toEntity(save);
        } catch (Exception exception) {
            throw new InternalErrorException("error occurred while saving new product");
        }
    }

}
