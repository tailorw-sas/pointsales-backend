package com.kynsof.patients.infrastructure.repository.command;

import com.kynsof.patients.infrastructure.entity.MedicalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicalInformationWriteDataJPARepository extends JpaRepository<MedicalInformation, UUID> {

}
