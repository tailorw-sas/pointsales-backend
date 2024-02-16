package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.PaginatedResponse;
import com.kynsof.calendar.application.query.QualificationResponse;
import com.kynsof.calendar.domain.dto.EQualificationStatus;
import com.kynsof.calendar.domain.dto.QualificationDto;
import com.kynsof.calendar.domain.exception.BusinessException;
import com.kynsof.calendar.domain.exception.DomainErrorMessage;
import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.calendar.infrastructure.config.redis.CacheConfig;
import com.kynsof.calendar.infrastructure.repository.command.QualificationWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.entity.Qualification;
import com.kynsof.calendar.infrastructure.entity.specifications.QualificationSpecifications;
import com.kynsof.calendar.infrastructure.repository.query.QualificationReadDataJPARepository;
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
public class QualificationServiceImpl implements IQualificationService {

    @Autowired
    private QualificationWriteDataJPARepository repositoryCommand;

    @Autowired
    private QualificationReadDataJPARepository repositoryQuery;

    @Override
    public void create(QualificationDto qualification) {
        qualification.setStatus(EQualificationStatus.ACTIVE);

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of("America/Guayaquil"));
        qualification.setCreateAt(localDateTime);

        this.repositoryCommand.save(new Qualification(qualification));
    }

    @Override
    public void delete(UUID id) {
        QualificationDto objectDelete = this.findById(id);
        objectDelete.setStatus(EQualificationStatus.INACTIVE);

        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime.atZone(ZoneId.of("America/Guayaquil"));
        objectDelete.setDeleteAt(localDateTime);

        this.repositoryCommand.save(new Qualification(objectDelete));
    }

    @Cacheable(cacheNames = CacheConfig.QUALIFICATION_CACHE, unless = "#result == null")
    @Override
    public QualificationDto findById(UUID id) {

        Optional<Qualification> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Qualification not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID idQualification, String filter) {
        QualificationSpecifications specifications = new QualificationSpecifications(idQualification, filter);
        Page<Qualification> data = this.repositoryQuery.findAll(specifications, pageable);

        List<QualificationResponse> qualification = new ArrayList<>();
        for (Qualification q : data.getContent()) {
            qualification.add(new QualificationResponse(q.toAggregate()));
        }
        return new PaginatedResponse(qualification, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void update(QualificationDto qualification) {
        if (qualification.getId() == null || qualification == null) {
            throw new BusinessException(DomainErrorMessage.QUALIFICATION_OR_ID_NULL, "Qualification DTO or ID cannot be null.");
        }

        this.repositoryQuery.findById(qualification.getId())
                .map(object -> {
                    if (qualification.getDescription() != null) {
                        object.setDescription(qualification.getDescription());
                    }
                    if (qualification.getStatus() != null) {
                        object.setStatus(qualification.getStatus());
                    }
                    LocalDateTime localDateTime = LocalDateTime.now();
                    localDateTime.atZone(ZoneId.of("America/Guayaquil"));
                    object.setUpdateAt(localDateTime);
                    return this.repositoryCommand.save(object);
                })
                .orElseThrow(() -> new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Qualification not found."));

    }

}
