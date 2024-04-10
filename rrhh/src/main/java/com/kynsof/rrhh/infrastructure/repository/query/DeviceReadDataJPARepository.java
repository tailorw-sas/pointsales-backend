package com.kynsof.rrhh.infrastructure.repository.query;

import com.kynsof.rrhh.infrastructure.identity.Device;
import com.kynsof.rrhh.infrastructure.identity.UserSystem;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface DeviceReadDataJPARepository extends JpaRepository<Device, UUID>, JpaSpecificationExecutor<Device> {
    Page<Device> findAll(Specification specification, Pageable pageable);

    @Query("SELECT ub.userSystem FROM Device d JOIN d.business b JOIN b.userBusinessRelations ub WHERE d.id = :deviceId")
    Page<UserSystem> findUsersByDeviceId(@Param("deviceId") UUID deviceId, Pageable pageable);
}
