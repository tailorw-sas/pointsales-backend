package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ResourceReadDataJPARepository extends JpaRepository<Resource, UUID>, JpaSpecificationExecutor<Resource> {
    Page<Resource> findAll(Specification specification, Pageable pageable);

    @Query("SELECT rs.resource FROM ResourceService rs WHERE rs.service.id = :serviceId")
    Page<Resource> findResourcesByServiceId(@Param("serviceId") UUID serviceId, Pageable pageable);
}
