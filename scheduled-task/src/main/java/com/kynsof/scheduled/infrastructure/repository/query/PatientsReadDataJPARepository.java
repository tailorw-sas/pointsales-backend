package com.kynsof.scheduled.infrastructure.repository.query;

import com.kynsof.scheduled.infrastructure.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PatientsReadDataJPARepository extends JpaRepository<Patient, UUID>, JpaSpecificationExecutor<Patient> {
    Page<Patient> findAll(Specification specification, Pageable pageable);
}
