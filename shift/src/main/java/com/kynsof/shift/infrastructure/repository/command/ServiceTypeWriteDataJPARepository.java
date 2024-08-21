package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceTypeWriteDataJPARepository extends JpaRepository<ServiceType, UUID> {
}
