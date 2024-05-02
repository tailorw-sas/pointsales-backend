package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.BusinessResponse;
import com.kynsof.calendar.domain.dto.BusinessDto;
import com.kynsof.calendar.domain.dto.ScheduleServiceInfoDto;
import com.kynsof.calendar.domain.service.IBusinessService;
import com.kynsof.calendar.infrastructure.entity.Business;
import com.kynsof.calendar.infrastructure.repository.command.BusinessWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.BusinessReadDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ScheduleReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessServiceImpl implements IBusinessService {

    @Autowired
    private BusinessWriteDataJPARepository repositoryCommand;

    @Autowired
    private BusinessReadDataJPARepository repositoryQuery;

    @Autowired
    private ScheduleReadDataJPARepository scheduleReadDataJPARepository;

    @Override
    public void create(BusinessDto object) {
        this.repositoryCommand.save(new Business(object));
    }

    @Override
    public void update(BusinessDto objectDto) {
        if (objectDto.getId() == null || objectDto == null) {
            throw new BusinessException(DomainErrorMessage.BUSINESS_OR_ID_NULL, "Business DTO or ID cannot be null.");
        }

        this.repositoryQuery.findById(objectDto.getId())
                .map(object -> {
                    if (objectDto.getName() != null) {
                        object.setName(objectDto.getName());
                    }
                    if (objectDto.getLatitude()!= null) {
                        object.setLatitude(objectDto.getLatitude());
                    }
                    if (objectDto.getLongitude() != null) {
                        object.setLongitude(objectDto.getLongitude());
                    }
                    if (objectDto.getLogo() != null) {
                        object.setLogo(objectDto.getLogo());
                    }

                    return this.repositoryCommand.save(object);
                })
                .orElseThrow(() -> new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Qualification not found."));

    }

    @Override
    public void delete(UUID id) {
        BusinessDto objectDelete = this.findById(id);

        this.repositoryCommand.save(new Business(objectDelete));
    }

    @Override
    public void deleteIds(List<UUID> ids) {
        this.repositoryCommand.deleteAllByIdInBatch(ids);
    }

//    @Cacheable(cacheNames = CacheConfig.BUSINESS_CACHE, unless = "#result == null")
    @Override
    public BusinessDto findById(UUID id) {

        Optional<Business> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, new ErrorField("id", "Business not found.")));
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Business> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Business> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse findBusinessesWithAvailableStockByDateAndService(LocalDate date, UUID serviceId,
                                                                              Pageable pageable) {
        Page<Business> data =  scheduleReadDataJPARepository.findBusinessesWithAvailableStockByDateAndService(date, serviceId, pageable);
        return getPaginatedResponse(data);
    }

    @Override
    public PaginatedResponse findDetailedAvailableSchedulesByResourceAndBusinessAndDateRange(LocalDate startDate,
                                                                                             LocalDate endDate, UUID serviceId,String businessName,
                                                                              Pageable pageable) {
        Page<ScheduleServiceInfoDto> data =  scheduleReadDataJPARepository.
                findDetailedAvailableSchedulesByResourceAndBusinessAndDateRange(serviceId, startDate, endDate,businessName, pageable);
        return new PaginatedResponse(data.stream().toList(), data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    private PaginatedResponse getPaginatedResponse(Page<Business> data) {
        List<BusinessResponse> businessResponses = new ArrayList<>();
        for (Business o : data.getContent()) {
            businessResponses.add(new BusinessResponse(o.toAggregate()));
        }
        return new PaginatedResponse(businessResponses, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
