package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ServiceTypeReadDataJPARepository extends JpaRepository<ServiceType, UUID>, JpaSpecificationExecutor<ServiceType> {
    @Query("SELECT COUNT(b) FROM ServiceType b WHERE b.name = :name AND b.id <> :id")
    Long countByNameAndNotId(@Param("name") String name, @Param("id") UUID id);

}
