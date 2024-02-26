package com.kynsof.patients.infrastructure.repositories.query;

import com.kynsof.patients.infrastructure.entity.MedicalInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface MedicalInformationReadDataJPARepository extends JpaRepository<MedicalInformation, UUID>, JpaSpecificationExecutor<MedicalInformation> {
    Page<MedicalInformation> findAll(Specification specification, Pageable pageable);
}
