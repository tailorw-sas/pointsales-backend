package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.BusinessResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BusinessResourceReadDataJPARepository extends JpaRepository<BusinessResource, UUID>, JpaSpecificationExecutor<BusinessResource> {
    Page<BusinessResource> findAll(Specification specification, Pageable pageable);
}
