package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ScheduleResponse;
import com.kynsof.calendar.domain.dto.AvailableDateDto;
import com.kynsof.calendar.domain.dto.AvailableTimeSlotDto;
import com.kynsof.calendar.domain.dto.ScheduleAvailabilityDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.infrastructure.entity.Resource;
import com.kynsof.calendar.infrastructure.entity.Schedule;
import com.kynsof.calendar.infrastructure.repository.command.ScheduleWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.ScheduleReadDataJPARepository;
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
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    private ScheduleReadDataJPARepository repositoryQuery;

    @Autowired
    private ScheduleWriteDataJPARepository repositoryCommand;

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

    @Override
    public Page<Schedule> getAll(Pageable pageable) {
        return repositoryQuery.findAll(pageable);
    }


    @Override
    public boolean findByResourceAndDateAndStartTimeAndEndingTime(Resource resource, LocalDate date, LocalTime startTime, LocalTime endingTime) {
        Schedule _schedule = this.repositoryQuery.findByResourceAndDateAndStartTimeAndEndingTimeAndStatus(resource, date, startTime, endingTime, EStatusSchedule.AVAILABLE);
        return _schedule != null;
    }

    @Override
    public boolean findByDateAndTimeInRange(Resource resource, LocalDate date, LocalTime startTime, LocalTime endingTime) {
        List<Schedule> _schedulesStartTime = this.repositoryQuery.findByDateAndTimeInRange(resource, date, startTime);
        if (!_schedulesStartTime.isEmpty() && _schedulesStartTime.get(0).getEndingTime().equals(startTime)) {
            return false;
        }
        List<Schedule> _schedulesEndingTime = this.repositoryQuery.findByDateAndTimeInRange(resource, date, endingTime);
        if (!_schedulesEndingTime.isEmpty() && _schedulesEndingTime.get(0).getStartTime().equals(endingTime)) {
            return false;
        }
        return !_schedulesStartTime.isEmpty() || !_schedulesEndingTime.isEmpty();
    }

    @Override
    public boolean findByDateAndTimeInRangeAndStartTimeAndEndingTime(Resource resource, LocalDate date, LocalTime startTime, LocalTime endingTime) {
        List<Schedule> _schedulesStartTime = this.repositoryQuery.findByDateAndTimeInRangeAndStartTimeAndEndingTime(resource, date, startTime, endingTime);

        return !_schedulesStartTime.isEmpty();
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
    public ScheduleDto update(ScheduleDto schedule) {
        repositoryCommand.save(new Schedule(schedule));
        return schedule;
    }

    /**
     * Si condition es igual a "EQUALS", el método devuelve true si la fecha
     * actual es igual a validateDate. Si condition es igual a "BEFORE", el
     * método devuelve true si la fecha actual es anterior a validateDate.
     *
     * @param validateDate
     * @param condition
     * @return
     */
    @Override
    public boolean validateDate(LocalDate validateDate, String condition) {
        LocalDate currentDate = LocalDate.now();
        switch (condition) {
            case "EQUALS":
                return currentDate.equals(validateDate);

            case "BEFORE":
                return currentDate.isBefore(validateDate);

            default:
                return false;

        }
    }

    /**
     * Si condition es igual a "EQUALS", el método devuelve true si la hora
     * actual es igual a validateDate. Si condition es igual a "BEFORE", el
     * método devuelve true si la hora actual es anterior a validateDate.
     *
     * @param validateTime
     * @param validateDate
     * @param condition
     * @return
     */
    @Override
    public boolean validateStartTime(LocalTime validateTime, LocalDate validateDate, String condition) {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        return switch (condition) {
            case "EQUALS" -> currentTime.equals(validateTime);
            case "BEFORE" ->
                    !(currentDate.isAfter(validateDate) || (currentDate.equals(validateDate) && currentTime.isAfter(validateTime)));
            default -> false;
        };
    }

    /**
     * Si startTime es anterior a endingTime, el método devuelve true.
     *
     * @param startTime
     * @param endingTime
     * @return
     */
    @Override
    public boolean validateStartTimeAndEndingTime(LocalTime startTime, LocalTime endingTime) {

        return startTime.isBefore(endingTime);
    }

    /**
     * Si startTime es igual a endingTime, el método devuelve true.
     *
     * @param startTime
     * @param endingTime
     * @return
     */
    @Override
    public boolean validateStartTimeAndEndingTimeEqual(LocalTime startTime, LocalTime endingTime) {

        return startTime.equals(endingTime);
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

}
