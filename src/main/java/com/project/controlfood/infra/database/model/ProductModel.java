package com.project.controlfood.infra.database.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @Column(nullable = false, length = 50)
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

    private int availableQuantity;

}
