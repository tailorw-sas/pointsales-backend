package com.kynsof.scheduled.infrastructure.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.query.ServicesResponse;
import com.kynsof.scheduled.domain.dto.EServiceStatus;
import com.kynsof.scheduled.domain.dto.ServiceDto;
import com.kynsof.scheduled.domain.exception.BusinessException;
import com.kynsof.scheduled.domain.exception.DomainErrorMessage;
import com.kynsof.scheduled.domain.service.IServiceService;
import com.kynsof.scheduled.infrastructure.config.redis.CacheConfig;
import com.kynsof.scheduled.infrastructure.repository.command.ServiceWriteDataJPARepository;
import com.kynsof.scheduled.infrastructure.entity.Services;
import com.kynsof.scheduled.infrastructure.entity.specifications.BusinessSpecifications;
import com.kynsof.scheduled.infrastructure.repository.query.ServiceReadDataJPARepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceServiceImpl implements IServiceService {

    @Autowired
    private ServiceWriteDataJPARepository repositoryCommand;

    @Autowired
    private ServiceReadDataJPARepository repositoryQuery;

    @Override
    public void create(ServiceDto object) {
        object.setStatus(EServiceStatus.ACTIVE);

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of("America/Guayaquil"));
        object.setCreateAt(localDateTime);

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
                    if (objectDto.getType() != null) {
                        object.setType(objectDto.getType());
                    }
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
                    LocalDateTime localDateTime = LocalDateTime.now();
                    localDateTime.atZone(ZoneId.of("America/Guayaquil"));
                    object.setUpdateAt(localDateTime);
                    return this.repositoryCommand.save(object);
                })
                .orElseThrow(() -> new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Qualification not found."));

    }

    @Override
    public void delete(UUID id) {
    
        ServiceDto objectDelete = this.findById(id);
        objectDelete.setStatus(EServiceStatus.INACTIVE);

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of("America/Guayaquil"));
        objectDelete.setDeleteAt(localDateTime);

        this.repositoryCommand.save(new Services(objectDelete));
    }

    @Cacheable(cacheNames = CacheConfig.QUALIFICATION_CACHE, unless = "#result == null")
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
    
}
