package com.kynsof.treatments.infrastructure.service;


import com.kynsof.treatments.application.query.examOrder.getall.ExamOrderResponse;
import com.kynsof.treatments.domain.dto.ExamOrderDto;
import com.kynsof.treatments.domain.dto.PaginatedResponse;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.treatments.domain.service.IExamOrderService;
import com.kynsof.treatments.infrastructure.entity.ExamOrder;
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
        ExamOrder entity =this.repositoryCommand.save(new ExamOrder(dto));
        return entity.getId();
    }

    @Override
    public UUID update(ExamOrder examOrder) {
        if (examOrder == null || examOrder.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }
        ExamOrder entity = this.repositoryCommand.save(examOrder);
        return entity.getId();
    }


    @Override
    public ExamOrderDto findById(UUID id) {
        Optional<ExamOrder> examOrder = this.repositoryQuery.findById(id);
        if (examOrder.isPresent()) {
            return examOrder.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Contact Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID patientId) {
        ExamOrderSpecifications specifications = new ExamOrderSpecifications( patientId);
        Page<ExamOrder> data = this.repositoryQuery.findAll(specifications, pageable);

        List<ExamOrderResponse> allergyResponses = new ArrayList<>();
        for (ExamOrder p : data.getContent()) {
            allergyResponses.add(new ExamOrderResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        ExamOrderDto examOrderDto = this.findById(id);
    }

}
