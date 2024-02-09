package com.kynsof.scheduled.infrastructure.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.query.BusinessResponse;
import com.kynsof.scheduled.domain.dto.BusinessDto;
import com.kynsof.scheduled.domain.dto.EBusinessStatus;
import com.kynsof.scheduled.domain.exception.BusinessException;
import com.kynsof.scheduled.domain.exception.DomainErrorMessage;
import com.kynsof.scheduled.domain.service.IBusinessService;
import com.kynsof.scheduled.infrastructure.repository.command.BusinessWriteDataJPARepository;
import com.kynsof.scheduled.infrastructure.entity.Business;
import com.kynsof.scheduled.infrastructure.entity.specifications.BusinessSpecifications;
import com.kynsof.scheduled.infrastructure.repository.query.BusinessReadDataJPARepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements IBusinessService {

    
    @Autowired
    private BusinessWriteDataJPARepository repositoryCommand;

    @Autowired
    private BusinessReadDataJPARepository repositoryQuery;

    @Override
    public void create(BusinessDto object) {
        object.setStatus(EBusinessStatus.ACTIVE);

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of("America/Guayaquil"));
        object.setCreateAt(localDateTime);

        this.repositoryCommand.save(new Business(object));
    }

    @Override
    public void update(BusinessDto objectDto) {
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
                    if (objectDto.getLogo() != null) {
                        object.setLogo(objectDto.getLogo());
                    }
                    if (objectDto.getName() != null) {
                        object.setName(objectDto.getName());
                    }
                    if (objectDto.getRuc() != null) {
                        object.setRuc(objectDto.getRuc());
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
    
        BusinessDto objectDelete = this.findById(id);
        objectDelete.setStatus(EBusinessStatus.INACTIVE);

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of("America/Guayaquil"));
        objectDelete.setDeleteAt(localDateTime);

        this.repositoryCommand.save(new Business(objectDelete));
    }

    @Override
    public BusinessDto findById(UUID id) {
        
        Optional<Business> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Business not found.");

    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID idObject, String filter) {
        BusinessSpecifications specifications = new BusinessSpecifications(idObject, filter);
        Page<Business> data = this.repositoryQuery.findAll(specifications, pageable);

        List<BusinessResponse> objects = new ArrayList<>();
        for (Business o : data.getContent()) {
            objects.add(new BusinessResponse(o.toAggregate()));
        }
        return new PaginatedResponse(objects, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
    
}
