package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.BusinessResource;
import com.kynsof.calendar.infrastructure.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BusinessResourceReadDataJPARepository extends JpaRepository<BusinessResource, UUID>, JpaSpecificationExecutor<BusinessResource> {
    Page<BusinessResource> findAll(Specification specification, Pageable pageable);
    
    @Query("SELECT br.resource FROM BusinessResource br WHERE br.business.id = :businessId")
    Page<Resource> findResourceByBusinessId(@Param("businessId") UUID businessId, Pageable pageable);
    
    @Query("SELECT br FROM BusinessResource br WHERE br.business.id = :businessId AND br.resource.id = :resourceId")
    Optional<BusinessResource> findBusinessResourceByBusinessIdAndResourceId(@Param("businessId") UUID businessId, @Param("resourceId") UUID resourceId);
    
    @Query("SELECT COUNT(br) FROM BusinessResource br WHERE br.business.id = :businessId AND br.resource.id = :resourceId")
    Long countBusinessResourceByBusinessIdAndResourceId(@Param("businessId") UUID businessId, @Param("resourceId") UUID resourceId);

}
