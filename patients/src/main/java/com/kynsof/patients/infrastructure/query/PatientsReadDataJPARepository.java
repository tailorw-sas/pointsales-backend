package com.kynsof.patients.infrastructure.query;

import com.kynsof.patients.infrastructure.entity.PatientsDAO;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PatientsReadDataJPARepository extends JpaRepository<PatientsDAO, UUID>, JpaSpecificationExecutor<PatientsDAO> {
    Page<PatientsDAO> findAll(Specification specification, Pageable pageable);
}
