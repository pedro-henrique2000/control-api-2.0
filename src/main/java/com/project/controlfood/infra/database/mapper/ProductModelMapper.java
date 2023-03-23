package com.project.controlfood.infra.database.mapper;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.entity.Status;
import com.project.controlfood.domain.entity.Tag;
import com.project.controlfood.infra.database.model.ProductModel;
import com.project.controlfood.infra.database.model.StatusModel;
import com.project.controlfood.infra.database.model.TagModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductModelMapper {

    public ProductModel toModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setPrice(product.getPrice());
        productModel.setDescription(product.getDescription());
        productModel.setTags(product.getTags().stream().map(tag -> TagModel.valueOf(tag.name())).collect(Collectors.toList()));
        productModel.setStatus(StatusModel.valueOf(product.getStatus().name()));
        productModel.setAvailableQuantity(product.getAvailableQuantity());
        return productModel;
    }

    public Product toEntity(ProductModel productModel) {
        return Product.builder()
                .id(productModel.getId())
                .description(productModel.getDescription())
                .status(Status.valueOf(productModel.getStatus().name()))
                .price(productModel.getPrice())
                .tags(productModel.getTags().stream().map(tag -> Tag.valueOf(tag.name())).collect(Collectors.toList()))
                .name(productModel.getName())
                .availableQuantity(productModel.getAvailableQuantity())
                .build();
    }

}
