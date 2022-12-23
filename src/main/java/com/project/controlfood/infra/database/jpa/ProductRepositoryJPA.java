package com.project.controlfood.infra.database.jpa;

import com.project.controlfood.infra.database.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryJPA extends JpaRepository<ProductModel, Long> {
}
