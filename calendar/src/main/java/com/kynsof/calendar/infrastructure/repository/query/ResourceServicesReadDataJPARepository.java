package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.infrastructure.entity.ResourceService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ResourceServicesReadDataJPARepository extends JpaRepository<ResourceService, UUID>, JpaSpecificationExecutor<ResourceService> {

    
    @Query("SELECT br.id FROM ResourceService br WHERE br.resource.id = :resourceId ")
    List<UUID> FindResourceServiceByResourceId(@Param("resourceId") UUID resourceId);
}
