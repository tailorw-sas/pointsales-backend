package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.business.geographiclocation.getall.GeographicLocationResponse;
import com.kynsof.identity.domain.dto.*;
import com.kynsof.identity.domain.dto.enumType.GeographicLocationType;
import com.kynsof.identity.domain.interfaces.service.IGeographicLocationService;
import com.kynsof.identity.infrastructure.identity.GeographicLocation;
import com.kynsof.identity.infrastructure.repository.command.GeographicLocationWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.GeographicLocationReadDataJPARepository;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.core.infrastructure.redis.CacheConfig;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GeographicLocationServiceImpl implements IGeographicLocationService {

    private final GeographicLocationReadDataJPARepository repositoryQuery;
    private final GeographicLocationWriteDataJPARepository repositoryCommand;

    public GeographicLocationServiceImpl(GeographicLocationReadDataJPARepository repositoryQuery,
                                         GeographicLocationWriteDataJPARepository repositoryCommand) {
        this.repositoryQuery = repositoryQuery;
        this.repositoryCommand = repositoryCommand;
    }

    @Override
    @Transactional
    public void create(GeographicLocationDto object) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(object, "GeographicLocation", "Geographic DTO cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(object.getId(), "GeographicLocation.id", "Geographic ID cannot be null."));
        repositoryCommand.save(new GeographicLocation(object));
    }

    @Override
    @Transactional
    public void delete(GeographicLocationDto delete) {
        var geolocation = new GeographicLocation(delete);
        geolocation.setName(delete.getName() + "-" + UUID.randomUUID());
        repositoryCommand.save(geolocation);
    }

    @Override
    @Cacheable(cacheNames = CacheConfig.LOCATION_CACHE, unless = "#result == null")
    public GeographicLocationDto findById(UUID id) {
        return repositoryQuery.findById(id)
                .map(GeographicLocation::toAggregate)
                .orElseThrow(() -> new BusinessNotFoundException(new GlobalBusinessException(
                        DomainErrorMessage.GEOGRAPHIC_LOCATION_NOT_FOUND, new ErrorField("id", "GeographicLocation not found."))));
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable) {
        var data = repositoryQuery.findAll(pageable);
        return getPaginatedResponse(data);
    }

    @Override
    @Cacheable(cacheNames = CacheConfig.LOCATION_CACHE, unless = "#result == null")
    public LocationHierarchyDto findCantonAndProvinceIdsByParroquiaId(UUID parroquiaId) {
        var parroquia = repositoryQuery.findById(parroquiaId)
                .filter(loc -> loc.getType() == GeographicLocationType.PARROQUIA && loc.getParent() != null)
                .orElseThrow(() -> new BusinessNotFoundException(new GlobalBusinessException(
                        DomainErrorMessage.GEOGRAPHIC_LOCATION_NOT_FOUND, new ErrorField("GeographicLocation.type", "Location not found."))));

        var canton = parroquia.getParent();
        if (canton.getType() != GeographicLocationType.CANTON || canton.getParent() == null) {
            throw new BusinessNotFoundException(new GlobalBusinessException(
                    DomainErrorMessage.GEOGRAPHIC_LOCATION_NOT_FOUND, new ErrorField("GeographicLocation.type", "Location not found.")));
        }

        var province = canton.getParent();
        if (province.getType() != GeographicLocationType.PROVINCE) {
            throw new BusinessNotFoundException(new GlobalBusinessException(
                    DomainErrorMessage.GEOGRAPHIC_LOCATION_NOT_FOUND, new ErrorField("GeographicLocation.type", "Location not found.")));
        }

        return new LocationHierarchyDto(
                new ProvinceDto(province.getId(), province.getName()),
                new CantonDto(canton.getId(), canton.getName()),
                new ParroquiaDto(parroquia.getId(), parroquia.getName())
        );
    }

    @Override
    @Cacheable(cacheNames = CacheConfig.LOCATION_CACHE, unless = "#result == null")
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        filterCriteria.forEach(filter -> {
            if ("type".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    filter.setValue(GeographicLocationType.valueOf((String) filter.getValue()));
                } catch (IllegalArgumentException e) {
                    System.err.println("Invalid value for GeographicLocationType enum: " + filter.getValue());
                }
            }
        });

        var specifications = new GenericSpecificationsBuilder<GeographicLocation>(filterCriteria);
        var data = repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<GeographicLocation> data) {
        var responses = data.getContent().stream()
                .map(geographicLocation -> new GeographicLocationResponse(geographicLocation.toAggregate()))
                .toList();
        return new PaginatedResponse(responses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}