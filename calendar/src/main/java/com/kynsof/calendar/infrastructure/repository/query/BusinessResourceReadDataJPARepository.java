package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.BusinessResource;
import com.kynsof.calendar.infrastructure.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusinessResourceReadDataJPARepository extends JpaRepository<BusinessResource, UUID>, JpaSpecificationExecutor<BusinessResource> {
    Page<BusinessResource> findAll(Specification specification, Pageable pageable);
    
    @Query("SELECT br.resource FROM BusinessResource br WHERE br.business.id = :businessId")
    Page<Resource> findResourceByBusinessId(@Param("businessId") UUID businessId, Pageable pageable);

}
