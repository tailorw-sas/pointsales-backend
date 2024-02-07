package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.Cie10;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ExternalConsultationReadDataJPARepository extends JpaRepository<ExternalConsultation, UUID>, JpaSpecificationExecutor<ExternalConsultation> {
    Page<ExternalConsultation> findAll(Specification specification, Pageable pageable);
}