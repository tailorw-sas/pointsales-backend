package com.kynsof.treatments.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.treatments.domain.dto.DoctorDto;
import com.kynsof.treatments.domain.dto.enumDto.Status;
import com.kynsof.treatments.domain.service.IDoctorService;
import com.kynsof.treatments.infrastructure.entity.Doctor;
import com.kynsof.treatments.infrastructure.repositories.command.DoctorWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.DoctorReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    private DoctorWriteDataJPARepository repositoryCommand;

    @Autowired
    private DoctorReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(DoctorDto doctorDto) {
        Doctor entity = this.repositoryCommand.save(new Doctor(doctorDto));
        return entity.getId();
    }

    @Override
    public UUID update(DoctorDto doctorDto) {
        if (doctorDto == null || doctorDto.getId() == null) {
            throw new IllegalArgumentException("Doctor DTO or ID cannot be null");
        }

        this.repositoryQuery.findById(doctorDto.getId())
                .map(patient -> {
                    if (doctorDto.getName() != null) {
                        patient.setName(doctorDto.getName());
                    }
                    if (doctorDto.getLastName() != null) {
                        patient.setLastName(doctorDto.getLastName());
                    }
                    if (doctorDto.getIdentification() != null) {
                        patient.setIdentification(doctorDto.getIdentification());
                    }
                    if (doctorDto.getStatus() != null) {
                        patient.setStatus(doctorDto.getStatus());
                    }
                    if (doctorDto.getImage() != null) {
                        patient.setImage(doctorDto.getImage());
                    }

                    return this.repositoryCommand.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + doctorDto.getId() + " not found"));

        return doctorDto.getId();
    }

    @Override
    public DoctorDto findById(UUID id) {
        Optional<Doctor> patient = this.repositoryQuery.findById(id);
        if (patient.isPresent()) {
            return patient.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.DOCTOR_NOT_FOUND, new ErrorField("id", "Doctor not found.")));
    }

    @Override
    public void delete(UUID id) {
        DoctorDto patientDelete = this.findById(id);
        patientDelete.setStatus(Status.INACTIVE);

        this.repositoryCommand.save(new Doctor(patientDelete));
    }

}
