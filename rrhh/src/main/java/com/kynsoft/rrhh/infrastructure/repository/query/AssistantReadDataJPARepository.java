package com.kynsoft.rrhh.infrastructure.repository.query;

import com.kynsoft.rrhh.infrastructure.identity.Assistant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssistantReadDataJPARepository extends JpaRepository<Assistant, UUID>, JpaSpecificationExecutor<Assistant> {
    Page<Assistant> findAll(Specification specification, Pageable pageable);

    @Query("SELECT COUNT(b) FROM Doctor b WHERE b.identification = :identification")
    Long countByIdentificationAndNotId(@Param("identification") String identification);

    @Query("SELECT COUNT(b) FROM Doctor b WHERE b.email = :email")
    Long countByEmailAndNotId(@Param("email") String email);
}
