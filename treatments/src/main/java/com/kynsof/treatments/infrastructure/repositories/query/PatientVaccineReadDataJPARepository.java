package com.kynsof.treatments.infrastructure.repositories.query;

import com.kynsof.treatments.infrastructure.entity.PatientVaccine;
import com.kynsof.treatments.infrastructure.entity.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PatientVaccineReadDataJPARepository extends JpaRepository<PatientVaccine, UUID>, JpaSpecificationExecutor<PatientVaccine> {
    Page<PatientVaccine> findAll(Specification specification, Pageable pageable);

    @Query("SELECT pv.vaccine FROM PatientVaccine pv WHERE pv.patient.id = :patientId AND pv.status IN ('ADMINISTERED', 'COMPLETED')")
    List<Vaccine> findVaccinesByPatientId(UUID patientId);
}
