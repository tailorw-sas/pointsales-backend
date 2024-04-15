package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ServicesResponse;
import com.kynsof.calendar.application.query.businesservice.getbyid.BusinessServicesResponse;
import com.kynsof.calendar.domain.dto.BusinessServicesDto;
import com.kynsof.calendar.domain.service.IBusinessServicesService;
import com.kynsof.calendar.infrastructure.entity.BusinessServices;
import com.kynsof.calendar.infrastructure.entity.Services;
import com.kynsof.calendar.infrastructure.repository.command.BusinessServicesWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.BusinessServicesReadDataJPARepository;
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
public class BusinessServicesServiceImpl implements IBusinessServicesService {

    @Autowired
    private BusinessServicesWriteDataJPARepository repositoryCommand;

    @Autowired
    private BusinessServicesReadDataJPARepository repositoryQuery;

    @Override
    public void create(BusinessServicesDto object) {
        this.repositoryCommand.save(new BusinessServices(object));
    }

    @Override
    public void update(BusinessServicesDto object) {
        this.repositoryCommand.save(new BusinessServices(object));
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BusinessServicesDto findById(UUID id) {
        
        Optional<BusinessServices> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, new ErrorField("id", "BusinessSevice not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<BusinessServices> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<BusinessServices> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<BusinessServices> data) {
        List<BusinessServicesResponse> patients = new ArrayList<>();
        for (BusinessServices s : data.getContent()) {
            patients.add(new BusinessServicesResponse(s.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    private PaginatedResponse getPaginatedServicesResponse(Page<Services> data) {
        List<ServicesResponse> patients = new ArrayList<>();
        for (Services s : data.getContent()) {
            patients.add(new ServicesResponse(s.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public PaginatedResponse findServicesByBusinessId(Pageable pageable, UUID businessId) {
        Page<Services> data = this.repositoryQuery.findServicesByBusinessId(businessId, pageable);
        return getPaginatedServicesResponse(data);
    }

}
