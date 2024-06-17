package com.kynsof.rrhh.infrastructure.repository.command;

import com.kynsof.rrhh.infrastructure.identity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceWriteDataJPARepository extends JpaRepository<Device, UUID> {
}
