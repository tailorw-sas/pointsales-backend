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
import com.kynsof.treatments.domain.service.IDiagnosisService;
import com.kynsof.treatments.infrastructure.entity.Diagnosis;
import com.kynsof.treatments.infrastructure.entity.Procedure;
import com.kynsof.treatments.infrastructure.repositories.command.DiagnosisWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.DiagnosisReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiagnosisServiceImpl implements IDiagnosisService {

    @Autowired
    private DiagnosisReadDataJPARepository repositoryQuery;

    @Autowired
    private DiagnosisWriteDataJPARepository repositoryCommand;

    @Override
    public void create(DiagnosisDto treatment) {
        this.repositoryCommand.save(new Diagnosis(treatment));
    }

    @Override
    public void update(DiagnosisDto treatment) {
        this.repositoryCommand.save(new Diagnosis(treatment));
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
}
