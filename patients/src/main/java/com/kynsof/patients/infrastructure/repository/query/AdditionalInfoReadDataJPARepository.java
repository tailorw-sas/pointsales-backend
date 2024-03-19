package com.kynsof.patients.infrastructure.repository.query;

import com.kynsof.patients.infrastructure.entity.AdditionalInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AdditionalInfoReadDataJPARepository extends JpaRepository<AdditionalInformation, UUID>, JpaSpecificationExecutor<AdditionalInformation> {
    Page<AdditionalInformation> findAll(Specification specification, Pageable pageable);
}
