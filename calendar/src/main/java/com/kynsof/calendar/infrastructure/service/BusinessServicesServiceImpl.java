package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.businessService.GetServicesSimpleByBusinessId.ServiceSimple;
import com.kynsof.calendar.application.query.businessService.getbyid.BusinessServicesResponse;
import com.kynsof.calendar.application.query.service.ServicesResponse;
import com.kynsof.calendar.domain.dto.BusinessServicePriceResponse;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        BusinessServices update = new BusinessServices(object);
        this.repositoryCommand.save(update);
    }

    @Override
    public void delete(BusinessServicesDto object) {
        try {
            this.repositoryCommand.deleteById(object.getId());
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    @Override
    public void deleteIds(List<UUID> ids) {
        this.repositoryCommand.deleteAllByIdInBatch(ids);
    }

    @Override
    public BusinessServicesDto findById(UUID id) {

        Optional<BusinessServices> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND,
                new ErrorField("id", "BusinessService not found.")));

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
        Page<BusinessServices> data = this.repositoryQuery.findServicesByBusinessId(businessId, pageable);
        List<BusinessServicePriceResponse> responses = new ArrayList<>();
        for (BusinessServices s : data.getContent()) {
            BusinessServicePriceResponse businessServicePriceResponse = new BusinessServicePriceResponse();
            businessServicePriceResponse.setPrice(s.getPrice());
            businessServicePriceResponse.setService(new ServicesResponse(s.getServices().toAggregate()));
            responses.add(businessServicePriceResponse);
        }
        return new PaginatedResponse(responses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public PaginatedResponse findServicesSimpleByBusinessId(Pageable pageable, UUID businessId) {
        Page<BusinessServices> data = this.repositoryQuery.findServicesByBusinessId(businessId, pageable);
        List<ServiceSimple> responses = new ArrayList<>();
        for (BusinessServices s : data.getContent()) {
            ServiceSimple serviceSimple = new ServiceSimple(s.getServices().getId(), s.getServices().getName(), s.getServices().getStatus());
            responses.add(serviceSimple);
        }
        return new PaginatedResponse(responses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public List<UUID> findBusinessServiceIdByBusinessId(UUID businessId) {
        return this.repositoryQuery.findBusinessServicesIdByBusinessId(businessId);
    }

    @Override
    public PaginatedResponse findServicesByResourceId(Pageable pageable, UUID resourceId) {
        Page<BusinessServices> data = this.repositoryQuery.findServicesByResourceId(resourceId, pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public void createAll(List<BusinessServicesDto> businessServicePrice) {
        this.repositoryCommand.saveAllAndFlush(businessServicePrice.stream().map(BusinessServices::new).toList());
    }

    @Override
    public Long countRelationsBetweenServiceAndBusiness(UUID serviceId, UUID businessId) {
        return this.repositoryQuery.countRelationsBetweenServiceAndBusiness(serviceId, businessId);
    }

}
