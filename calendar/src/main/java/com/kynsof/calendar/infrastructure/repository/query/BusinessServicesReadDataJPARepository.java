package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.BusinessServices;
import com.kynsof.calendar.infrastructure.entity.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessServicesReadDataJPARepository extends JpaRepository<BusinessServices, UUID>, JpaSpecificationExecutor<BusinessServices> {
    Page<BusinessServices> findAll(Specification specification, Pageable pageable);

    @Query("SELECT bs.services FROM BusinessServices bs WHERE bs.business.id = :businessId")
    Page<Services> findServicesByBusinessId(@Param("businessId") UUID businessId, Pageable pageable);

    @Query("SELECT bs FROM BusinessServices bs " +
           "JOIN bs.business b " +
           "JOIN b.businessResources br " +
           "JOIN br.resource r " +
           "WHERE r.id = :resourceId")
    Page<BusinessServices> findServicesByResourceId(@Param("resourceId") UUID resourceId, Pageable pageable);
}
