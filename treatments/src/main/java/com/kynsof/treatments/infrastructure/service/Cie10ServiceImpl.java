package com.kynsof.treatments.infrastructure.service;


import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.application.query.cie10.getAll.Cie10Response;
import com.kynsof.treatments.domain.dto.Cie10Dto;
import com.kynsof.treatments.domain.service.ICie10Service;
import com.kynsof.treatments.infrastructure.entity.Cie10;
import com.kynsof.treatments.infrastructure.entity.specifications.Cie10Specifications;
import com.kynsof.treatments.infrastructure.repositories.query.Cie10ReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Cie10ServiceImpl implements ICie10Service {


    @Autowired
    private Cie10ReadDataJPARepository repositoryQuery;


    @Override
    public Cie10Dto findByCode(String code) {
        Optional<Cie10> cie10ByCode = Optional.ofNullable(this.repositoryQuery.findCie10ByCode(code));
        if (cie10ByCode.isPresent()) {
            return cie10ByCode.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Cie10 not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, String name, String code) {
        Cie10Specifications specifications = new Cie10Specifications(name, code);
        Page<Cie10> data = this.repositoryQuery.findAll(specifications, pageable);

        List<Cie10Response> allergyResponses = new ArrayList<>();
        for (Cie10 p : data.getContent()) {
            allergyResponses.add(new Cie10Response(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
