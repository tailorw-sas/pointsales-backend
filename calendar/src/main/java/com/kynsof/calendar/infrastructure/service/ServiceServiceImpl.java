package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ServicesResponse;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.infrastructure.entity.Services;
import com.kynsof.calendar.infrastructure.entity.specifications.BusinessSpecifications;
import com.kynsof.calendar.infrastructure.repository.command.ServiceWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ServiceReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.share.utils.ConfigureTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceServiceImpl implements IServiceService {

    @Autowired
    private ServiceWriteDataJPARepository repositoryCommand;

    @Autowired
    private ServiceReadDataJPARepository repositoryQuery;

    @Override
    public void create(ServiceDto object) {
        object.setStatus(EServiceStatus.ACTIVE);

        object.setCreateAt(ConfigureTimeZone.getTimeZone());

        this.repositoryCommand.save(new Services(object));
    }

    @Override
    public void update(ServiceDto objectDto) {
        if (objectDto.getId() == null || objectDto == null) {
            throw new BusinessException(DomainErrorMessage.BUSINESS_OR_ID_NULL, "Business DTO or ID cannot be null.");
        }

        this.repositoryQuery.findById(objectDto.getId())
                .map(object -> {
                    if (objectDto.getDescription() != null) {
                        object.setDescription(objectDto.getDescription());
                    }
                    if (objectDto.getStatus() != null) {
                        object.setStatus(objectDto.getStatus());
                    }
//                    if (objectDto.getType() != null) {
//                        object.setType(objectDto.getType());
//                    }
                    if (objectDto.getName() != null) {
                        object.setName(objectDto.getName());
                    }
                    if (objectDto.getPicture() != null) {
                        object.setPicture(objectDto.getPicture());
                    }
                    if (objectDto.getNormalAppointmentPrice() != null) {
                        object.setNormalAppointmentPrice(objectDto.getNormalAppointmentPrice());
                    }
                    if (objectDto.getExpressAppointmentPrice() != null) {
                        object.setExpressAppointmentPrice(objectDto.getExpressAppointmentPrice());
                    }
                    object.setUpdateAt(ConfigureTimeZone.getTimeZone());
                    return this.repositoryCommand.save(object);
                })
                .orElseThrow(() -> new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Qualification not found."));

    }

    @Override
    public void delete(UUID id) {
    
        ServiceDto objectDelete = this.findById(id);
        objectDelete.setStatus(EServiceStatus.INACTIVE);

        objectDelete.setDeleteAt(ConfigureTimeZone.getTimeZone());

        this.repositoryCommand.save(new Services(objectDelete));
    }

    ///@Cacheable(cacheNames = CacheConfig.SERVICE_CACHE, unless = "#result == null")
    @Override
    public ServiceDto findById(UUID id) {
        
        Optional<Services> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Business not found.");

    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter) {
        BusinessSpecifications specifications = new BusinessSpecifications(idObject, filter);
        Page<Services> data = this.repositoryQuery.findAll(specifications, pageable);

        List<ServicesResponse> objects = new ArrayList<>();
        for (Services o : data.getContent()) {
            objects.add(new ServicesResponse(o.toAggregate()));
        }
        return new PaginatedResponse(objects, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Service> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Services> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Services> data) {
        List<ServicesResponse> patients = new ArrayList<>();
        for (Services s : data.getContent()) {
            patients.add(new ServicesResponse(s.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
