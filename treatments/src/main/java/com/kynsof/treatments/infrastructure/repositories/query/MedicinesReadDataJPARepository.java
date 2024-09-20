package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.Medicines;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface MedicinesReadDataJPARepository extends JpaRepository<Medicines, UUID>, JpaSpecificationExecutor<Medicines> {
    Page<Medicines> findAll(Specification specification, Pageable pageable);

    @Query("SELECT COUNT(b) FROM Medicines b WHERE b.name = :name AND b.id <> :id")
    Long countByNameAndNotId(@Param("name") String name, @Param("id") UUID id);
}
