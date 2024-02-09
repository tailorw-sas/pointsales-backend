package com.kynsof.scheduled.infrastructure.repository.query;

import com.kynsof.scheduled.infrastructure.entity.Resource;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResourceReadDataJPARepository extends JpaRepository<Resource, UUID>, JpaSpecificationExecutor<Resource> {
    Page<Resource> findAll(Specification specification, Pageable pageable);
}
