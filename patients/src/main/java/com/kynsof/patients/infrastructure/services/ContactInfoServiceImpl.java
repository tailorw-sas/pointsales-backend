package com.kynsof.patients.infrastructure.services;

import com.kynsof.patients.application.query.contactInfo.getall.ContactInfoResponse;
import com.kynsof.patients.domain.dto.ContactInfoDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.exception.BusinessException;
import com.kynsof.patients.domain.exception.DomainErrorMessage;
import com.kynsof.patients.domain.service.IContactInfoService;
import com.kynsof.patients.infrastructure.entity.ContactInformation;
import com.kynsof.patients.infrastructure.entity.specifications.ContactInfoSpecifications;
import com.kynsof.patients.infrastructure.repositories.command.ContactInfoWriteDataJPARepository;
import com.kynsof.patients.infrastructure.repositories.query.ContactInfoReadDataJPARepository;
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
public class ContactInfoServiceImpl implements IContactInfoService {

    @Autowired
    private ContactInfoWriteDataJPARepository repositoryCommand;

    @Autowired
    private ContactInfoReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(ContactInfoDto dto) {
        this.repositoryCommand.save(new ContactInformation(dto));
        return dto.getId();
    }

    @Override
    public UUID update(ContactInfoDto contactInfoDto) {
        if (contactInfoDto == null || contactInfoDto.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }

        this.repositoryQuery.findById(contactInfoDto.getId())
                .map(patient -> {
                    if (contactInfoDto.getAddress() != null) patient.setAddress(contactInfoDto.getAddress());
                    if (contactInfoDto.getTelephone() != null) patient.setTelephone(contactInfoDto.getTelephone());
                    if (contactInfoDto.getBirthdayDate() != null) patient.setBirthdayDate(contactInfoDto.getBirthdayDate());
                    if (contactInfoDto.getPatient() != null) patient.setPatient(contactInfoDto.getPatient());

                    return this.repositoryCommand.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + contactInfoDto.getId() + " not found"));

        return contactInfoDto.getId();
    }


    @Override
    public ContactInfoDto findById(UUID id) {
        Optional<ContactInformation> contactInformation = this.repositoryQuery.findById(id);
        if (contactInformation.isPresent()) {
            return contactInformation.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.PATIENTS_NOT_FOUND, "Contact Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID idPatients, String email, String phone) {
        ContactInfoSpecifications specifications = new ContactInfoSpecifications(idPatients, email);
        Page<ContactInformation> data = this.repositoryQuery.findAll(specifications, pageable);

        List<ContactInfoResponse> patients = new ArrayList<>();
        for (ContactInformation p : data.getContent()) {
            patients.add(new ContactInfoResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        ContactInfoDto contactInfoDto = this.findById(id);
        contactInfoDto.setStatus(EStatusPatients.INACTIVE);
        
        this.repositoryCommand.save(new ContactInformation(contactInfoDto));
    }

}
