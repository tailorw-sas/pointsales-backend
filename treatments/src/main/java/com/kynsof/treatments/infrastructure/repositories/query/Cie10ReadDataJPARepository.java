package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.Cie10;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface Cie10ReadDataJPARepository extends JpaRepository<Cie10, UUID>, JpaSpecificationExecutor<Cie10> {
    Page<Cie10> findAll(Specification specification, Pageable pageable);
    Cie10 findCie10ByCode(String code);
}
