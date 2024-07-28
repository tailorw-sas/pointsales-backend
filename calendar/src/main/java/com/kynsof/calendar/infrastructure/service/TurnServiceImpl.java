package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.TurnResponse;
import com.kynsof.calendar.domain.dto.TurnDto;
import com.kynsof.calendar.domain.service.ITurnService;
import com.kynsof.calendar.infrastructure.entity.Turn;
import com.kynsof.calendar.infrastructure.repository.command.TurnWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.TurnReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TurnServiceImpl implements ITurnService {

    private final TurnWriteDataJPARepository repositoryCommand;

    private final TurnReadDataJPARepository repositoryQuery;

    public TurnServiceImpl(TurnWriteDataJPARepository repositoryCommand, TurnReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    public UUID create(TurnDto object) {
        return this.repositoryCommand.save(new Turn(object)).getId();
    }

    @Override
    @Transactional
    public void update(TurnDto objectDto) {
        Turn update = new Turn(objectDto);
        this.repositoryCommand.save(update);
    }

    @Override
    public void delete(UUID id) {
        try {
            this.repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    //@Cacheable(cacheNames = CacheConfig.SERVICE_CACHE, unless = "#result == null")
    @Override
    public TurnDto findById(UUID id) {

        Optional<Turn> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SERVICE_TYPE_NOT_FOUND, new ErrorField("id", "Service Type not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Turn> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Turn> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public List<TurnDto> findByServiceIds(List<UUID> serviceId, UUID businessId) {
        return this.repositoryQuery.findByServiceIds(serviceId, businessId).stream().map(Turn::toAggregate).toList();
    }

    @Override
    public TurnDto findByLocalId(String local, UUID businessId) {
        List<Turn> object = this.repositoryQuery.findByLocalId(local, businessId);
        return object.stream().map(Turn::toAggregate).findFirst().orElse(null);
    }

    @Override
    public Integer findMaxOrderNumberByServiceId(UUID serviceId, UUID businessId) {
        return this.repositoryQuery.findMaxOrderNumberByServiceId(serviceId, businessId);
    }


    private PaginatedResponse getPaginatedResponse(Page<Turn> data) {
        List<TurnResponse> patients = new ArrayList<>();
        for (Turn o : data.getContent()) {
            patients.add(new TurnResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }


}
