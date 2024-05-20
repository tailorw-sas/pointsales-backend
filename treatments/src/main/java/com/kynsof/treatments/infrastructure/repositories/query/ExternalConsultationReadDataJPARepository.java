package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import feign.Param;
import java.util.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface ExternalConsultationReadDataJPARepository extends JpaRepository<ExternalConsultation, UUID>, JpaSpecificationExecutor<ExternalConsultation> {

    Page<ExternalConsultation> findAll(Specification specification, Pageable pageable);

    @Query("SELECT COUNT(ec) FROM ExternalConsultation ec JOIN ec.business b WHERE b.id = :businessId AND ec.deleted = false AND ec.consultationTime >= :startDate AND ec.consultationTime < :endDate")
    Long countConsultationsByBusinessAndDateRange(@Param("businessId") UUID businessId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
