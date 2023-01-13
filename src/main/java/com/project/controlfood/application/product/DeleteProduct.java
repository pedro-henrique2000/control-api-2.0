package com.project.controlfood.application.product;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.ports.IDeleteProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DeleteProduct {

    private final FindProductById findProductById;
    private final IDeleteProductRepository deleteProductRepository;

    public void invoke(Long id) {
        log.info("Received product with id to delete #{}", id);
        Product product = findProductById.invoke(id);
        deleteProductRepository.delete(product);
        log.info("Deleted product with id #{}", id);
    }

}
