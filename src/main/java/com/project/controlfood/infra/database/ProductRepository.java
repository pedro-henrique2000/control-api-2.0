package com.project.controlfood.infra.database;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.entity.ProductPage;
import com.project.controlfood.domain.exception.InternalErrorException;
import com.project.controlfood.domain.ports.*;
import com.project.controlfood.infra.database.jpa.ProductRepositoryJPA;
import com.project.controlfood.infra.database.mapper.ProductModelMapper;
import com.project.controlfood.infra.database.model.ProductModel;
import com.project.controlfood.infra.database.model.StatusModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class ProductRepository implements
        ICreateProductRepository,
        IFindProductRepository,
        IFindProductByIdRepository,
        IDeleteProductRepository,
        IUpdateProductRepository {

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

    @Override
    public ProductPage findProduct(int page, int limit) {
        try {
            PageRequest pageRequest = PageRequest.of(page - 1, limit);
            Page<ProductModel> pageResult = this.productRepositoryJPA.findByStatus(StatusModel.ACTIVE, pageRequest);
            log.info("found {} products", pageResult.getNumberOfElements());
            return new ProductPage(
                    pageResult.getContent().stream().map(productModelMapper::toEntity).collect(Collectors.toList()),
                    page,
                    pageResult.getTotalElements()
            );
        } catch (Exception exception) {
            throw new InternalErrorException("error occurred while querying products");
        }
    }

    @Override
    public Product findById(Long id) {
        try {
            Optional<ProductModel> productModelOptional = this.productRepositoryJPA.findById(id);
            if (productModelOptional.isEmpty())
                return null;
            return this.productModelMapper.toEntity(productModelOptional.get());
        } catch (Exception exception) {
            throw new InternalErrorException("error occurred while querying product with id " + id);
        }
    }

    @Override
    public void delete(Product product) {
        try {
            productRepositoryJPA.delete(productModelMapper.toModel(product));
        } catch (Exception exception) {
            throw new InternalErrorException("error occurred while deleting product with id " + product.getId());
        }
    }

    @Override
    public Product update(Product product) {
        try {
            return this.create(product);
        } catch (Exception exception) {
            throw new InternalErrorException("error occurred while updating product with id " + product.getId());
        }
    }
}
