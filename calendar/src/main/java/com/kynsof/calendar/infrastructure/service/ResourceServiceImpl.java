package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ResourceResponse;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ResourceWithSchedulesDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.infrastructure.entity.*;
import com.kynsof.calendar.infrastructure.entity.specifications.ResourceSpecifications;
import com.kynsof.calendar.infrastructure.repository.command.BusinessWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.command.ResourceServiceWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.command.ResourceWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.BusinessReadDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ResourceReadDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ScheduleReadDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ServiceReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.share.utils.ConfigureTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceWriteDataJPARepository repositoryCommand;

    @Autowired
    private ResourceReadDataJPARepository repositoryQuery;
    @Autowired

    private ScheduleReadDataJPARepository scheduleReadDataJPARepository;
    @Autowired
    private BusinessReadDataJPARepository businessReadDataJPARepository;
    @Autowired
    private BusinessWriteDataJPARepository businessWriteDataJPARepository;

    @Autowired
    private ServiceReadDataJPARepository serviceReadRepository;

    @Autowired
    private ResourceServiceWriteDataJPARepository resourceServiceWriteDataJPARepository;

    @Override
    public void create(ResourceDto object) {
        object.setStatus(EResourceStatus.ACTIVE);

        this.repositoryCommand.save(new Resource(object));
    }

    @Override
    public void update(ResourceDto objectDto) {
        this.repositoryCommand.save(new Resource(objectDto));
    }

    @Override
    public void delete(UUID id) {

        ResourceDto objectDelete = this.findById(id);
        objectDelete.setStatus(EResourceStatus.INACTIVE);

        this.repositoryCommand.save(new Resource(objectDelete));
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
    public PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter) {
        ResourceSpecifications specifications = new ResourceSpecifications(idObject, filter);
        Page<Resource> data = this.repositoryQuery.findAll(specifications, pageable);

        List<ResourceResponse> objects = new ArrayList<>();
        for (Resource o : data.getContent()) {
            objects.add(new ResourceResponse(o.toAggregate()));
        }
        return new PaginatedResponse(objects, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Resource> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Resource> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
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
    public PaginatedResponse findResourcesWithAvailableSchedules(UUID businessId, UUID serviceId, LocalDate date, Pageable pageable) {
        Page<Schedule> schedules = scheduleReadDataJPARepository.findSchedulesWithStockByBusinessServiceAndDate(businessId, serviceId, date, pageable);

        Map<UUID, List<Schedule>> schedulesByResourceId = schedules.stream()
                .collect(Collectors.groupingBy(s -> s.getResource().getId()));

        List<ResourceWithSchedulesDto> resourcesWithSchedules = new ArrayList<>();
        schedulesByResourceId.forEach((resourceId, schedulesList) -> {
            Resource resource = schedulesList.get(0).getResource();
            List<ScheduleDto> scheduleDtos = schedulesList.stream()
                    .map(Schedule::toAggregate)
                    .collect(Collectors.toList());

            resourcesWithSchedules.add(new ResourceWithSchedulesDto(resource.toAggregate(), scheduleDtos));
        });

        return new PaginatedResponse(resourcesWithSchedules, schedules.getTotalPages(), schedules.getNumberOfElements(),
                schedules.getTotalElements(), schedules.getSize(), schedules.getNumber());
    }

    @Override
    public void addBusiness(UUID businessId, UUID userId, LocalDate date) {
        Optional<Resource> resource = this.repositoryQuery.findById(userId);
        Optional<Business> business = this.businessReadDataJPARepository.findById(businessId);
        if (resource.isPresent() && business.isPresent()) {
            Resource resourceObject = resource.get();
            Business businessObject = business.get();
            BusinessResource businessResource = new BusinessResource();
            businessResource.setBusiness(businessObject);
            businessResource.setResource(resourceObject);
            businessResource.setCreatedAt(ConfigureTimeZone.getTimeZone());  // Asumiendo que hay un campo para la fecha de creación
            businessObject.getBusinessResources().add(businessResource);
            resourceObject.getBusinessResources().add(businessResource);

            this.repositoryCommand.save(resourceObject);
            this.businessReadDataJPARepository.save(businessObject);
        }
    }

    @Override
    public void addServicesToResource(UUID resourceId, List<UUID> serviceIds) {
        Optional<Resource> resource = this.repositoryQuery.findById(resourceId);
        Resource resourceObject = resource.get();
        if (resourceObject.getResourceServices() == null) {
            resourceObject.setResourceServices(new HashSet<>());
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
        Page<Resource> data = this.repositoryQuery.findResourcesByServiceIdAndBusinessId(serviceId,businessId, pageable);
        return getPaginatedResponse(data);
    }

}
