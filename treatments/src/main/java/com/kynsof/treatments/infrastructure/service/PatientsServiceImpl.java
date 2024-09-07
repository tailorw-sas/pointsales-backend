package com.kynsof.treatments.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.treatments.domain.dto.PatientDto;
import com.kynsof.treatments.domain.service.IPatientsService;
import com.kynsof.treatments.infrastructure.entity.Patients;
import com.kynsof.treatments.infrastructure.repositories.command.PatientsWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.PatientsReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class PatientsServiceImpl implements IPatientsService {

    private final PatientsWriteDataJPARepository repositoryCommand;
    private final PatientsReadDataJPARepository repositoryQuery;

    public PatientsServiceImpl(PatientsWriteDataJPARepository repositoryCommand,
                               PatientsReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    @Transactional
    public UUID create(PatientDto patients) {
        Patients entity = repositoryCommand.save(new Patients(patients));
        return entity.getId();
    }

    @Override
    @Transactional
    public UUID update(PatientDto patientDto) {
        if (patientDto == null || patientDto.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }

        Patients updatedPatient = repositoryQuery.findById(patientDto.getId())
                .map(patient -> {
                    Optional.ofNullable(patientDto.getName()).ifPresent(patient::setName);
                    Optional.ofNullable(patientDto.getLastName()).ifPresent(patient::setLastName);
                    Optional.ofNullable(patientDto.getIdentification()).ifPresent(patient::setIdentification);
                    Optional.ofNullable(patientDto.getGender()).ifPresent(patient::setGender);
                    Optional.ofNullable(patientDto.getStatus()).ifPresent(patient::setStatus);
                    Optional.ofNullable(patientDto.getBirthDate()).ifPresent(patient::setBirthDate);
                    return repositoryCommand.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + patientDto.getId() + " not found"));

        return updatedPatient.getId();
    }

    @Override
    public PatientDto findById(UUID id) {
        return repositoryQuery.findById(id)
                .map(Patients::toAggregate)
                .orElseThrow(() -> new BusinessNotFoundException(new GlobalBusinessException(
                        DomainErrorMessage.PATIENTS_NOT_FOUND,
                        new ErrorField("id", "Patient not found."))));
    }

    @Override
    public void delete(UUID id) {
        try {
            repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(
                    DomainErrorMessage.NOT_DELETE,
                    new ErrorField("id", "Element cannot be deleted as it has a related element.")));
        }
    }
}