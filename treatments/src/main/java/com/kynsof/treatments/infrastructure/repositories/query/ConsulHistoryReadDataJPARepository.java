package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.ConsultHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ConsulHistoryReadDataJPARepository extends JpaRepository<ConsultHistory, UUID>, JpaSpecificationExecutor<ConsultHistory> {
    Page<ConsultHistory> findAll(Specification specification, Pageable pageable);
}
