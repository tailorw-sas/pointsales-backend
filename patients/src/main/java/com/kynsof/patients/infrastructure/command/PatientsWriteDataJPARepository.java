package com.kynsof.patients.infrastructure.command;

import com.kynsof.patients.infrastructure.entity.Patients;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsWriteDataJPARepository extends JpaRepository<Patients, UUID> {

}
