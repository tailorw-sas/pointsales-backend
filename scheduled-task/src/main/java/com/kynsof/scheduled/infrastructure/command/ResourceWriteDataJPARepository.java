package com.kynsof.scheduled.infrastructure.command;

import com.kynsof.scheduled.infrastructure.entity.Resource;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceWriteDataJPARepository extends JpaRepository<Resource, UUID> {
}
