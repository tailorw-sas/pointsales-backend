package com.kynsof.patients.infrastructure.repository.command;

import com.kynsof.patients.infrastructure.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientsWriteDataJPARepository extends JpaRepository<Patients, UUID> {

}
