package com.kynsof.patients.infrastructure.repositories.query;

import com.kynsof.patients.infrastructure.entity.CurrentMedication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CurrentMedicationReadDataJPARepository extends JpaRepository<CurrentMedication, UUID>, JpaSpecificationExecutor<CurrentMedication> {
    Page<CurrentMedication> findAll(Specification specification, Pageable pageable);
}
