package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ScheduleResponse;
import com.kynsof.calendar.domain.dto.*;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.infrastructure.entity.Schedule;
import com.kynsof.calendar.infrastructure.entity.Services;
import com.kynsof.calendar.infrastructure.repository.command.ScheduleWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ScheduleReadDataJPARepository;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    private final ScheduleReadDataJPARepository repositoryQuery;

    private final ScheduleWriteDataJPARepository repositoryCommand;

    public ScheduleServiceImpl(ScheduleReadDataJPARepository repositoryQuery, ScheduleWriteDataJPARepository repositoryCommand) {
        this.repositoryQuery = repositoryQuery;
        this.repositoryCommand = repositoryCommand;
    }

    public List<LocalDate> findDistinctAvailableDatesByServiceIdAndDateRange(UUID serviceId, LocalDate startDate, LocalDate endDate) {
        return repositoryQuery.findDistinctAvailableDatesByServiceIdAndDateRange(serviceId, startDate, endDate);
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {
            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    EStatusSchedule enumValue = EStatusSchedule.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Valor inválido para el tipo Enum EStatusSchedule: " + filter.getValue());
                }
            }
        }
        GenericSpecificationsBuilder<Schedule> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Schedule> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Schedule> data) {
        List<ScheduleResponse> patients = new ArrayList<>();
        for (Schedule s : data.getContent()) {
            patients.add(new ScheduleResponse(s.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }


   // @Cacheable(cacheNames = CacheConfig.SCHEDULE_CACHE, unless = "#result == null")
    @Override
    public ScheduleDto findById(UUID id) {

        Optional<Schedule> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SCHEDULE_NOT_FOUND, new ErrorField("id", "Schedule not found.")));
    }

    @Override
    public void delete(UUID id) {
        try {
            this.repositoryCommand.deleteById(id);
        } catch (Exception e) {
            throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.NOT_DELETE, new ErrorField("id", "Element cannot be deleted has a related element.")));
        }
    }

    @Override
    public void delete(Schedule schedule) {
        repositoryCommand.delete(schedule);
    }

    @Override
    public UUID create(ScheduleDto schedule) {
        List<Schedule> overlappingSchedules = repositoryQuery.findOverlappingSchedules(
                schedule.getResource().getId(),
                schedule.getDate(),
                schedule.getStartTime(),
                schedule.getEndingTime());

        if (overlappingSchedules.isEmpty()) {
            Schedule entity = repositoryCommand.save(new Schedule(schedule));
            return entity.getId();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.SCHEDULED_TASK_ALREADY_EXISTS, new ErrorField("id", "A scheduled task for this service already exists.")));
    }


    @Override
    public void createAll(List<ScheduleDto> schedule) {

        repositoryCommand.saveAll(schedule.stream().map(Schedule::new).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    public void update(ScheduleDto schedule) {
        var entity = new Schedule(schedule);
        repositoryCommand.save(entity);
    }


    @Override
    public List<AvailableDateDto> getAvailableDatesAndSlots(UUID resourceId, UUID businessId,  LocalDate startDate, LocalDate endDate) {
        List<ScheduleAvailabilityDto> schedules = this.repositoryQuery.findAvailableSchedulesByResourceAndBusinessAndDateRange(resourceId, businessId, startDate, endDate);

        Map<LocalDate, List<ScheduleAvailabilityDto>> groupedByDate = schedules.stream()
                .collect(Collectors.groupingBy(ScheduleAvailabilityDto::getDate));

        List<AvailableDateDto> availableDates = new ArrayList<>();

        groupedByDate.forEach((date, scheduleAvailabilityDtos) -> {
            List<AvailableTimeSlotDto> timeSlots = scheduleAvailabilityDtos.stream()
                    .map(s -> new AvailableTimeSlotDto(s.getStartTime(), s.getEndingTime(), s.getScheduleId()))
                    .collect(Collectors.toList());
            availableDates.add(new AvailableDateDto(date, timeSlots));
        });

        return availableDates;
    }

    @Override
    public List<Schedule> findOverlappingSchedules(UUID resourceId, LocalDate date, LocalTime startTime, LocalTime endingTime) {
        return this.repositoryQuery.findOverlappingSchedules(resourceId, date, startTime, endingTime);
    }

    @Override
    public List<ScheduleDto> findSchedulesWithEqualStock(LocalDate date) {
        return this.repositoryQuery.findSchedulesWithEqualStock(date).stream().map(Schedule::toAggregate).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedResponse getUniqueAvailableServices(Pageable pageable) {
        // Obtiene los schedules disponibles (con stock > 0 y fecha mayor a la actual) paginados
        Page<Schedule> availableSchedules = this.repositoryQuery.findAvailableSchedules(LocalDate.now(), pageable);

        // Extrae los servicios únicos y convierte a ServiceDto
        List<ServiceDto> serviceDtos = availableSchedules.stream()
                .map(Schedule::getService)
                .filter(service -> service != null) // Filtra servicios nulos
                .distinct() // Elimina duplicados
                .map(Services::toAggregate) // Convierte a DTO
                .collect(Collectors.toList());

        // Construir y retornar PaginatedResponse
        return new PaginatedResponse(
                serviceDtos,
                availableSchedules.getTotalPages(),
                availableSchedules.getNumberOfElements(),
                availableSchedules.getTotalElements(),
                availableSchedules.getSize(),
                availableSchedules.getNumber()
        );
    }
}

