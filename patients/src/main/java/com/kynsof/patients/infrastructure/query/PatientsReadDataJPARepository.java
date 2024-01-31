package com.kynsof.patients.infrastructure.query;

import com.kynsof.patients.infrastructure.dto.PatientsDAO;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsReadDataJPARepository extends JpaRepository<PatientsDAO, UUID> {

}
