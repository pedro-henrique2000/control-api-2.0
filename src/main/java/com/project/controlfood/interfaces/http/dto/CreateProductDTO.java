package com.project.controlfood.interfaces.http.dto;


import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.entity.Status;
import com.project.controlfood.domain.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateProductDTO {

    private String name;
    private String description;
    private double price;
    private List<String> tags;
    private String status;

    public Product toEntity() {
        return Product.builder()
                .description(this.description)
                .status(Status.valueOf(this.status))
                .price(this.price)
                .tags(this.tags.stream().map(Tag::valueOf).collect(Collectors.toList()))
                .name(this.name)
                .build();
    }

}
