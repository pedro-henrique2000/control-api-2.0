package com.project.controlfood.interfaces.http.dto;


import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.entity.Status;
import com.project.controlfood.domain.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateProductDTO {

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotEmpty
    @Size(max = 255)
    private String description;

    @Positive
    private double price;

    private List<@NotNull TagDTO> tags;

    @Positive
    private int availableQuantity;

    @NotNull
    private StatusDTO status;

    public Product toEntity() {
        return Product.builder()
                .description(this.description)
                .status(Status.valueOf(this.status.name()))
                .price(this.price)
                .tags(this.tags.stream().map(tag -> Tag.valueOf(tag.name())).collect(Collectors.toList()))
                .name(this.name)
                .availableQuantity(this.availableQuantity)
                .build();
    }

}
