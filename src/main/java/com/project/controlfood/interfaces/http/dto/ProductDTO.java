package com.project.controlfood.interfaces.http.dto;

import com.project.controlfood.domain.entity.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
    private List<TagDTO> tags;
    private StatusDTO status;

    public static ProductDTO from(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setTags(product.getTags().stream().map(tag -> TagDTO.valueOf(tag.name())).collect(Collectors.toList()));
        productDTO.setStatus(StatusDTO.valueOf(product.getStatus().name()));
        return productDTO;
    }

}
