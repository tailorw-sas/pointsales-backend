package com.kynsof.treatments.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.share.utils.GeneratorRandomNumber;
import com.kynsof.treatments.application.query.externalConsultation.getall.ExternalConsultationResponse;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IExternalConsultationService;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import com.kynsof.treatments.infrastructure.entity.specifications.ExternalConsultationSpecifications;
import com.kynsof.treatments.infrastructure.repositories.command.ExternalConsultationWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.ExternalConsultationReadDataJPARepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ExternalConsultationServiceImpl implements IExternalConsultationService {

    @Autowired
    private ExternalConsultationWriteDataJPARepository repositoryCommand;

    @Autowired
    private ExternalConsultationReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(ExternalConsultationDto dto) {
        ExternalConsultation entity = new ExternalConsultation(dto);
        entity.setReferenceNumber(GeneratorRandomNumber.generateRandomSecurity());

        this.repositoryCommand.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public UUID createAll(ExternalConsultationDto dto) {
        ExternalConsultation entity = new ExternalConsultation(dto);
        entity.setReferenceNumber(GeneratorRandomNumber.generateRandomSecurity());

        this.repositoryCommand.save(entity);
        return entity.getId();
    }

    @Override
    public UUID update(ExternalConsultationDto dto) {
        ExternalConsultation update = new ExternalConsultation(dto);
        update.setUpdatedAt(LocalDateTime.now());
        ExternalConsultation entity = this.repositoryCommand.save(update);
        return entity.getId();
    }

    @Override
    public ExternalConsultationDto findById(UUID id) {
        Optional<ExternalConsultation> contactInformation = this.repositoryQuery.findById(id);
        if (contactInformation.isPresent()) {
            return contactInformation.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.EXTERNAL_CONSULTATION_NOT_FOUND, new ErrorField("id", "External Consultation not found.")));
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID doctorId, UUID patientId) {
        ExternalConsultationSpecifications specifications = new ExternalConsultationSpecifications(doctorId, patientId);
        Page<ExternalConsultation> data = this.repositoryQuery.findAll(specifications, pageable);

        List<ExternalConsultationResponse> externalConsultationResponses = new ArrayList<>();
        for (ExternalConsultation p : data.getContent()) {
            externalConsultationResponses.add(new ExternalConsultationResponse(p.toAggregate()));
        }
        return new PaginatedResponse(externalConsultationResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filter) {
        GenericSpecificationsBuilder<ExternalConsultation> specifications = new GenericSpecificationsBuilder<>(filter);
        Page<ExternalConsultation> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<ExternalConsultation> data) {
        List<ExternalConsultationResponse> externalConsultationResponses = new ArrayList<>();
        for (ExternalConsultation p : data.getContent()) {
            externalConsultationResponses.add(new ExternalConsultationResponse(p.toAggregate()));
        }
        return new PaginatedResponse(externalConsultationResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(ExternalConsultationDto dto) {
        try {
            this.repositoryCommand.deleteById(dto.getId());
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    @Override
    public Long countConsultationsByBusinessAndDateRange(UUID businessId, Date startDate, Date endDate) {
        return this.repositoryQuery.countConsultationsByBusinessAndDateRange(businessId, startDate, endDate);
    }

}
