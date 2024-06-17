package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.domain.dto.PatientDto;
import com.kynsof.calendar.domain.dto.enumType.PatientStatus;
import com.kynsof.calendar.domain.service.IPatientsService;
import com.kynsof.calendar.infrastructure.entity.Patient;
import com.kynsof.calendar.infrastructure.repository.command.PatientsWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.PatientsReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
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
        Patient entity = this.repositoryCommand.save(new Patient(patients));
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
                    if(patientDto.getLogo() != null) patient.setLogo(patientDto.getLogo());

                    return this.repositoryCommand.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + patientDto.getId() + " not found"));

        return patientDto.getId();
    }
    
    @Override
    public PatientDto findById(UUID id) {
        Optional<Patient> patient = this.repositoryQuery.findById(id);
        if (patient.isPresent()) {
            return patient.get().toAggregate();
        }
        //throw new RuntimeException("Patients not found.");
        throw new BusinessException(DomainErrorMessage.PATIENTS_NOT_FOUND, "Patients not found.");
    }

    @Override
    public void delete(UUID id) {
        try {
            this.repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

}
