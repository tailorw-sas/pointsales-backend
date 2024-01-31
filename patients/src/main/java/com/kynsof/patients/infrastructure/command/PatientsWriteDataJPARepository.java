package com.kynsof.patients.infrastructure.command;

import com.kynsof.patients.infrastructure.dto.PatientsDAO;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsWriteDataJPARepository extends JpaRepository<PatientsDAO, UUID> {

}
