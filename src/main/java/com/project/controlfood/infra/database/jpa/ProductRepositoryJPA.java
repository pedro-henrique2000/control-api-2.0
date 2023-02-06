package com.project.controlfood.infra.database.jpa;

import com.project.controlfood.infra.database.model.ProductModel;
import com.project.controlfood.infra.database.model.StatusModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryJPA extends JpaRepository<ProductModel, Long> {
    Page<ProductModel> findByStatus(StatusModel status, Pageable pageable);
}
