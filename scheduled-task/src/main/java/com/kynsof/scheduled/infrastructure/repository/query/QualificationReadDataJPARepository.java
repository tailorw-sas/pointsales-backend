package com.kynsof.scheduled.infrastructure.repository.query;

import com.kynsof.scheduled.infrastructure.entity.Qualification;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QualificationReadDataJPARepository extends JpaRepository<Qualification, UUID>, JpaSpecificationExecutor<Qualification> {
    Page<Qualification> findAll(Specification specification, Pageable pageable);
}
