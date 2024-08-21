package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResourceWriteDataJPARepository extends JpaRepository<Resource, UUID> {
}
