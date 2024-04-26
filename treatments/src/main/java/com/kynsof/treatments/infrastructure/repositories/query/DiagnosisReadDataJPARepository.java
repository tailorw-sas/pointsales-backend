package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.Diagnosis;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface DiagnosisReadDataJPARepository extends JpaRepository<Diagnosis, UUID>, JpaSpecificationExecutor<Diagnosis> {
    Page<Diagnosis> findAll(Specification specification, Pageable pageable);

    Page<Diagnosis> findByExternalConsultation(ExternalConsultation externalConsultation, Pageable pageable);
}
