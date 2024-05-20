package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.BusinessServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BusinessServicesReadDataJPARepository extends JpaRepository<BusinessServices, UUID>, JpaSpecificationExecutor<BusinessServices> {
    Page<BusinessServices> findAll(Specification specification, Pageable pageable);

    @Query("SELECT bs FROM BusinessServices bs" +
            " WHERE bs.business.id = :businessId")
    Page<BusinessServices> findServicesByBusinessId(@Param("businessId") UUID businessId, Pageable pageable);

    @Query("SELECT bs.id FROM BusinessServices bs WHERE bs.business.id = :businessId")
    List<UUID> findBusinessServicesIdByBusinessId(@Param("businessId") UUID businessId);

    @Query("SELECT bs FROM BusinessServices bs " +
           "JOIN bs.business b " +
           "JOIN b.businessResources br " +
           "JOIN br.resource r " +
           "WHERE r.id = :resourceId")
    Page<BusinessServices> findServicesByResourceId(@Param("resourceId") UUID resourceId, Pageable pageable);

    @Query("SELECT COUNT(bs) FROM BusinessServices bs WHERE bs.services.id = :serviceId AND bs.business.id = :businessId AND bs.deleted = false")
    Long countRelationsBetweenServiceAndBusiness(@Param("serviceId") UUID serviceId, @Param("businessId") UUID businessId);
}
