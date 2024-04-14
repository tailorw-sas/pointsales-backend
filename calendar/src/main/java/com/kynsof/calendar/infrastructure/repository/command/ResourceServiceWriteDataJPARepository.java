package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.ResourceService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceServiceWriteDataJPARepository extends JpaRepository<ResourceService, UUID> {
}
