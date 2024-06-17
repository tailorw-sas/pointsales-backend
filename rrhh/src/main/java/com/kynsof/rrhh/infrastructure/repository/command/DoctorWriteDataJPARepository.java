package com.kynsof.rrhh.infrastructure.repository.command;

import com.kynsof.rrhh.infrastructure.identity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorWriteDataJPARepository extends JpaRepository<Doctor, UUID> {
}
