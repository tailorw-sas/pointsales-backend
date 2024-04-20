package com.kynsof.treatments.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.treatments.application.query.treatment.getbyid.TreatmentResponse;
import com.kynsof.treatments.domain.dto.TreatmentDto;
import com.kynsof.treatments.domain.dto.enumDto.MedicalExamCategory;
import com.kynsof.treatments.domain.service.ITreatmentService;
import com.kynsof.treatments.infrastructure.entity.Procedure;
import com.kynsof.treatments.infrastructure.entity.Treatment;
import com.kynsof.treatments.infrastructure.repositories.command.TreatmentWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.TreatmentReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TreatmentServiceImpl implements ITreatmentService {

    @Autowired
    private TreatmentReadDataJPARepository repositoryQuery;

    @Autowired
    private TreatmentWriteDataJPARepository repositoryCommand;

    @Override
    public void create(TreatmentDto treatment) {
        this.repositoryCommand.save(new Treatment(treatment));
    }

    @Override
    public void update(TreatmentDto treatment) {
        this.repositoryCommand.save(new Treatment(treatment));
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        filterCreteria(filterCriteria);
        GenericSpecificationsBuilder<Procedure> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Treatment> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private void filterCreteria(List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {
            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    MedicalExamCategory enumValue = MedicalExamCategory.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Valor inválido para el tipo Enum MedicalExamCategory: " + filter.getValue());
                }
            }
        }
    }

    private PaginatedResponse getPaginatedResponse(Page<Treatment> data) {
        List<TreatmentResponse> patients = new ArrayList<>();
        for (Treatment o : data.getContent()) {
            patients.add(new TreatmentResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public TreatmentDto findById(UUID id) {
        Optional<Treatment> procedure = this.repositoryQuery.findById(id);
        if (procedure.isPresent()) {
            return procedure.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.PROCEDURE_NOT_FOUND, new ErrorField("id", "Procedure not found.")));
    }
}
