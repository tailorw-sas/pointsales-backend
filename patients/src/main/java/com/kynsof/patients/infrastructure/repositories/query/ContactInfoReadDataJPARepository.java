package com.kynsof.patients.infrastructure.repositories.query;

import com.kynsof.patients.infrastructure.entity.ContactInformation;
import com.kynsof.patients.infrastructure.entity.Patients;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ContactInfoReadDataJPARepository extends JpaRepository<ContactInformation, UUID>, JpaSpecificationExecutor<ContactInformation> {
    Page<ContactInformation> findAll(Specification specification, Pageable pageable);
}
