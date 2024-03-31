package com.kynsof.store.infrastructure.repositories.queries;

import com.kynsof.store.infrastructure.entity.InventoryMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface InventoryReadDataJPARepository extends JpaRepository<InventoryMovement, UUID>, JpaSpecificationExecutor<InventoryMovement> {
    Page<InventoryMovement> findAll(Specification specification, Pageable pageable);
}