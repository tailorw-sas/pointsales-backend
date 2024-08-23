package com.kynsof.shift.infrastructure.repository.query;

import com.kynsof.shift.infrastructure.entity.Resource;
import com.kynsof.shift.infrastructure.entity.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResourceReadDataJPARepository extends JpaRepository<Resource, UUID>, JpaSpecificationExecutor<Resource> {
    Page<Resource> findAll(Specification specification, Pageable pageable);

    @Query("SELECT br.resource FROM BusinessResource br WHERE br.business.id = :businessId AND br.resource IN" +
            " (SELECT rs.resource FROM ResourceService rs WHERE rs.service.id = :serviceId)")
    Page<Resource> findResourcesByServiceIdAndBusinessId(@Param("serviceId") UUID serviceId, @Param("businessId") UUID businessId, Pageable pageable);

    @Query("SELECT rs.service FROM ResourceService rs JOIN rs.resource r JOIN r.businessResources br WHERE rs.resource.id = :resourceId AND br.business.id = :businessId")
    List<Services> findAllServicesByResourceAndBusiness(@Param("resourceId") UUID resourceId, @Param("businessId") UUID businessId);

    Optional<Resource> findResourceByCode(String code);

    boolean existsResourceByCode(String code);
}