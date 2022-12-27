package com.project.controlfood.application.product;

import com.project.controlfood.domain.entity.ProductPage;
import com.project.controlfood.domain.ports.IFindProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FindProducts {

    private IFindProductRepository findProductRepository;

    public ProductPage invoke(int page, int limit) {
        return findProductRepository.findProduct(page, limit);
    }

}
