package com.kynsof.patients.infrastructure.services;

import com.kynsof.patients.domain.dto.DependentPatientDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.application.query.patients.getall.PatientsResponse;
import com.kynsof.patients.domain.dto.enumTye.Status;
import com.kynsof.patients.domain.dto.PatientDto;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.patients.domain.service.IPatientsService;
import com.kynsof.patients.infrastructure.config.redis.CacheConfig;
import com.kynsof.patients.infrastructure.entity.Insurance;
import com.kynsof.patients.infrastructure.repositories.command.PatientsWriteDataJPARepository;
import com.kynsof.patients.infrastructure.entity.Patients;
import com.kynsof.patients.infrastructure.repositories.query.InsuranceReadDataJPARepository;
import com.kynsof.patients.infrastructure.repositories.query.PatientsReadDataJPARepository;

import java.util.*;
import java.util.stream.Collectors;

import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.share.core.infrastructure.specifications.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientsServiceImpl implements IPatientsService {

    @Autowired
    private PatientsWriteDataJPARepository repositoryCommand;

    @Autowired
    private PatientsReadDataJPARepository repositoryQuery;

    @Autowired
    private InsuranceReadDataJPARepository insuranceReadDataJPARepository;

    @Override
    public UUID create(PatientDto patients) {
        Patients entity = this.repositoryCommand.save(new Patients(patients));
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
                    updatePatient(patient, patientDto.getName(), patientDto.getLastName(), patientDto.getIdentification(),
                            patientDto.getGender(), patientDto.getStatus());

                    return this.repositoryCommand.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + patientDto.getId() + " not found"));

        return patientDto.getId();
    }

    @Override
    public void updateDependent(DependentPatientDto patientDto) {
        if (patientDto == null || patientDto.getId() == null) {
            throw new IllegalArgumentException("Patient DTO or ID cannot be null");
        }

        this.repositoryQuery.findById(patientDto.getId())
                .map(patient -> {
                    updatePatient(patient, patientDto.getName(),
                            patientDto.getLastName(), patientDto.getIdentification(),
                            patientDto.getGender(), patientDto.getStatus());
                    if (patientDto.getPrime() != null) patient.setPrime(new Patients(patientDto.getPrime()));
                    return this.repositoryCommand.save(patient);
                })
                .orElseThrow(() -> new EntityNotFoundException("Patient with ID " + patientDto.getId() + " not found"));

    }

    private void updatePatient(Patients patient, String name, String lastName, String identification, String gender, Status status) {
        if (name != null) patient.setName(name);
        if (lastName != null) patient.setLastName(lastName);
        if (identification != null)
            patient.setIdentification(identification);
        if (gender != null) patient.setGender(gender);
        if (status != null) patient.setStatus(status);
    }


    @Cacheable(cacheNames = CacheConfig.USER_CACHE, unless = "#result == null")
    @Override
    public PatientDto findById(UUID id) {
        Optional<Patients> patient = this.repositoryQuery.findById(id);
        if (patient.isPresent()) {
            return patient.get().toAggregate();
        }
        //throw new RuntimeException("Patients not found.");
        throw new BusinessException(DomainErrorMessage.ACCESS_CODE_REQUIRED, "Patients not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<Patients> data = this.repositoryQuery.findAll(pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {

        List<SearchCriteria> criteriaList = filterCriteria.stream()
                .map(filterCriteriaItem -> new SearchCriteria(filterCriteriaItem.getKey(), filterCriteriaItem.getOperator(), filterCriteriaItem.getValue()))
                .collect(Collectors.toList());
        GenericSpecificationsBuilder<Patients> specifications = new GenericSpecificationsBuilder<>(criteriaList);
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
    public void delete(UUID id) {
        PatientDto patientDelete = this.findById(id);
        patientDelete.setStatus(Status.INACTIVE);

        this.repositoryCommand.save(new Patients(patientDelete));
    }

}
