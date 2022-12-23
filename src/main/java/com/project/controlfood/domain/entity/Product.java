package com.project.controlfood.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Product {

    private Long id;
    private String name;
    private String description;
    private double price;
    private List<Tag> tags;
    private Status status;

}
