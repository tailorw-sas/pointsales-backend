package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ServiceTypeResponse;
import com.kynsof.calendar.domain.dto.ServiceTypeDto;
import com.kynsof.calendar.domain.service.IServiceTypeService;
import com.kynsof.calendar.infrastructure.entity.ServiceType;
import com.kynsof.calendar.infrastructure.repository.command.ServiceTypeWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ServiceTypeReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.redis.CacheConfig;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceTypeServiceImpl implements IServiceTypeService {

    @Autowired
    private ServiceTypeWriteDataJPARepository repositoryCommand;

    @Autowired
    private ServiceTypeReadDataJPARepository repositoryQuery;

    @Override
    public void create(ServiceTypeDto object) {
        this.repositoryCommand.save(new ServiceType(object));
    }

    @Override
    public void update(ServiceTypeDto objectDto) {
        if (objectDto.getId() == null) {
            throw new BusinessException(DomainErrorMessage.BUSINESS_OR_ID_NULL, "ServiceTyoe DTO or ID cannot be null.");
        }

        this.repositoryQuery.findById(objectDto.getId())
                .map(object -> {
                    if (objectDto.getName() != null) {
                        object.setName(objectDto.getName());
                    }

                    if (objectDto.getPicture() != null) {
                        object.setPicture(objectDto.getPicture());
                    }

                    return this.repositoryCommand.save(object);
                })
                .orElseThrow(() -> new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Qualification not found."));

    }

    @Override
    public void delete(UUID id) {
        ServiceTypeDto objectDelete = this.findById(id);

        this.repositoryCommand.save(new ServiceType(objectDelete));
    }

    @Cacheable(cacheNames = CacheConfig.BUSINESS_CACHE, unless = "#result == null")
    @Override
    public ServiceTypeDto findById(UUID id) {

        Optional<ServiceType> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "ServiceType not found.");

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<ServiceType> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<ServiceType> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<ServiceType> data) {
        List<ServiceTypeResponse> patients = new ArrayList<>();
        for (ServiceType o : data.getContent()) {
            patients.add(new ServiceTypeResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
