package com.kynsof.scheduled.infrastructure.repository.command;

import com.kynsof.scheduled.infrastructure.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientsWriteDataJPARepository extends JpaRepository<Patient, UUID> {

}
