package com.kynsof.rrhh.infrastructure.repository.query;

import com.kynsof.rrhh.infrastructure.identity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface DeviceReadDataJPARepository extends JpaRepository<Device, UUID>, JpaSpecificationExecutor<Device> {
    Page<Device> findAll(Specification specification, Pageable pageable);
}
