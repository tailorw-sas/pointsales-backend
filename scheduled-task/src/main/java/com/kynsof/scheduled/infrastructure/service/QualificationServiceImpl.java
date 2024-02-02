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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public QualificationDto findById(UUID id) {

        Optional<Qualification> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }
        //throw new RuntimeException("Patients not found.");
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

}
