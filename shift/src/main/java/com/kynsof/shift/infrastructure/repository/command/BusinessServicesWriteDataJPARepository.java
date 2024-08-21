package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.BusinessServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessServicesWriteDataJPARepository extends JpaRepository<BusinessServices, UUID> {
}
