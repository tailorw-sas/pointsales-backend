package com.kynsof.patients.infrastructure.services;

import com.kynsof.patients.application.query.additionalInfo.getall.AdditionalInfoResponse;
import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import com.kynsof.patients.infrastructure.entity.AdditionalInformation;
import com.kynsof.patients.infrastructure.repository.command.AdditionaltInfoWriteDataJPARepository;
import com.kynsof.patients.infrastructure.repository.query.AdditionalInfoReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
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
public class AdditionalInfoServiceImpl implements IAdditionalInfoService {

    @Autowired
    private AdditionaltInfoWriteDataJPARepository repositoryCommand;

    @Autowired
    private AdditionalInfoReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(AdditionalInformationDto dto) {
       AdditionalInformation additionalInformation = this.repositoryCommand.save(new AdditionalInformation(dto));
        return additionalInformation.getId();
    }

    @Override
    public UUID update(AdditionalInformationDto additionalInformationDto) {
        if (additionalInformationDto == null || additionalInformationDto.getId() == null) {
            throw new IllegalArgumentException("Additional Information cannot be null");
        }

        this.repositoryQuery.findById(additionalInformationDto.getId())
                .map(additionalInformation -> {
                    if (additionalInformationDto.getMaritalStatus() != null)
                        additionalInformation.setMaritalStatus(additionalInformationDto.getMaritalStatus());
                    if (additionalInformationDto.getOccupation() != null)
                        additionalInformation.setOccupation(additionalInformationDto.getOccupation());
                    if (additionalInformationDto.getEmergencyContactName() != null)
                        additionalInformation.setEmergencyContactName(additionalInformationDto.getEmergencyContactName());
                    if (additionalInformationDto.getEmergencyContactPhone() != null)
                        additionalInformation.setEmergencyContactPhone(additionalInformationDto.getEmergencyContactPhone());
                    if (additionalInformationDto.getPatient() != null)
                        additionalInformation.setPatient(additionalInformationDto.getPatient());

                    return this.repositoryCommand.save(additionalInformation);
                })
                .orElseThrow(() -> new EntityNotFoundException("Additional Information with ID " + additionalInformationDto.getId() + " not found"));

        return additionalInformationDto.getId();
    }


    @Override
    public AdditionalInformationDto findById(UUID id) {
        Optional<AdditionalInformation> contactInformation = this.repositoryQuery.findById(id);
        if (contactInformation.isPresent()) {
            return contactInformation.get().toAggregate();
        }
        throw new RuntimeException("Patients not found.");
    }
    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<AdditionalInformation> data = this.repositoryQuery.findAll( pageable);

        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<AdditionalInformation> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<AdditionalInformation> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<AdditionalInformation> data) {
        List<AdditionalInfoResponse> patients = new ArrayList<>();
        for (AdditionalInformation p : data.getContent()) {
            patients.add(new AdditionalInfoResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        try {
            this.repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

}
