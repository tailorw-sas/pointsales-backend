package com.kynsof.scheduled.infrastructure.service;

import com.kynsof.scheduled.application.PaginatedResponse;
import com.kynsof.scheduled.application.query.QualificationResponse;
import com.kynsof.scheduled.domain.dto.EQualificationStatus;
import com.kynsof.scheduled.domain.dto.QualificationDto;
import com.kynsof.scheduled.domain.exception.BusinessException;
import com.kynsof.scheduled.domain.exception.DomainErrorMessage;
import com.kynsof.scheduled.domain.service.IQualificationService;
import com.kynsof.scheduled.infrastructure.command.QualificationWriteDataJPARepository;
import com.kynsof.scheduled.infrastructure.entity.Qualification;
import com.kynsof.scheduled.infrastructure.entity.specifications.QualificationSpecifications;
import com.kynsof.scheduled.infrastructure.query.QualificationReadDataJPARepository;
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
