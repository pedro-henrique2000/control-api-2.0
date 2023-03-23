package com.project.controlfood.infra.database.jpa;

import com.project.controlfood.infra.database.model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepositoryJPA extends JpaRepository<ProductModel, Long> {
    @Query(value = "select * from product where status = 'ACTIVE'", nativeQuery = true, countQuery = "select count(*) from product where status = 'ACTIVE'")
    Page<ProductModel> findAllActive(Pageable pageable);
}
