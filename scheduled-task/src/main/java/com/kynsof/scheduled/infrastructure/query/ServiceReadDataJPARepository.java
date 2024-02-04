package com.kynsof.scheduled.infrastructure.query;

import com.kynsof.scheduled.infrastructure.entity.Service;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServiceReadDataJPARepository extends JpaRepository<Service, UUID>, JpaSpecificationExecutor<Service> {
    Page<Service> findAll(Specification specification, Pageable pageable);
}
