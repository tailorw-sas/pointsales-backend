package com.kynsof.treatments.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.redis.CacheConfig;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.treatments.application.query.cie10.getAll.Cie10Response;
import com.kynsof.treatments.domain.dto.Cie10Dto;
import com.kynsof.treatments.domain.service.ICie10Service;
import com.kynsof.treatments.infrastructure.entity.Cie10;
import com.kynsof.treatments.infrastructure.entity.specifications.Cie10Specifications;
import com.kynsof.treatments.infrastructure.repositories.query.Cie10ReadDataJPARepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Cie10ServiceImpl implements ICie10Service {

    private final Cie10ReadDataJPARepository repositoryQuery;

    public Cie10ServiceImpl(Cie10ReadDataJPARepository repositoryQuery) {
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    @Cacheable(cacheNames = CacheConfig.CIE10, unless = "#result == null")
    public Cie10Dto findByCode(String code) {
        Optional<Cie10> cie10ByCode = Optional.ofNullable(this.repositoryQuery.findCie10ByCode(code));
        if (cie10ByCode.isPresent()) {
            return cie10ByCode.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.CIE10_NOT_FOUND,
                new ErrorField("id", "Cie10 not found.")));
    }

    @Override
    @Cacheable(cacheNames = CacheConfig.CIE10, unless = "#result == null")
    public PaginatedResponse findAll(Pageable pageable, String name, String code) {
        var specifications = new Cie10Specifications(name, code);
        var data = repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    @Override
    @Cacheable(cacheNames = CacheConfig.CIE10, unless = "#result == null")
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        var specifications = new GenericSpecificationsBuilder<Cie10>(filterCriteria);
        var data = repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Cie10> data) {
        var responses = data.getContent().stream()
                .map(cie10 -> new Cie10Response(cie10.toAggregate()))
                .toList();
        return new PaginatedResponse(responses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}