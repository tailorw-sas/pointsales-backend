package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.BusinessServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessServicesWriteDataJPARepository extends JpaRepository<BusinessServices, UUID> {
}
