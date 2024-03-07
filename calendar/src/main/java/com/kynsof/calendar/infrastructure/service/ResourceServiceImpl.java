package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ResourceResponse;
import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.calendar.infrastructure.entity.Resource;
import com.kynsof.calendar.infrastructure.entity.specifications.ResourceSpecifications;
import com.kynsof.calendar.infrastructure.repository.command.ResourceWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ResourceReadDataJPARepository;
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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResourceServiceImpl implements IResourceService {

    
    @Autowired
    private ResourceWriteDataJPARepository repositoryCommand;

    @Autowired
    private ResourceReadDataJPARepository repositoryQuery;

    @Override
    public void create(ResourceDto object) {
        object.setStatus(EResourceStatus.ACTIVE);

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of("America/Guayaquil"));
        object.setCreateAt(localDateTime);

        this.repositoryCommand.save(new Resource(object));
    }

    @Override
    public void update(ResourceDto objectDto) {
        if (objectDto.getId() == null || objectDto == null) {
            throw new BusinessException(DomainErrorMessage.BUSINESS_OR_ID_NULL, "Business DTO or ID cannot be null.");
        }

        this.repositoryQuery.findById(objectDto.getId())
                .map(object -> {
                    if (objectDto.getExpressAppointments() != null) {
                        object.setExpressAppointments(objectDto.getExpressAppointments());
                    }
                    if (objectDto.getStatus() != null) {
                        object.setStatus(objectDto.getStatus());
                    }
                    if (objectDto.getLanguage() != null) {
                        object.setLanguage(objectDto.getLanguage());
                    }
                    if (objectDto.getName() != null) {
                        object.setName(objectDto.getName());
                    }
                    if (objectDto.getPicture() != null) {
                        object.setPicture(objectDto.getPicture());
                    }
                    LocalDateTime localDateTime = LocalDateTime.now();
                    localDateTime.atZone(ZoneId.of("America/Guayaquil"));
                    object.setUpdatedAt(localDateTime);
                    return this.repositoryCommand.save(object);
                })
                .orElseThrow(() -> new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Qualification not found."));

    }

    @Override
    public void delete(UUID id) {

        ResourceDto objectDelete = this.findById(id);
        objectDelete.setStatus(EResourceStatus.INACTIVE);

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of("America/Guayaquil"));
        objectDelete.setDeleteAt(localDateTime);
        objectDelete.setDeleted(true);

        this.repositoryCommand.save(new Resource(objectDelete));
    }

    @Cacheable(cacheNames = CacheConfig.RESOURCE_CACHE, unless = "#result == null")
    @Override
    public ResourceDto findById(UUID id) {

        Optional<Resource> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.RESOURCE_NOT_FOUND, "Resource not found.");

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

}
