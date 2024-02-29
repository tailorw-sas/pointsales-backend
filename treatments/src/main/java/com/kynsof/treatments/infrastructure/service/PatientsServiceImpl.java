package com.kynsof.treatments.infrastructure.service;

import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.treatments.domain.service.IPatientsService;
import com.kynsof.treatments.infrastructure.entity.Patients;
import com.kynsof.treatments.infrastructure.repositories.command.PatientsWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.PatientsReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PatientsServiceImpl implements IPatientsService {

    @Autowired
    private PatientsWriteDataJPARepository repositoryCommand;

    @Autowired
    private PatientsReadDataJPARepository repositoryQuery;


    @Override
    public UUID create(PatientDto patients) {
        Patients entity = this.repositoryCommand.save(new Patients(patients));
        return entity.getId();
    }

    @Override
    public UUID update(PatientDto patientDto) {
        if (patientDto == null || patientDto.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }

        this.repositoryQuery.findById(patientDto.getId())
                .map(patient -> {
                    if (patientDto.getName() != null) patient.setName(patientDto.getName());
                    if (patientDto.getLastName() != null) patient.setLastName(patientDto.getLastName());
                    if (patientDto.getIdentification() != null)
                        patient.setIdentification(patientDto.getIdentification());
                    if (patientDto.getGender() != null) patient.setGender(patientDto.getGender());
                    if (patientDto.getStatus() != null) patient.setStatus(patientDto.getStatus());

                    return this.repositoryCommand.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + patientDto.getId() + " not found"));

        return patientDto.getId();
    }


    @Override
    public PatientDto findById(UUID id) {
        Optional<Patients> patient = this.repositoryQuery.findById(id);
        if (patient.isPresent()) {
            return patient.get().toAggregate();
        }
        //throw new RuntimeException("Patients not found.");
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Patients not found.");
    }



    @Override
    public void delete(UUID id) {
        PatientDto patientDelete = this.findById(id);
        patientDelete.setStatus(Status.INACTIVE);

        this.repositoryCommand.save(new Patients(patientDelete));
    }

}
