package com.kynsof.store.infrastructure.repositories.queries;

import com.kynsof.store.infrastructure.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CategoryReadDataJPARepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {
    Page<Category> findAll(Specification specification, Pageable pageable);
}
