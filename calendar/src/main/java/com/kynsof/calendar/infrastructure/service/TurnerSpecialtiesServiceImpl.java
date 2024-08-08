package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.TurnerSpecialtiesResponse;
import com.kynsof.calendar.domain.dto.TurnerSpecialtiesDto;
import com.kynsof.calendar.domain.dto.enumType.ETurnerSpecialtiesStatus;
import com.kynsof.calendar.infrastructure.entity.TurnerSpecialties;
import com.kynsof.calendar.infrastructure.repository.command.TurnerSpecialtiesWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.TurnerSpecialtiesReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.kynsof.calendar.domain.service.ITurnerSpecialtiesService;

@Service
public class TurnerSpecialtiesServiceImpl implements ITurnerSpecialtiesService {

    @Autowired
    private TurnerSpecialtiesWriteDataJPARepository repositoryCommand;

    @Autowired
    private TurnerSpecialtiesReadDataJPARepository repositoryQuery;

    @Override
    public void create(TurnerSpecialtiesDto dto) {
        TurnerSpecialties data = new TurnerSpecialties(dto);
        this.repositoryCommand.save(data);
    }

    @Override
    public void create(List<TurnerSpecialtiesDto> dtos) {
        List<TurnerSpecialties> objects = new ArrayList<>();
        for (TurnerSpecialtiesDto dto : dtos) {
            objects.add(new TurnerSpecialties(dto));
        }
        this.repositoryCommand.saveAll(objects);
    }

    @Override
    public void update(TurnerSpecialtiesDto dto) {
        TurnerSpecialties update = new TurnerSpecialties(dto);
        this.repositoryCommand.save(update);
    }

    @Override
    public void delete(UUID id) {
        try{
            this.repositoryCommand.deleteById(id);
        } catch (Exception e){
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", DomainErrorMessage.NOT_DELETE.getReasonPhrase())));
        }
    }

    @Override
    public TurnerSpecialtiesDto findById(UUID id) {
        Optional<TurnerSpecialties> userSystem = this.repositoryQuery.findById(id);
        if (userSystem.isPresent()) {
            return userSystem.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.OBJECT_NOT_FOUNT, new ErrorField("id", DomainErrorMessage.OBJECT_NOT_FOUNT.getReasonPhrase())));
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        filterCriteria(filterCriteria);

        GenericSpecificationsBuilder<TurnerSpecialties> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<TurnerSpecialties> data = this.repositoryQuery.findAll(specifications, pageable);

        return getPaginatedResponse(data);
    }

    private void filterCriteria(List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {

            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    ETurnerSpecialtiesStatus enumValue = ETurnerSpecialtiesStatus.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Valor inválido para el tipo Enum Status: " + filter.getValue());
                }
            }
        }
    }

    private PaginatedResponse getPaginatedResponse(Page<TurnerSpecialties> data) {
        List<TurnerSpecialtiesResponse> responses = new ArrayList<>();
        for (TurnerSpecialties p : data.getContent()) {
            responses.add(new TurnerSpecialtiesResponse(p.toAggregate()));
        }
        return new PaginatedResponse(responses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }
}
