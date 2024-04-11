package com.kynsof.treatments.infrastructure.service;


import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.treatments.application.query.vaccine.getall.VaccineResponse;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.dto.enumDto.VaccinationStatus;
import com.kynsof.treatments.domain.service.IVaccineService;
import com.kynsof.treatments.infrastructure.entity.Vaccine;
import com.kynsof.treatments.infrastructure.entity.specifications.Cie10Specifications;
import com.kynsof.treatments.infrastructure.repositories.command.VaccineWriteDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.PatientVaccineReadDataJPARepository;
import com.kynsof.treatments.infrastructure.repositories.query.VaccineReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VaccineServiceImpl implements IVaccineService {

    @Autowired
    private VaccineWriteDataJPARepository repositoryCommand;

    @Autowired
    private VaccineReadDataJPARepository repositoryQuery;
    @Autowired
    private PatientVaccineReadDataJPARepository patientVaccineReadDataJPARepository;

    @Override
    public VaccineDto findById(UUID id) {
        Optional<Vaccine> vaccine = this.repositoryQuery.findById(id);
        if (vaccine.isPresent()) {
            return vaccine.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Vaccine not found.");
    }


//    @Override
//    public List<VaccineDto> getApplicableVaccines(LocalDate birthDate, UUID patientId) {
//        long monthsOld = ChronoUnit.MONTHS.between(birthDate, LocalDate.now());
//        List<Vaccine> applicableVaccines = this.repositoryQuery.findByMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(monthsOld);
//        // Obtener las vacunas que el paciente ya se ha puesto
//        List<Vaccine> administeredVaccines = this.patientVaccineReadDataJPARepository.findVaccinesByPatientId(patientId);
//
//        // Filtrar las vacunas aplicables para excluir las ya administradas
//        List<VaccineDto> nonAdministeredVaccines = applicableVaccines.stream()
//                .filter(vaccine -> !administeredVaccines.contains(vaccine))
//                .map(Vaccine::toAggregate)
//                .collect(Collectors.toList());
//
//        return nonAdministeredVaccines;
//    }

    @Override
    public PaginatedResponse getApplicableVaccines(LocalDate birthDate, UUID patientId, Pageable pageable) {
        long monthsOld = ChronoUnit.MONTHS.between(birthDate, LocalDate.now());

        Page<Vaccine> vaccinePage = this.repositoryQuery.findByMinAgeLessThanEqualAndMaxAgeGreaterThanEqual( monthsOld, pageable);
        List<Vaccine> administeredVaccines = patientVaccineReadDataJPARepository.findVaccinesByPatientId(patientId);

        List<VaccineDto> nonAdministeredVaccines = vaccinePage.getContent().stream()
                .filter(vaccine -> !administeredVaccines.contains(vaccine))
                .map(Vaccine::toAggregate)
                .collect(Collectors.toList());

        return new PaginatedResponse(
                nonAdministeredVaccines,
                vaccinePage.getTotalPages(),
                vaccinePage.getNumberOfElements(),
                vaccinePage.getTotalElements(),
                pageable.getPageSize(),
                pageable.getPageNumber()
        );
    }

    @Override
    public UUID create(VaccineDto vaccineDto) {
        Vaccine entity = this.repositoryCommand.save(new Vaccine(vaccineDto));
        return entity.getId();
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, String name, String description) {
        Cie10Specifications specifications = new Cie10Specifications(name, description);
        Page<Vaccine> data = this.repositoryQuery.findAll(specifications, pageable);

        List<VaccineResponse> allergyResponses = new ArrayList<>();
        for (Vaccine p : data.getContent()) {
            allergyResponses.add(new VaccineResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {
            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    VaccinationStatus enumValue = VaccinationStatus.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Valor inválido para el tipo Enum RoleStatus: " + filter.getValue());
                }
            }
        }
        GenericSpecificationsBuilder<Vaccine> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Vaccine> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Vaccine> data) {
        List<VaccineResponse> vaccineResponses = new ArrayList<>();
        for (Vaccine p : data.getContent()) {
            vaccineResponses.add(new VaccineResponse(p.toAggregate()));
        }
        return new PaginatedResponse(vaccineResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }


}
