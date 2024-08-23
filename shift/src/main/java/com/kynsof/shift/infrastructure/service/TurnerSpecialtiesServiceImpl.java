package com.kynsof.shift.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.shift.application.query.TurnerSpecialtiesResponse;
import com.kynsof.shift.domain.dto.TurnerSpecialtiesDto;
import com.kynsof.shift.domain.dto.enumType.ETurnerSpecialtiesStatus;
import com.kynsof.shift.domain.service.ITurnerSpecialtiesService;
import com.kynsof.shift.infrastructure.entity.TurnerSpecialties;
import com.kynsof.shift.infrastructure.repository.command.TurnerSpecialtiesWriteDataJPARepository;
import com.kynsof.shift.infrastructure.repository.query.TurnerSpecialtiesReadDataJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(
                        Sort.Order.desc("status"), // Puts 'PENDING' first if enum is sorted by ordinal or alphabetically
                        Sort.Order.asc("consultationTime")
                )
        );
        Page<TurnerSpecialties> data = this.repositoryQuery.findAll(specifications, sortedPageable);

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
