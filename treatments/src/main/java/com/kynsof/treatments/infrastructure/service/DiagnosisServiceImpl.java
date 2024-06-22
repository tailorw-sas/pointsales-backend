package com.kynsof.treatments.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.treatments.application.query.diagnosis.getbyid.DiagnosisResponse;
import com.kynsof.treatments.domain.dto.DiagnosisDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IDiagnosisService;
import com.kynsof.treatments.infrastructure.entity.Diagnosis;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import com.kynsof.treatments.infrastructure.entity.Procedure;
import com.kynsof.treatments.infrastructure.repositories.command.DiagnosisWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.DiagnosisReadDataJPARepository;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiagnosisServiceImpl implements IDiagnosisService {

    private final DiagnosisReadDataJPARepository repositoryQuery;

    private final DiagnosisWriteDataJPARepository repositoryCommand;

    public DiagnosisServiceImpl(DiagnosisReadDataJPARepository repositoryQuery,
            DiagnosisWriteDataJPARepository repositoryCommand) {
        this.repositoryQuery = repositoryQuery;
        this.repositoryCommand = repositoryCommand;
    }

    @Override
    public void create(List<DiagnosisDto> diagnosisDtoList) {
        List<Diagnosis> diagnoses = diagnosisDtoList.stream().map(Diagnosis::new).toList();
        this.repositoryCommand.saveAll(diagnoses);
    }

    @Override
    public void update(DiagnosisDto treatment) {
        Diagnosis update = new Diagnosis(treatment);
        update.setUpdatedAt(LocalDateTime.now());
        this.repositoryCommand.save(update);
    }

    @Override
    public void delete(DiagnosisDto treatment) {
        try {
            this.repositoryCommand.deleteById(treatment.getId());
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    @Override
    public void deleteByIds(List<UUID> ids) {
        repositoryCommand.deleteAllByIdInBatch(ids);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Procedure> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Diagnosis> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Diagnosis> data) {
        List<DiagnosisResponse> patients = new ArrayList<>();
        for (Diagnosis o : data.getContent()) {
            patients.add(new DiagnosisResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public DiagnosisDto findById(UUID id) {
        Optional<Diagnosis> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.DIAGNOSIS_NOT_FOUND, new ErrorField("id", "Diagnosis not found.")));
    }

    @Override
    public PaginatedResponse findByExternalConsultation(ExternalConsultationDto externalConsultation, Pageable pageable) {
        Page<Diagnosis> diagnosis = this.repositoryQuery.findByExternalConsultation(new ExternalConsultation(externalConsultation), pageable);
        return getPaginatedResponse(diagnosis);
    }
}
