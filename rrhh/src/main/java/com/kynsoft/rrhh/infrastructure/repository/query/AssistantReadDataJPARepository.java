package com.kynsoft.rrhh.infrastructure.repository.query;

import com.kynsoft.rrhh.infrastructure.identity.Assistant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AssistantReadDataJPARepository extends JpaRepository<Assistant, UUID>, JpaSpecificationExecutor<Assistant> {
    Page<Assistant> findAll(Specification specification, Pageable pageable);

    @Query("SELECT COUNT(b) FROM Doctor b WHERE b.identification = :identification")
    Long countByIdentification(@Param("identification") String identification);

    @Query("SELECT COUNT(b) FROM Doctor b WHERE b.identification = :identification AND b.id <> :id")
    Long countByIdentificationAndNotId(@Param("identification") String identification, @Param("id") UUID id);

    @Query("SELECT COUNT(b) FROM Doctor b WHERE b.email = :email")
    Long countByEmail(@Param("email") String email);

    @Query("SELECT COUNT(b) FROM Doctor b WHERE b.email = :email AND b.id <> :id")
    Long countByEmailAndNotId(@Param("email") String email, @Param("id") UUID id);
}
