package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.AttendanceLogResponse;
import com.kynsof.calendar.domain.dto.AttendanceLogDto;
import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import com.kynsof.calendar.domain.service.IAttendanceLogService;
import com.kynsof.calendar.infrastructure.entity.AttendanceLog;
import com.kynsof.calendar.infrastructure.repository.command.AttendanceLogWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.AttendanceLogReadDataJPARepository;
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
public class AttendanceLogServiceImpl implements IAttendanceLogService {

    private final AttendanceLogWriteDataJPARepository repositoryCommand;

    private final AttendanceLogReadDataJPARepository repositoryQuery;

    public AttendanceLogServiceImpl(AttendanceLogWriteDataJPARepository repositoryCommand, AttendanceLogReadDataJPARepository repositoryQuery) {
        this.repositoryCommand = repositoryCommand;
        this.repositoryQuery = repositoryQuery;
    }

    @Override
    @Transactional
    public UUID create(AttendanceLogDto object) {
        return this.repositoryCommand.save(new AttendanceLog(object)).getId();
    }

    @Override
    @Transactional
    public void update(AttendanceLogDto objectDto) {
        AttendanceLog update = new AttendanceLog(objectDto);
        this.repositoryCommand.save(update);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        try {
            this.repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    @Override
    public AttendanceLogDto getByServiceId(UUID serviceId, UUID businessId) {
        List<AttendanceLog> object = this.repositoryQuery.getByServiceId(serviceId, businessId);
        return object.stream().map(AttendanceLog::toAggregate).findFirst().orElse(null);

    }

    @Override
    public List<AttendanceLogDto> getByLocalId(UUID placeId, UUID businessId) {
        List<AttendanceLog> object = this.repositoryQuery.getByPlaceId(placeId, businessId);
        return object.stream().map(AttendanceLog::toAggregate).toList();
    }

    @Override
    @Transactional
    public void deleteByIds(List<UUID> list) {
        this.repositoryCommand.deleteAllById(list);
    }

    @Override
    public AttendanceLogDto findById(UUID id) {
        Optional<AttendanceLog> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SERVICE_TYPE_NOT_FOUND, new ErrorField("id", "Service Type not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        filterCriteria(filterCriteria);
        GenericSpecificationsBuilder<AttendanceLog> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<AttendanceLog> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private void filterCriteria(List<FilterCriteria> filterCriteria) {
        filterCriteria.forEach(filter -> {
            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                filter.setValue(parseEnum(EServiceStatus.class, (String) filter.getValue(), "EServiceStatus"));
            }
        });
    }

    private <T extends Enum<T>> T parseEnum(Class<T> enumClass, String value, String enumName) {
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid value for enum " + enumName + ": " + value);
            return null;
        }
    }

    private PaginatedResponse getPaginatedResponse(Page<AttendanceLog> data) {
        List<AttendanceLogResponse> patients = new ArrayList<>();
        for (AttendanceLog o : data.getContent()) {
            patients.add(new AttendanceLogResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }


}
