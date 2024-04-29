package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.ExamOrder;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ExamOrderReadDataJPARepository extends JpaRepository<ExamOrder, UUID>, JpaSpecificationExecutor<ExamOrder> {
    Page<ExamOrder> findAll(Specification specification, Pageable pageable);

    Optional<ExamOrder> findByExternalConsultation(ExternalConsultation externalConsultation);
}
