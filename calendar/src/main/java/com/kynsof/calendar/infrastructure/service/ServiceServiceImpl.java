package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ServicesResponse;
import com.kynsof.calendar.domain.dto.ServiceDto;
import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.calendar.domain.service.IServiceService;
import com.kynsof.calendar.infrastructure.entity.Services;
import com.kynsof.calendar.infrastructure.entity.specifications.BusinessSpecifications;
import com.kynsof.calendar.infrastructure.repository.command.ServiceWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ServiceReadDataJPARepository;
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
public class ServiceServiceImpl implements IServiceService {

    @Autowired
    private ServiceWriteDataJPARepository repositoryCommand;

    @Autowired
    private ServiceReadDataJPARepository repositoryQuery;

    @Override
    public void create(ServiceDto object) {
        object.setStatus(EServiceStatus.ACTIVE);
        this.repositoryCommand.save(new Services(object));
    }

    @Override
    public void update(ServiceDto objectDto) {
        this.repositoryCommand.save(new Services(objectDto));
    }

    @Override
    public void delete(UUID id) {
    
        ServiceDto objectDelete = this.findById(id);
        objectDelete.setStatus(EServiceStatus.INACTIVE);

        this.repositoryCommand.save(new Services(objectDelete));
    }

    ///@Cacheable(cacheNames = CacheConfig.SERVICE_CACHE, unless = "#result == null")
    @Override
    public ServiceDto findById(UUID id) {
        
        Optional<Services> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SERVICE_NOT_FOUND, new ErrorField("id", "Service not found.")));

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

    @Override
    public Long countByNameAndNotId(String name, UUID id) {
        return this.repositoryQuery.countByNameAndNotId(name, id);
    }

    @Override
    public PaginatedResponse findServicesByResourceId(Pageable pageable, UUID resourceId) {
        Page<Services> services = this.repositoryQuery.findServicesByResourceId(resourceId, pageable);
        return getPaginatedResponse(services);
    }

}
