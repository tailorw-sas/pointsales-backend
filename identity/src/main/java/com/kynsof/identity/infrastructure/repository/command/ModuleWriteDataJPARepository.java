package com.kynsof.identity.infrastructure.repository.command;

import com.kynsof.identity.infrastructure.identity.SystemModule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleWriteDataJPARepository extends JpaRepository<SystemModule, UUID> {
}
