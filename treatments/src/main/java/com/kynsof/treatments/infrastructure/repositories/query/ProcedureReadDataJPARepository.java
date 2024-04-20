package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.Procedure;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface ProcedureReadDataJPARepository extends JpaRepository<Procedure, UUID>, JpaSpecificationExecutor<Procedure> {
    Page<Procedure> findAll(Specification specification, Pageable pageable);
    Procedure findProcedureByCode(String code);

    @Query("SELECT COUNT(b) FROM Procedure b WHERE b.code = :code AND b.id <> :id")
    Long countByCodeAndNotId(@Param("code") String code, @Param("id") UUID id);

}
