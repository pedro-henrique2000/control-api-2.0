package com.project.controlfood.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductPage {
    private List<Product> products;
    private int page;
    private long total;
}
