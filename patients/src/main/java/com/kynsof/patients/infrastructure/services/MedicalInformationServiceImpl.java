package com.kynsof.patients.infrastructure.services;

import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.dto.MedicalInformationUpdateDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import com.kynsof.patients.infrastructure.entity.MedicalInformation;
import com.kynsof.patients.infrastructure.repositories.command.MedicalInformationWriteDataJPARepository;
import com.kynsof.patients.infrastructure.repositories.query.MedicalInformationReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicalInformationServiceImpl implements IMedicalInformationService {

    @Autowired
    private MedicalInformationWriteDataJPARepository repositoryCommand;

    @Autowired
    private MedicalInformationReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(MedicalInformationDto dto) {
        try {
            MedicalInformation medicalInformation = this.repositoryCommand.save(new MedicalInformation(dto));
            return medicalInformation.getId();
        } catch (Exception e) {
            String message = e.getMessage();
            throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Medical Information not found.");
        }
    }

    @Override
    public UUID update(MedicalInformationUpdateDto medicalInformationDto) {
        if (medicalInformationDto == null || medicalInformationDto.getId() == null) {
            throw new IllegalArgumentException("Medical Information cannot be null or have a null ID.");
        }

        MedicalInformation medicalInformation = repositoryQuery.findById(medicalInformationDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Medical Information with ID " + medicalInformationDto.getId() + " not found"));

        // Actualizar campos simples solo si no son null
        if (medicalInformationDto.getBloodType() != null) {
            medicalInformation.setBloodType(medicalInformationDto.getBloodType());
        }
        if (medicalInformationDto.getMedicalHistory() != null) {
            medicalInformation.setMedicalHistory(medicalInformationDto.getMedicalHistory());
        }

        if (medicalInformationDto.getStatus() != null) {
            medicalInformation.setStatus(medicalInformationDto.getStatus());
        }

        repositoryCommand.save(medicalInformation);

        return medicalInformation.getId();
    }


    @Override
    public MedicalInformationDto findById(UUID id) {
        Optional<MedicalInformation> medicalInformation = this.repositoryQuery.findById(id);
        if (medicalInformation.isPresent()) {
            return medicalInformation.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Medical Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<MedicalInformation> data = this.repositoryQuery.findAll(pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<MedicalInformation> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<MedicalInformation> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<MedicalInformation> data) {
        List<MedicalInformationDto> dtoList = new ArrayList<>();
        for (MedicalInformation medicalInformation : data.getContent()) {
            dtoList.add(medicalInformation.toAggregate());
        }
        return new PaginatedResponse(dtoList, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        MedicalInformationDto medicalInformationDto = this.findById(id);
        medicalInformationDto.setStatus(Status.INACTIVE);
        this.repositoryCommand.save(new MedicalInformation(medicalInformationDto));
    }

}
