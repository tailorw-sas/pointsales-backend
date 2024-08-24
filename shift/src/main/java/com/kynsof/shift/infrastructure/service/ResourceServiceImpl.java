package com.kynsof.shift.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.share.utils.ConfigureTimeZone;
import com.kynsof.shift.application.query.ResourceResponse;
import com.kynsof.shift.domain.dto.ResourceDto;
import com.kynsof.shift.domain.dto.ServiceDto;
import com.kynsof.shift.domain.dto.enumType.EResourceStatus;
import com.kynsof.shift.domain.service.IResourceService;
import com.kynsof.shift.infrastructure.entity.*;
import com.kynsof.shift.infrastructure.repository.command.BusinessResourceWriteDataJPARepository;
import com.kynsof.shift.infrastructure.repository.command.ResourceServiceWriteDataJPARepository;
import com.kynsof.shift.infrastructure.repository.command.ResourceWriteDataJPARepository;
import com.kynsof.shift.infrastructure.repository.query.BusinessReadDataJPARepository;
import com.kynsof.shift.infrastructure.repository.query.ResourceReadDataJPARepository;
import com.kynsof.shift.infrastructure.repository.query.ResourceServicesReadDataJPARepository;
import com.kynsof.shift.infrastructure.repository.query.ServiceReadDataJPARepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResourceServiceImpl implements IResourceService {

    private final ResourceWriteDataJPARepository repositoryCommand;
    private final ResourceReadDataJPARepository repositoryQuery;

    private final BusinessReadDataJPARepository businessReadDataJPARepository;
    private final ServiceReadDataJPARepository serviceReadRepository;
    private final ResourceServiceWriteDataJPARepository resourceServiceWriteDataJPARepository;
    private final ResourceServicesReadDataJPARepository resourceServiceReadDataJPARepository;
    private final BusinessResourceWriteDataJPARepository businessResourceWriteDataJPARepository;

    public ResourceServiceImpl(ResourceWriteDataJPARepository repositoryCommand, ResourceReadDataJPARepository repositoryQuery,
                               BusinessReadDataJPARepository businessReadDataJPARepository,
                               ServiceReadDataJPARepository serviceReadRepository,
                               ResourceServiceWriteDataJPARepository resourceServiceWriteDataJPARepository,
                               ResourceServicesReadDataJPARepository resourceServiceReadDataJPARepository,
                               BusinessResourceWriteDataJPARepository businessResourceWriteDataJPARepository) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;

        this.businessReadDataJPARepository = businessReadDataJPARepository;
        this.serviceReadRepository = serviceReadRepository;
        this.resourceServiceWriteDataJPARepository = resourceServiceWriteDataJPARepository;
        this.resourceServiceReadDataJPARepository = resourceServiceReadDataJPARepository;
        this.businessResourceWriteDataJPARepository = businessResourceWriteDataJPARepository;
    }

    @Override
    public void create(ResourceDto object) {
        object.setStatus(EResourceStatus.ACTIVE);

        this.repositoryCommand.save(new Resource(object));
    }

    @Override
    public void update(ResourceDto objectDto) {
        Resource update = new Resource(objectDto);
        update.setUpdatedAt(LocalDateTime.now());
        this.repositoryCommand.save(new Resource(objectDto));
    }

    @Override
    public void delete(UUID id) {
        try {
            this.repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

//    @Cacheable(cacheNames = CacheConfig.RESOURCE_CACHE, unless = "#result == null")
    @Override
    public ResourceDto findById(UUID id) {

        Optional<Resource> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.RESOURCE_NOT_FOUND, new ErrorField("id", "Resource not found.")));

    }

    @Override
    public ResourceDto findByCode(String code) {
        Optional<Resource> object = this.repositoryQuery.findResourceByExternalCode(code);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.RESOURCE_NOT_FOUND, new ErrorField("code", "Resource not found.")));
    }

    @Override
    public boolean existResourceByCode(String code) {
        return repositoryQuery.existsResourceByExternalCode(code);
    }


    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Resource> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Resource> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse findResourcesWithAvailableSchedules(UUID businessId, UUID serviceId, LocalDate date, Pageable pageable) {
        return null;
    }

    private PaginatedResponse getPaginatedResponse(Page<Resource> data) {
        List<ResourceResponse> patients = new ArrayList<>();
        for (Resource s : data.getContent()) {
            patients.add(new ResourceResponse(s.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }



    @Override
    public void addBusiness(UUID businessId, UUID userId, LocalDate date) {
        Optional<Resource> resource = this.repositoryQuery.findById(userId);
        Optional<Business> business = this.businessReadDataJPARepository.findById(businessId);
        if (resource.isPresent() && business.isPresent()) {
            Resource resourceObject = resource.get();
            Business businessObject = business.get();

            BusinessResource businessResource = new BusinessResource();
            businessResource.setId(UUID.randomUUID());
            businessResource.setBusiness(businessObject);
            businessResource.setResource(resourceObject);
            businessResource.setCreatedAt(ConfigureTimeZone.getTimeZone());  // Asumiendo que hay un campo para la fecha de creación
            this.businessResourceWriteDataJPARepository.save(businessResource);

//            businessObject.getBusinessResources().add(businessResource);
//            resourceObject.getBusinessResources().add(businessResource);
//
//            this.repositoryCommand.save(resourceObject);
//            this.businessReadDataJPARepository.save(businessObject);
        }
    }

    @Override
    public void addServicesToResource(UUID resourceId, List<UUID> serviceIds) {
        Optional<Resource> resource = this.repositoryQuery.findById(resourceId);
        Resource resourceObject = resource.get();

        List<UUID> resourceServices = this.resourceServiceReadDataJPARepository.FindResourceServiceByResourceId(resourceId);

        if (!resourceServices.isEmpty()) {
            this.resourceServiceWriteDataJPARepository.deleteAllById(resourceServices);
        }
        List<Services> servicesToAdd = this.serviceReadRepository.findAllById(serviceIds);

        // Assuming ResourceService is the joining entity and is correctly mapped in Resource and Services entities
        servicesToAdd.forEach(service -> {
            ResourceService resourceService = new ResourceService();
            resourceService.setService(service);
            resourceService.setResource(resourceObject);
            resourceService.setCreationDate(ConfigureTimeZone.getTimeZone());

            this.resourceServiceWriteDataJPARepository.save(resourceService);
            //  this.serviceWriteDataJPARepository.save(service);
        });

    }

    @Override
    public PaginatedResponse findResourcesByServiceId(UUID businessId, UUID serviceId, Pageable pageable) {
        Page<Resource> data = this.repositoryQuery.findResourcesByServiceIdAndBusinessId(serviceId, businessId, pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public List<ServiceDto> getAllServicesByResourceAndBusiness(UUID resourceId, UUID businessId) {
        List<Services> services = repositoryQuery.findAllServicesByResourceAndBusiness(resourceId, businessId);
        return services.stream().map(Services::toAggregate).toList();
    }

}
