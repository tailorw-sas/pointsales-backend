package com.kynsof.calendar.infrastructure.repository.command;

import com.kynsof.calendar.infrastructure.entity.Resource;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceWriteDataJPARepository extends JpaRepository<Resource, UUID> {
}
