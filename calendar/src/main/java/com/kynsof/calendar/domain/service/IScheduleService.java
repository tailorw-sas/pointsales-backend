package com.kynsof.calendar.domain.service;

import com.kynsof.calendar.domain.dto.AvailableDateDto;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.calendar.infrastructure.entity.Resource;
import com.kynsof.calendar.infrastructure.entity.Schedule;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface IScheduleService {
    public void delete(UUID id);
    public ScheduleDto findById(UUID id);
    PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria);
    Page<Schedule> getAll(Pageable pageable);
    PaginatedResponse findAll(Pageable pageable, UUID resource, LocalDate date, EStatusSchedule status, LocalDate startDate, LocalDate endDate);
    boolean findByResourceAndDateAndStartTimeAndEndingTime(Resource resource, LocalDate date, LocalTime startTime, LocalTime endingTime);
    boolean findByDateAndTimeInRange(Resource resource, LocalDate date, LocalTime startTime, LocalTime endingTime);
    boolean findByDateAndTimeInRangeAndStartTimeAndEndingTime(Resource resource, LocalDate date, LocalTime startTime, LocalTime endingTime);
//    List<Schedule> findByDateAndEndingTimeAndStatus(LocalDate date, LocalTime endingTime);
    void delete(Schedule schedule);
    UUID create(ScheduleDto schedule);
    Schedule changeStatus(Schedule schedule, EStatusSchedule status);
    void createAll(List<ScheduleDto> schedule);
    ScheduleDto update(ScheduleDto schedule);
    boolean validateDate(LocalDate validateDate, String condition);
    boolean validateStartTime(LocalTime validateTime, LocalDate validateDate, String condition);
    boolean validateStartTimeAndEndingTime(LocalTime startTime, LocalTime endingTime);
    boolean validateStartTimeAndEndingTimeEqual(LocalTime startTime, LocalTime endingTime);
    List<LocalDate> getBusinessDays(LocalDate startDate, LocalDate endDate);
    void validate(ResourceDto resource, LocalDate validateDate, LocalTime startTime, LocalTime endingTime);

    List<LocalDate> findDistinctAvailableDatesByServiceIdAndDateRange(UUID serviceId,LocalDate startDate, LocalDate endDate);
    List<AvailableDateDto> getAvailableDatesAndSlots(UUID resourceId, UUID businessId,  LocalDate startDate, LocalDate endDate);
    List<Schedule> findOverlappingSchedules(UUID resourceId, LocalDate date, LocalTime startTime, LocalTime endingTime);

}