package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.businessresource.getbyid.BusinessResourceResponse;
import com.kynsof.calendar.domain.dto.BusinessResourceDto;
import com.kynsof.calendar.domain.service.IBusinessResourceService;
import com.kynsof.calendar.infrastructure.entity.BusinessResource;
import com.kynsof.calendar.infrastructure.repository.command.BusinessResourceWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.BusinessResourceReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BusinessResourceServiceImpl implements IBusinessResourceService {

    @Autowired
    private BusinessResourceWriteDataJPARepository repositoryCommand;

    @Autowired
    private BusinessResourceReadDataJPARepository repositoryQuery;

    @Override
    public void create(BusinessResourceDto object) {
        this.repositoryCommand.save(new BusinessResource(object));
    }

    @Override
    public void update(BusinessResourceDto object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BusinessResourceDto findById(UUID id) {
        
        Optional<BusinessResource> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, new ErrorField("id", "BusinessResource not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<BusinessResource> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<BusinessResource> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<BusinessResource> data) {
        List<BusinessResourceResponse> patients = new ArrayList<>();
        for (BusinessResource s : data.getContent()) {
            patients.add(new BusinessResourceResponse(s.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
