package com.kynsof.patients.infrastructure.services;


import com.kynsof.patients.application.query.allergy.getall.AllergyResponse;
import com.kynsof.patients.domain.dto.AllergyEntityDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.exception.BusinessException;
import com.kynsof.patients.domain.exception.DomainErrorMessage;
import com.kynsof.patients.domain.service.IAllergyService;
import com.kynsof.patients.infrastructure.entity.Allergy;
import com.kynsof.patients.infrastructure.entity.specifications.AllergySpecifications;
import com.kynsof.patients.infrastructure.repositories.command.AllergyWriteDataJPARepository;
import com.kynsof.patients.infrastructure.repositories.query.AllergyReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AllergyServiceImpl implements IAllergyService {

    @Autowired
    private AllergyWriteDataJPARepository repositoryCommand;

    @Autowired
    private AllergyReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(AllergyEntityDto dto) {
        Allergy allergy =this.repositoryCommand.save(new Allergy(dto));
        return allergy.getId();
    }

    @Override
    public UUID update(Allergy dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }
        Allergy entity = this.repositoryCommand.save(dto);
        return entity.getId();
    }


    @Override
    public AllergyEntityDto findById(UUID id) {
        Optional<Allergy> contactInformation = this.repositoryQuery.findById(id);
        if (contactInformation.isPresent()) {
            return contactInformation.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.PATIENTS_NOT_FOUND, "Contact Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID medicalInformationId, String name, String code) {
        AllergySpecifications specifications = new AllergySpecifications(medicalInformationId, name, code);
        Page<Allergy> data = this.repositoryQuery.findAll(specifications, pageable);

        List<AllergyResponse> allergyResponses = new ArrayList<>();
        for (Allergy p : data.getContent()) {
            allergyResponses.add(new AllergyResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        AllergyEntityDto contactInfoDto = this.findById(id);
        contactInfoDto.setStatus(EStatusPatients.INACTIVE);

        this.repositoryCommand.save(new Allergy(contactInfoDto));
    }

}
