package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TreatmentReadDataJPARepository extends JpaRepository<Treatment, UUID>, JpaSpecificationExecutor<Treatment> {
    Page<Treatment> findAll(Specification specification, Pageable pageable);
}
