package com.project.controlfood.application.product;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.ports.IUpdateProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UpdateProduct {

    private final FindProductById findProductById;
    private final IUpdateProductRepository updateProductRepository;

    public void invoke(Long id, Product product) {
        Product oldProduct = findProductById.invoke(id);

        updateProductRepository.update(getNewProduct(product, oldProduct));

    }

    private static Product getNewProduct(Product product, Product oldProduct) {
        return Product.builder()
                .id(oldProduct.getId())
                .name(product.getName() == null ? oldProduct.getName() : product.getName())
                .description(product.getDescription() == null ? oldProduct.getDescription() : product.getDescription())
                .price(product.getPrice() == null ? oldProduct.getPrice() : product.getPrice())
                .tags(product.getTags())
                .status(product.getStatus())
                .build();
    }


}
