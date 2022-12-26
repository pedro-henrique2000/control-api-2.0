package com.project.controlfood.interfaces.http.dto;


import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.entity.Status;
import com.project.controlfood.domain.entity.Tag;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreateProductDTO {

    @NotBlank
    private String name;

    @NotEmpty
    private String description;

    @Positive
    private double price;

    @NotEmpty
    private List<String> tags;

    @NotBlank
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
