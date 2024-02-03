package com.kynsof.scheduled.infrastructure.query;

import com.kynsof.scheduled.infrastructure.entity.Business;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BusinessReadDataJPARepository extends JpaRepository<Business, UUID>, JpaSpecificationExecutor<Business> {
    Page<Business> findAll(Specification specification, Pageable pageable);
}
