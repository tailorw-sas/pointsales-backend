package com.kynsof.treatments.infrastructure.service;


import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.treatments.application.query.vaccine.getall.VaccineResponse;
import com.kynsof.treatments.domain.dto.VaccineDto;
import com.kynsof.treatments.domain.service.IVaccineService;
import com.kynsof.treatments.infrastructure.entity.Vaccine;
import com.kynsof.treatments.infrastructure.entity.specifications.Cie10Specifications;
import com.kynsof.treatments.infrastructure.repositories.query.VaccineReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VaccineServiceImpl implements IVaccineService {

    @Autowired
    private VaccineReadDataJPARepository repositoryQuery;

    @Override
    public VaccineDto findById(UUID id) {
        Optional<Vaccine> vaccine = this.repositoryQuery.findById(id);
        if (vaccine.isPresent()) {
            return vaccine.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Vaccine not found.");
    }


    @Override
    public List<VaccineResponse> getEligibleVaccines(double age) {
        List<Vaccine> vaccines = this.repositoryQuery.findByMinAgeLessThanEqualAndMaxAgeGreaterThanEqual(age);
        List<VaccineResponse> vaccineResponses = new ArrayList<>();
        for (Vaccine p : vaccines) {
            vaccineResponses.add(new VaccineResponse(p.toAggregate()));
        }
       return vaccineResponses;
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

}
