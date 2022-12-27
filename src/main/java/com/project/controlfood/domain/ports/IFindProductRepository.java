package com.project.controlfood.domain.ports;

import com.project.controlfood.domain.entity.ProductPage;

public interface IFindProductRepository {

    ProductPage findProduct(int page, int limit);

}
