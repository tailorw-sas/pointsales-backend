package com.kynsof.patients.infrastructure.repository.query;

import com.kynsof.patients.infrastructure.entity.GeographicLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface GeographicLocationReadDataJPARepository extends JpaRepository<GeographicLocation, UUID>, JpaSpecificationExecutor<GeographicLocation> {
    Page<GeographicLocation> findAll(Specification specification, Pageable pageable);
}
