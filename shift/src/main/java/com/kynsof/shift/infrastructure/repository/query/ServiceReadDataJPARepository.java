package com.kynsof.shift.infrastructure.repository.query;

import com.kynsof.shift.infrastructure.entity.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ServiceReadDataJPARepository extends JpaRepository<Services, UUID>, JpaSpecificationExecutor<Services> {
    Page<Services> findAll(Specification specification, Pageable pageable);

    @Query("SELECT COUNT(b) FROM Services b WHERE b.name = :name AND b.id <> :id")
    Long countByNameAndNotId(@Param("name") String name, @Param("id") UUID id);

    @Query("SELECT rs.service FROM ResourceService rs WHERE rs.resource.id = :resourceId")
    Page<Services> findServicesByResourceId(@Param("resourceId") UUID resourceId, Pageable pageable);

    Optional<Services> findServicesByCode(String code);
    boolean existsServicesByCode(String code);
}
