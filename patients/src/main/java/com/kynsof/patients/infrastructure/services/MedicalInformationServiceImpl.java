package com.kynsof.patients.infrastructure.services;

import com.kynsof.patients.application.query.additionalInfo.getall.AdditionalInfoResponse;
import com.kynsof.patients.application.query.medicalInformation.getall.MedicalInformationResponse;
import com.kynsof.patients.domain.dto.AdditionalInformationDto;
import com.kynsof.patients.domain.dto.EStatusPatients;
import com.kynsof.patients.domain.dto.MedicalInformationDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.exception.BusinessException;
import com.kynsof.patients.domain.exception.DomainErrorMessage;
import com.kynsof.patients.domain.service.IAdditionalInfoService;
import com.kynsof.patients.domain.service.IMedicalInformationService;
import com.kynsof.patients.infrastructure.entity.AdditionalInformation;
import com.kynsof.patients.infrastructure.entity.Allergy;
import com.kynsof.patients.infrastructure.entity.CurrentMedication;
import com.kynsof.patients.infrastructure.entity.MedicalInformation;
import com.kynsof.patients.infrastructure.entity.specifications.AdditionalInfoSpecifications;
import com.kynsof.patients.infrastructure.entity.specifications.MedicalInformationSpecifications;
import com.kynsof.patients.infrastructure.repositories.command.AdditionaltInfoWriteDataJPARepository;
import com.kynsof.patients.infrastructure.repositories.command.MedicalInformationWriteDataJPARepository;
import com.kynsof.patients.infrastructure.repositories.query.AdditionalInfoReadDataJPARepository;
import com.kynsof.patients.infrastructure.repositories.query.MedicalInformationReadDataJPARepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicalInformationServiceImpl implements IMedicalInformationService {

    @Autowired
    private MedicalInformationWriteDataJPARepository repositoryCommand;

    @Autowired
    private MedicalInformationReadDataJPARepository repositoryQuery;

    @Override
    public UUID create(MedicalInformationDto dto) {
        try {
            this.repositoryCommand.save(new MedicalInformation(dto));
            return dto.getId();
        }catch (Exception e){
            String message = e.getMessage();
            throw new BusinessException(DomainErrorMessage.PATIENTS_NOT_FOUND, "Medical Information not found.");
        }
    }

    @Override
    public UUID update(MedicalInformationDto medicalInformationDto) {
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

        // Actualizar el paciente solo si patientId no es null
        if (medicalInformationDto.getPatientId() != null) {
//            Patient patient = patientRepository.findById(medicalInformationDto.getPatientId())
//                    .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + medicalInformationDto.getPatientId() + " not found"));
//            medicalInformation.setPatient(patient);
        }

        // Actualizar las alergias solo si la lista no es null
        if (medicalInformationDto.getAllergies() != null) {
            List<Allergy> allergies = medicalInformationDto.getAllergies().stream()
                    .map(allergyDto -> {
                        Allergy allergy = new Allergy(); // Ajusta según tu implementación
                        allergy.setCode(allergyDto.getCode());
                        allergy.setName(allergyDto.getName());
                        allergy.setMedicalInformation(medicalInformation); // Establecer la relación inversa
                        return allergy;
                    })
                    .collect(Collectors.toList());
            medicalInformation.setAllergies(allergies);
        }

        // Actualizar los medicamentos actuales solo si la lista no es null
        if (medicalInformationDto.getCurrentMedications() != null) {
            List<CurrentMedication> currentMedications = medicalInformationDto.getCurrentMedications().stream()
                    .map(medicationDto -> {
                        CurrentMedication medication = new CurrentMedication(); // Ajusta según tu implementación
                        medication.setName(medicationDto.getName());
                        medication.setDosage(medicationDto.getDosage());
                        medication.setMedicalInformation(medicalInformation); // Establecer la relación inversa
                        return medication;
                    })
                    .collect(Collectors.toList());
            medicalInformation.setCurrentMedications(currentMedications);
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
        throw new BusinessException(DomainErrorMessage.PATIENTS_NOT_FOUND, "Medical Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID idPatients) {
        MedicalInformationSpecifications specifications = new MedicalInformationSpecifications(idPatients);
        Page<MedicalInformation> data = this.repositoryQuery.findAll(specifications, pageable);

        List<MedicalInformation> medicalInformations = new ArrayList<>();
        for (MedicalInformation p : data.getContent()) {
            medicalInformations.add(new MedicalInformation(p.toAggregate()));
        }
        return new PaginatedResponse(medicalInformations, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void delete(UUID id) {
        MedicalInformationDto medicalInformationDto = this.findById(id);
        medicalInformationDto.setStatus(EStatusPatients.INACTIVE);
        this.repositoryCommand.save(new MedicalInformation(medicalInformationDto));
    }

}
