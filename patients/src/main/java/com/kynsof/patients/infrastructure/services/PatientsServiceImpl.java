package com.kynsof.patients.infrastructure.services;

import com.kynsof.patients.application.query.patients.getall.PatientsResponse;
import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PatientByIdDto;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.entity.Insurance;
import com.kynsof.patients.infrastructure.entity.Patients;
import com.kynsof.patients.infrastructure.repository.command.PatientsWriteDataJPARepository;
import com.kynsof.patients.infrastructure.repository.query.InsuranceReadDataJPARepository;
import com.kynsof.patients.infrastructure.repository.query.PatientsReadDataJPARepository;
import com.kynsof.patients.infrastructure.services.kafka.producer.ProducerPatientsEventService;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.redis.CacheConfig;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientsServiceImpl implements IPatientsService {

    @Autowired
    private PatientsWriteDataJPARepository repositoryCommand;

    @Autowired
    private PatientsReadDataJPARepository repositoryQuery;

    @Autowired
    private InsuranceReadDataJPARepository insuranceReadDataJPARepository;

    @Autowired
    private ProducerPatientsEventService patientEventService;

    @Override
    public UUID create(PatientDto patients) {
        Patients entity = this.repositoryCommand.save(new Patients(patients));
        this.patientEventService.create(patients);
        return entity.getId();
    }

    @Override
    public UUID createDependent(DependentPatientDto patients) {
        Patients entity = this.repositoryCommand.save(new Patients(patients));
        return entity.getId();
    }

    @Override
    public UUID update(PatientDto patientDto) {
        if (patientDto == null || patientDto.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }

        this.repositoryQuery.findById(patientDto.getId())
                .map(patient -> {
                    if (patientDto.getName() != null) {
                        patient.setName(patientDto.getName());
                    }
                    if (patientDto.getLastName() != null) {
                        patient.setLastName(patientDto.getLastName());
                    }
                    if (patientDto.getIdentification() != null) {
                        patient.setIdentification(patientDto.getIdentification());
                    }
                    if (patientDto.getGender() != null) {
                        patient.setGender(patientDto.getGender());
                    }
                    if (patientDto.getStatus() != null) {
                        patient.setStatus(patientDto.getStatus());
                    }
                    if (patientDto.getWeight() != null) {
                        patient.setWeight(patientDto.getWeight());
                    }
                    if (patientDto.getHeight() != null) {
                        patient.setHeight(patientDto.getHeight());
                    }
                    if (patientDto.getHasDisability() != null) {
                        patient.setHasDisability(patientDto.getHasDisability());
                    }
                    if (patientDto.getIsPregnant() != null) {
                        patient.setIsPregnant(patientDto.getIsPregnant());
                    }

                    if (patientDto.getGestationTime()> 0) {
                        patient.setGestationTime(patientDto.getGestationTime());
                    }

                    if (patientDto.getDisabilityType() != null) {
                        patient.setDisabilityType(patientDto.getDisabilityType());
                    }

                    if (patientDto.getPhoto() != null) {
                        patient.setPhoto(patientDto.getPhoto());
                    }

                    return this.repositoryCommand.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + patientDto.getId() + " not found"));

        return patientDto.getId();
    }


    @Override
    public void updateDependent(DependentPatientDto dependentPatientDto) {
        if (dependentPatientDto == null || dependentPatientDto.getId() == null) {
            throw new IllegalArgumentException("DependentPatient DTO or ID cannot be null");
        }

        this.repositoryQuery.findById(dependentPatientDto.getId())
                .map(dependentPatient -> {
                    if (dependentPatientDto.getName() != null) {
                        dependentPatient.setName(dependentPatientDto.getName());
                    }
                    if (dependentPatientDto.getLastName() != null) {
                        dependentPatient.setLastName(dependentPatientDto.getLastName());
                    }
                    if (dependentPatientDto.getIdentification() != null) {
                        dependentPatient.setIdentification(dependentPatientDto.getIdentification());
                    }
                    if (dependentPatientDto.getGender() != null) {
                        dependentPatient.setGender(dependentPatientDto.getGender());
                    }
                    if (dependentPatientDto.getStatus() != null) {
                        dependentPatient.setStatus(dependentPatientDto.getStatus());
                    }
                    if (dependentPatientDto.getWeight() != null) {
                        dependentPatient.setWeight(dependentPatientDto.getWeight());
                    }
                    if (dependentPatientDto.getHeight() != null) {
                        dependentPatient.setHeight(dependentPatientDto.getHeight());
                    }
                    if (dependentPatientDto.getHasDisability() != null) {
                        dependentPatient.setHasDisability(dependentPatientDto.getHasDisability());
                    }
                    if (dependentPatientDto.getIsPregnant() != null) {
                        dependentPatient.setIsPregnant(dependentPatientDto.getIsPregnant());
                    }
                    if (dependentPatientDto.getFamilyRelationship() != null) {
                        dependentPatient.setFamilyRelationship(dependentPatientDto.getFamilyRelationship());
                    }
                    // Considera cómo actualizar la información del paciente principal (prime) si es necesario.
                    // Esto puede implicar buscar el paciente principal por su ID y establecer la relación adecuadamente.

                    return this.repositoryCommand.save(dependentPatient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Dependent patient with ID " + dependentPatientDto.getId() + " not found"));

    }

    @Override
    public PatientByIdDto findById(UUID id) {
        Optional<Patients> patient = this.repositoryQuery.findById(id);
        if (patient.isPresent()) {
            PatientByIdDto patientByIdDto =  patient.get().toAggregateById();
            return patientByIdDto;
        }
      throw new RuntimeException("Patients not found.");

    }

    @Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null")
    @Override
    public PatientDto findByIdSimple(UUID id) {
        Optional<Patients> patient = this.repositoryQuery.findById(id);
        if (patient.isPresent()) {
            return patient.get().toAggregate();
        }
        throw new RuntimeException("Patients not found.");

    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<Patients> data = this.repositoryQuery.findAll(pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Patients> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Patients> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Patients> data) {
        List<PatientsResponse> patients = new ArrayList<>();
        for (Patients p : data.getContent()) {
            patients.add(new PatientsResponse(p.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void createInsurance(UUID patientId, List<UUID> insuranceIds) {
        Optional<Patients> patientOpt = repositoryQuery.findById(patientId);
        if (patientOpt.isEmpty()) {
            throw new RuntimeException("Paciente no encontrado");
        }
        Patients patient = patientOpt.get();

        List<Insurance> insurances = insuranceReadDataJPARepository.findAllById(insuranceIds);
        if (insurances.size() != insuranceIds.size()) {
            throw new RuntimeException("Una o más aseguradoras no encontradas");
        }
        patient.setInsurances(insurances);
        repositoryCommand.save(patient);

    }

    @Override
    public PatientDto findByIdentification(String identification) {
        Optional<Patients> patient = this.repositoryQuery.findByIdentification(identification);
        if (patient.isPresent()) {
            return patient.get().toAggregate();
        }
        throw new RuntimeException("Patients not found.");
    }

    @Override
    public void delete(UUID id) {
        Optional<Patients> patient = this.repositoryQuery.findById(id);
        PatientDto patientDto = patient.get().toAggregate();
        patientDto.setStatus(Status.INACTIVE);

        this.repositoryCommand.save(new Patients(patientDto));
    }

}
