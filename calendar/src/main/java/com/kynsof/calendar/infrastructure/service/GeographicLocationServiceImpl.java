package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.PaginatedResponse;
import com.kynsof.calendar.domain.dto.GeographicLocationDto;
import com.kynsof.calendar.domain.dto.GeographicLocationResponse;
import com.kynsof.calendar.domain.exception.BusinessException;
import com.kynsof.calendar.domain.exception.DomainErrorMessage;
import com.kynsof.calendar.domain.service.IGeographicLocationService;
import com.kynsof.calendar.infrastructure.entity.GeographicLocation;
import com.kynsof.calendar.infrastructure.entity.specifications.GeographicLocationSpecifications;
import com.kynsof.calendar.infrastructure.repository.query.GeographicLocationReadDataJPARepository;
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
        throw new BusinessException(DomainErrorMessage.PATIENTS_NOT_FOUND, "Location Information not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID parentId, String name, String type) {
        GeographicLocationSpecifications specifications = new GeographicLocationSpecifications( name, parentId, type);
        Page<GeographicLocation> data = this.repositoryQuery.findAll(specifications, pageable);

        List<GeographicLocationResponse> response = new ArrayList<>();
        for (GeographicLocation p : data.getContent()) {
            response.add(new GeographicLocationResponse(p.toAggregate()));
        }
        return new PaginatedResponse(response, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
