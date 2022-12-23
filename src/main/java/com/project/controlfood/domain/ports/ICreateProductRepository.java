package com.project.controlfood.domain.ports;

import com.project.controlfood.domain.entity.Product;

public interface ICreateProductRepository {

    Product create(Product product);

}
