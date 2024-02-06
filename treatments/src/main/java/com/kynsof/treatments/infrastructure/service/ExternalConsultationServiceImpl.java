package com.kynsof.treatments.infrastructure.service;


import com.kynsof.treatments.application.query.externalConsultation.getall.ExternalConsultationResponse;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.treatments.domain.dto.Status;
import com.kynsof.treatments.domain.exception.BusinessException;
import com.kynsof.treatments.domain.exception.DomainErrorMessage;
import com.kynsof.treatments.domain.service.IExternalConsultationService;

import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import com.kynsof.treatments.infrastructure.entity.specifications.ExternalConsultationSpecifications;
import com.kynsof.treatments.infrastructure.repositories.command.ExternalConsultationWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.ExternalConsultationReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExternalConsultationServiceImpl implements IExternalConsultationService {

    @Autowired
    private ExternalConsultationWriteDataJPARepository repositoryCommand;

    @Autowired
    private ExternalConsultationReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(ExternalConsultationDto dto) {
        ExternalConsultation entity =this.repositoryCommand.save(new ExternalConsultation(dto));
        return entity.getId();
    }

    @Override
    public UUID update(ExternalConsultation dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }
        ExternalConsultation entity = this.repositoryCommand.save(dto);
        return entity.getId();
    }


    @Override
    public ExternalConsultationDto findById(UUID id) {
        Optional<ExternalConsultation> contactInformation = this.repositoryQuery.findById(id);
        if (contactInformation.isPresent()) {
            return contactInformation.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.PATIENTS_NOT_FOUND, "Contact Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID doctorId, UUID patientId) {
        ExternalConsultationSpecifications specifications = new ExternalConsultationSpecifications(doctorId, patientId);
        Page<ExternalConsultation> data = this.repositoryQuery.findAll(specifications, pageable);

        List<ExternalConsultationResponse> allergyResponses = new ArrayList<>();
        for (ExternalConsultation p : data.getContent()) {
            allergyResponses.add(new ExternalConsultationResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        ExternalConsultationDto contactInfoDto = this.findById(id);
    }

}
