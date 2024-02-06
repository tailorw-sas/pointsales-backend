package com.kynsof.patients.infrastructure.services;
import com.kynsof.patients.application.query.currentMedication.getall.CurrentMedicationResponse;
import com.kynsof.patients.domain.dto.CurrentMerdicationEntityDto;
import com.kynsof.patients.domain.dto.Status;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.exception.BusinessException;
import com.kynsof.patients.domain.exception.DomainErrorMessage;
import com.kynsof.patients.domain.service.ICurrentMedicationService;
import com.kynsof.patients.infrastructure.entity.CurrentMedication;
import com.kynsof.patients.infrastructure.entity.specifications.CurrentMedicationSpecifications;
import com.kynsof.patients.infrastructure.repositories.command.CurrentMedicationWriteDataJPARepository;
import com.kynsof.patients.infrastructure.repositories.query.CurrentMedicationReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CurrentMedicationServiceImpl implements ICurrentMedicationService {

    @Autowired
    private CurrentMedicationWriteDataJPARepository repositoryCommand;

    @Autowired
    private CurrentMedicationReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(CurrentMerdicationEntityDto dto) {
       CurrentMedication currentMedication = this.repositoryCommand.save(new CurrentMedication(dto));
        return currentMedication.getId();
    }

    @Override
    public UUID update(CurrentMedication dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }
        CurrentMedication entity = this.repositoryCommand.save(dto);

        return dto.getId();
    }


    @Override
    public CurrentMerdicationEntityDto findById(UUID id) {
        Optional<CurrentMedication> currenMedication = this.repositoryQuery.findById(id);
        if (currenMedication.isPresent()) {
            return currenMedication.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.PATIENTS_NOT_FOUND, "Contact Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID medicalInformationId, String name) {
        CurrentMedicationSpecifications specifications = new CurrentMedicationSpecifications(medicalInformationId, name);
        Page<CurrentMedication> data = this.repositoryQuery.findAll(specifications, pageable);

        List<CurrentMedicationResponse> currentMedicationResponses = new ArrayList<>();
        for (CurrentMedication p : data.getContent()) {
            currentMedicationResponses.add(new CurrentMedicationResponse(p.toAggregate()));
        }
        return new PaginatedResponse(currentMedicationResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        CurrentMerdicationEntityDto currentMerdicationEntityDto = this.findById(id);
        currentMerdicationEntityDto.setStatus(Status.INACTIVE);

        this.repositoryCommand.save(new CurrentMedication(currentMerdicationEntityDto));
    }

}
