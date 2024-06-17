package com.kynsoft.rrhh.infrastructure.repository.command;

import com.kynsoft.rrhh.infrastructure.identity.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssistantWriteDataJPARepository extends JpaRepository<Assistant, UUID> {
}
