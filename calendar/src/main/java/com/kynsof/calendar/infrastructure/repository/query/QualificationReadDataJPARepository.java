package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.Qualification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface QualificationReadDataJPARepository extends JpaRepository<Qualification, UUID>, JpaSpecificationExecutor<Qualification> {
    Page<Qualification> findAll(Specification specification, Pageable pageable);
    Long countByDescription(String description);
}
