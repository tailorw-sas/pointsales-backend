package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceWriteDataJPARepository extends JpaRepository<Resource, UUID> {
}
