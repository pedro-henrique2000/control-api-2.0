package com.project.controlfood.domain.ports;

import com.project.controlfood.domain.entity.Product;

public interface IFindProductByIdRepository {
    Product findById(Long id);
}
