package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.BusinessResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessResourceWriteDataJPARepository extends JpaRepository<BusinessResource, UUID> {
}