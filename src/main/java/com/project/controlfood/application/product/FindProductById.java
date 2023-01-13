package com.project.controlfood.application.product;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.exception.NotFoundException;
import com.project.controlfood.domain.ports.IFindProductByIdRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class FindProductById {

    private final IFindProductByIdRepository findProductRepository;

    public Product invoke(Long id) {
        log.info("Searching for product with id #{}", id);
        Product product = findProductRepository.findById(id);
        if (Objects.isNull(product))
            throw new NotFoundException("not found product with id " + id);
        log.info("Found product with id #{}", product.getId());
        return product;
    }

}
