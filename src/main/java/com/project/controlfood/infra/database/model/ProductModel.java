package com.project.controlfood.infra.database.model;

import com.project.controlfood.domain.entity.Product;
import com.project.controlfood.domain.entity.Status;
import com.project.controlfood.domain.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ProductTag", joinColumns = @JoinColumn(name = "product_id"))
    private List<TagModel> tags;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusModel status;

    public static ProductModel toModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        productModel.setPrice(product.getPrice());
        productModel.setDescription(product.getDescription());
        productModel.setTags(product.getTags().stream().map(tag -> TagModel.valueOf(tag.name())).collect(Collectors.toList()));
        productModel.setStatus(StatusModel.valueOf(product.getStatus().name()));
        return productModel;
    }

    public Product toEntity() {
        return Product.builder()
                .id(this.id)
                .description(this.description)
                .status(Status.valueOf(this.status.name()))
                .price(this.price)
                .tags(this.tags.stream().map(tag -> Tag.valueOf(tag.name())).collect(Collectors.toList()))
                .name(this.name)
                .build();
    }

}
