package com.project.controlfood.infra.database;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.entity.ProductPage;
import com.project.controlfood.domain.exception.InternalErrorException;
import com.project.controlfood.domain.ports.ICreateProductRepository;
import com.project.controlfood.domain.ports.IFindProductRepository;
import com.project.controlfood.infra.database.jpa.ProductRepositoryJPA;
import com.project.controlfood.infra.database.mapper.ProductModelMapper;
import com.project.controlfood.infra.database.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class ProductRepository implements ICreateProductRepository, IFindProductRepository {

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
            Page<ProductModel> pageResult = this.productRepositoryJPA.findAll(pageRequest);
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

}
