package com.kynsoft.notification.infrastructure.repository.command;

import com.kynsoft.notification.infrastructure.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TenantWriteDataJPARepository extends JpaRepository<Tenant, UUID> {
}
