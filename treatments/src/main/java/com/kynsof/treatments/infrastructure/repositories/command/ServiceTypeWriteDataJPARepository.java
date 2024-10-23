package com.kynsof.treatments.infrastructure.repositories.command;

import com.kynsof.treatments.infrastructure.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceTypeWriteDataJPARepository extends JpaRepository<ServiceType, UUID> {
}
