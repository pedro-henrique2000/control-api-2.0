package com.project.controlfood.application.order;

import com.project.controlfood.application.product.FindProductById;
import com.project.controlfood.domain.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateOrder {

    private FindProductById findProductById;

    public void invoke(Long productId, int quantity) {
        Product product = findProductById.invoke(productId);

//        product.getDescription()

    }

}
