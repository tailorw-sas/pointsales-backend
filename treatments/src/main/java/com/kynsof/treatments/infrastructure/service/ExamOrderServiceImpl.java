package com.kynsof.treatments.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.treatments.application.query.examOrder.getall.ExamOrderResponse;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.ExternalConsultationDto;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.infrastructure.entity.ExamOrder;
import com.kynsof.treatments.infrastructure.entity.ExternalConsultation;
import com.kynsof.treatments.infrastructure.entity.specifications.ExamOrderSpecifications;
import com.kynsof.treatments.infrastructure.repositories.command.ExamOrderWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.ExamOrderReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExamOrderServiceImpl implements IExamOrderService {

    @Autowired
    private ExamOrderWriteDataJPARepository repositoryCommand;

    @Autowired
    private ExamOrderReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(ExamOrderDto dto) {
        ExamOrder entity = this.repositoryCommand.save(new ExamOrder(dto));
        return entity.getId();
    }

    @Override
    public void update(ExamOrderDto examOrder) {
        this.repositoryCommand.save(new ExamOrder(examOrder));
    }

    @Override
    public ExamOrderDto findById(UUID id) {
        Optional<ExamOrder> examOrder = this.repositoryQuery.findById(id);
        if (examOrder.isPresent()) {
            return examOrder.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.EXAM_ORDER_NOT_FOUND, new ErrorField("id", "Exam Order not found.")));
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID patientId) {
        ExamOrderSpecifications specifications = new ExamOrderSpecifications(patientId);
        Page<ExamOrder> data = this.repositoryQuery.findAll(specifications, pageable);

        List<ExamOrderResponse> allergyResponses = new ArrayList<>();
        for (ExamOrder p : data.getContent()) {
            allergyResponses.add(new ExamOrderResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(ExamOrderDto dto) {
        ExamOrder delete = new ExamOrder(dto);
        delete.setDeleted(Boolean.TRUE);
        delete.setExternalConsultation(null);

        this.repositoryCommand.save(delete);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<ExamOrder> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<ExamOrder> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<ExamOrder> data) {
        List<ExamOrderResponse> patients = new ArrayList<>();
        for (ExamOrder o : data.getContent()) {
            patients.add(new ExamOrderResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public ExamOrderDto findByExternalConsultation(ExternalConsultationDto externalConsultation) {
        Optional<ExamOrder> exOptional = this.repositoryQuery.findByExternalConsultation(new ExternalConsultation(externalConsultation));
        if (exOptional.isPresent()) {
            return exOptional.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.EXAM_ORDER_NOT_FOUND, new ErrorField("id", "Exam Order not found.")));
    }

}
