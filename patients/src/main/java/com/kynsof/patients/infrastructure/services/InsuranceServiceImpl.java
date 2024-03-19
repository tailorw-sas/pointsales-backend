package com.kynsof.patients.infrastructure.services;


import com.kynsof.patients.application.query.insuarance.getall.InsuranceResponse;
import com.kynsof.patients.domain.dto.InsuranceDto;
import com.kynsof.patients.domain.service.IInsuranceService;
import com.kynsof.patients.infrastructure.entity.Insurance;
import com.kynsof.patients.infrastructure.repository.query.InsuranceReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InsuranceServiceImpl implements IInsuranceService {

    @Autowired
    private InsuranceReadDataJPARepository repositoryQuery;
    @Override
    public InsuranceDto findById(UUID id) {
        Optional<Insurance> insurance = this.repositoryQuery.findById(id);
        if (insurance.isPresent()) {
            return insurance.get().toAggregate();
        }
      //  throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Contact Information not found.");
        throw new RuntimeException("Patients not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {

        Page<Insurance> data = this.repositoryQuery.findAll( pageable);

        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Insurance> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Insurance> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Insurance> data) {
        List<InsuranceResponse> insuranceResponses = new ArrayList<>();
        for (Insurance p : data.getContent()) {
            insuranceResponses.add(new InsuranceResponse(p.toAggregate()));
        }
        return new PaginatedResponse(insuranceResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
