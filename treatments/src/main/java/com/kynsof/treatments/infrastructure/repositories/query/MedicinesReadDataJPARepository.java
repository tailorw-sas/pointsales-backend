package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.Medicines;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface MedicinesReadDataJPARepository extends JpaRepository<Medicines, UUID>, JpaSpecificationExecutor<Medicines> {
    Page<Medicines> findAll(Specification specification, Pageable pageable);
}
