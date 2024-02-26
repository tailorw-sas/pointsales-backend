package com.kynsof.treatments.infrastructure.service;


import com.kynsof.treatments.application.query.patientVaccine.getall.PatientVaccineResponse;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.domain.dto.PatientVaccineDto;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.treatments.domain.service.IPatientVaccineService;
import com.kynsof.treatments.infrastructure.entity.PatientVaccine;
import com.kynsof.treatments.infrastructure.entity.specifications.PatientVaccineSpecifications;
import com.kynsof.treatments.infrastructure.repositories.command.PatientVaccineWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.PatientVaccineReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientVaccineServiceImpl implements IPatientVaccineService {

    @Autowired
    private PatientVaccineWriteDataJPARepository repositoryCommand;

    @Autowired
    private PatientVaccineReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(PatientVaccineDto dto) {
        PatientVaccine entity =this.repositoryCommand.save(new PatientVaccine(dto));
        return entity.getId();
    }

    @Override
    public UUID update(PatientVaccine dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }
        PatientVaccine entity = this.repositoryCommand.save(dto);
        return entity.getId();
    }


    @Override
    public PatientVaccineDto findById(UUID id) {
        Optional<PatientVaccine> patientVaccine = this.repositoryQuery.findById(id);
        if (patientVaccine.isPresent()) {
            return patientVaccine.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.PARAMETIRAZATION_NOT_FOUND, "Contact Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID patientId) {
        PatientVaccineSpecifications specifications = new PatientVaccineSpecifications( patientId);
        Page<PatientVaccine> data = this.repositoryQuery.findAll(specifications, pageable);

        List<PatientVaccineResponse> allergyResponses = new ArrayList<>();
        for (PatientVaccine p : data.getContent()) {
            allergyResponses.add(new PatientVaccineResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        PatientVaccineDto contactInfoDto = this.findById(id);
    }

}
