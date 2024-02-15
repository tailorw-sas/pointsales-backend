package com.kynsof.patients.infrastructure.services;


import com.kynsof.patients.application.query.geographicLocation.getall.GeographicLocationResponse;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.infrastructure.entity.GeographicLocation;
import com.kynsof.patients.infrastructure.entity.specifications.GeographicLocationSpecifications;
import com.kynsof.patients.infrastructure.repositories.query.GeographicLocationReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GeographicLocationServiceImpl implements IGeographicLocationService {


    @Autowired
    private GeographicLocationReadDataJPARepository repositoryQuery;

    @Override
    public GeographicLocationDto findById(UUID id) {
        Optional<GeographicLocation> location = this.repositoryQuery.findById(id);
        if (location.isPresent()) {
            return location.get().toAggregate();
        }
        throw new BusinessException(DomainErrorMessage.CITIZEN_MARITAL_STATUS_REQUIRED, "Location Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID parentId, String name, String type) {
        GeographicLocationSpecifications specifications = new GeographicLocationSpecifications( name, parentId, type);
        Page<GeographicLocation> data = this.repositoryQuery.findAll(specifications, pageable);

        List<GeographicLocationResponse> allergyResponses = new ArrayList<>();
        for (GeographicLocation p : data.getContent()) {
            allergyResponses.add(new GeographicLocationResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
