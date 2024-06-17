package com.kynsof.rrhh.infrastructure.repository.command;

import com.kynsof.rrhh.infrastructure.identity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessWriteDataJPARepository extends JpaRepository<Business, UUID> {
}
