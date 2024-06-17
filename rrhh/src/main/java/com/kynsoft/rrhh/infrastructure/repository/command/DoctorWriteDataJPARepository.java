package com.kynsoft.rrhh.infrastructure.repository.command;

import com.kynsoft.rrhh.infrastructure.identity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorWriteDataJPARepository extends JpaRepository<Doctor, UUID> {
}
