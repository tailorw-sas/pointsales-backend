package com.kynsof.identity.infrastructure.services;


import com.kynsof.identity.application.query.business.geographiclocation.getall.GeographicLocationResponse;
import com.kynsof.identity.domain.dto.CantonDto;
import com.kynsof.identity.domain.dto.GeographicLocationDto;
import com.kynsof.identity.domain.dto.LocationHierarchyDto;
import com.kynsof.identity.domain.dto.ParroquiaDto;
import com.kynsof.identity.domain.dto.ProvinceDto;
import com.kynsof.identity.domain.dto.enumType.GeographicLocationType;
import com.kynsof.identity.domain.interfaces.service.IGeographicLocationService;
import com.kynsof.identity.infrastructure.identity.GeographicLocation;
import com.kynsof.identity.infrastructure.repository.command.GeographicLocationWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.GeographicLocationReadDataJPARepository;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.redis.CacheConfig;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
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
public class GeographicLocationServiceImpl implements IGeographicLocationService {


    @Autowired
    private GeographicLocationReadDataJPARepository repositoryQuery;

    @Autowired
    private GeographicLocationWriteDataJPARepository repositoryCommand;

    @Override
    public void create(GeographicLocationDto object) {
        this.repositoryCommand.save(new GeographicLocation(object));
    }

    @Override
    @Cacheable(cacheNames =  CacheConfig.LOCATION_CACHE, unless = "#result == null")
    public GeographicLocationDto findById(UUID id) {
        Optional<GeographicLocation> location = this.repositoryQuery.findById(id);
        if (location.isPresent()) {
            return location.get().toAggregate();
        }
        //  throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Location Information not found.");
        throw new RuntimeException("Location not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        Page<GeographicLocation> data = this.repositoryQuery.findAll(pageable);

        return getPaginatedResponse(data);
    }

    @Override
//    @Cacheable(cacheNames =  CacheConfig.LOCATION_CACHE, unless = "#result == null")
    public LocationHierarchyDto findCantonAndProvinceIdsByParroquiaId(UUID parroquiaId) {
        Optional<GeographicLocation> parroquiaOptional = repositoryQuery.findById(parroquiaId);

        if (parroquiaOptional.isEmpty()) {
            throw new RuntimeException("Location not found.");
        }

        GeographicLocation parroquia = parroquiaOptional.get();
        if (parroquia.getType() != GeographicLocationType.PARROQUIA || parroquia.getParent() == null) {
            throw new RuntimeException("Location not found.");
        }

        GeographicLocation canton = parroquia.getParent();
        if (canton.getType() != GeographicLocationType.CANTON || canton.getParent() == null) {
            throw new RuntimeException("Location not found.");
        }

        GeographicLocation province = canton.getParent();
        if (province.getType() != GeographicLocationType.PROVINCE) {
            throw new RuntimeException("Location not found.");
        }

        return new LocationHierarchyDto(new ProvinceDto(province.getId(),province.getName()),
                new CantonDto(canton.getId(),canton.getName()),
                new ParroquiaDto(parroquia.getId(),parroquia.getName()));
    }

    @Override
    //@Cacheable(cacheNames =  CacheConfig.LOCATION_CACHE, unless = "#result == null")
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {
            if ("type".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    GeographicLocationType enumValue = GeographicLocationType.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Valor inválido para el tipo Enum GeographicLocationType: " + filter.getValue());
                }
            }
        }

        GenericSpecificationsBuilder<GeographicLocation> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<GeographicLocation> data = this.repositoryQuery.findAll(specifications, pageable);
        PaginatedResponse paginatedResponse = getPaginatedResponse(data);
        return  paginatedResponse;
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
