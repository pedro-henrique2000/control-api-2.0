package com.project.controlfood.domain.ports;

import com.project.controlfood.domain.entity.Product;

public interface IUpdateProductRepository {

    public Product update(Product product);

}
