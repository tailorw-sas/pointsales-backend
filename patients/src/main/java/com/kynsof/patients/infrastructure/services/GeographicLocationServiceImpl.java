package com.kynsof.patients.infrastructure.services;


import com.kynsof.patients.application.query.geographicLocation.getall.GeographicLocationResponse;
import com.kynsof.patients.domain.dto.GeographicLocationDto;
import com.kynsof.patients.domain.service.IGeographicLocationService;
import com.kynsof.patients.infrastructure.entity.GeographicLocation;
import com.kynsof.patients.infrastructure.repositories.query.GeographicLocationReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
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
      //  throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Location Information not found.");
        throw new RuntimeException("Patients not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<GeographicLocation> data = this.repositoryQuery.findAll( pageable);

        return getPaginatedResponse(data);
    }
    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<GeographicLocation> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<GeographicLocation> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }
    private PaginatedResponse getPaginatedResponse(Page<GeographicLocation> data) {
        List<GeographicLocationResponse> allergyResponses = new ArrayList<>();
        for (GeographicLocation p : data.getContent()) {
            allergyResponses.add(new GeographicLocationResponse(p.toAggregate()));
        }
        return new PaginatedResponse(allergyResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
