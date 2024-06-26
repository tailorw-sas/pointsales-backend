package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.PlaceResponse;
import com.kynsof.calendar.domain.dto.PlaceDto;
import com.kynsof.calendar.domain.service.IPlaceService;
import com.kynsof.calendar.infrastructure.entity.Place;
import com.kynsof.calendar.infrastructure.repository.command.PlaceWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.PlaceReadDataJPARepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlaceServiceImpl implements IPlaceService {

    private final PlaceWriteDataJPARepository repositoryCommand;

    private final PlaceReadDataJPARepository repositoryQuery;

    public PlaceServiceImpl(PlaceWriteDataJPARepository repositoryCommand, PlaceReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    public UUID create(PlaceDto object) {
        return this.repositoryCommand.save(new Place(object)).getId();
    }

    @Override
    public void update(PlaceDto objectDto) {
        Place update = new Place(objectDto);
        update.setUpdatedAt(LocalDateTime.now());
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

    private PlaceDto getById(UUID id) {

        Optional<Place> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SERVICE_TYPE_NOT_FOUND, new ErrorField("id", "Service Type not found.")));

    }

    //@Cacheable(cacheNames = CacheConfig.SERVICE_CACHE, unless = "#result == null")
    @Override
    public PlaceDto findById(UUID id) {

        Optional<Place> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SERVICE_TYPE_NOT_FOUND, new ErrorField("id", "Service Type not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Place> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Place> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Place> data) {
        List<PlaceResponse> patients = new ArrayList<>();
        for (Place o : data.getContent()) {
            patients.add(new PlaceResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }


}
