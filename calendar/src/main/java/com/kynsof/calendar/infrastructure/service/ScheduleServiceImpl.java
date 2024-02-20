package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.ScheduleResponse;
import com.kynsof.calendar.domain.dto.EStatusSchedule;
import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.dto.ScheduleDto;
import com.kynsof.calendar.domain.exception.BusinessException;
import com.kynsof.calendar.domain.exception.DomainErrorMessage;
import com.kynsof.calendar.domain.service.IScheduleService;
import com.kynsof.calendar.infrastructure.config.redis.CacheConfig;
import com.kynsof.calendar.infrastructure.repository.command.ScheduleWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.entity.Resource;
import com.kynsof.calendar.infrastructure.entity.Schedule;
import com.kynsof.calendar.infrastructure.entity.specifications.ScheduleSpecifications;
import com.kynsof.calendar.infrastructure.repository.query.ScheduleReadDataJPARepository;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    private ScheduleReadDataJPARepository repositoryQuery;
    
    @Autowired
    private ScheduleWriteDataJPARepository repositoryCommand;

    @Override
    public Page<Schedule> getAll(Pageable pageable) {
        return repositoryQuery.findAll(pageable);
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID resource, LocalDate date, EStatusSchedule status, LocalDate startDate, LocalDate endDate) {
        ScheduleSpecifications spec = new ScheduleSpecifications(resource, startDate, endDate, date, status);
        Page<Schedule> data = this.repositoryQuery.findAll(spec, pageable);

        List<ScheduleResponse> objects = new ArrayList<>();
        for (Schedule o : data.getContent()) {
            objects.add(new ScheduleResponse(o.toAggregate()));
        }

        return new PaginatedResponse(objects, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public List<Schedule> getAllScheduleForResource(UUID id) {
        return repositoryQuery.findByResourceId(id);
    }

    @Override
    public List<Schedule> getAllScheduleForResourceAndStatus(UUID id, EStatusSchedule status) {
        return repositoryQuery.findByResourceIdAndStatus(id, status);
    }

    @Override
    public List<Schedule> findByResourceIdAndDateAndStatus(UUID id, LocalDate date) {
        return repositoryQuery.findByResourceIdAndDateAndStatus(id, date, EStatusSchedule.ACTIVE);
    }

    @Override
    public List<Schedule> findActiveSchedulesAfterDateAndTime(LocalDate date, LocalTime time) {
        return this.repositoryQuery.findActiveSchedulesAfterDateAndTime(date, time);
    }

    @Override
    public boolean findByResourceAndDateAndStartTimeAndEndingTime(Resource resource, LocalDate date, LocalTime startTime, LocalTime endingTime) {
        Schedule _schedule = this.repositoryQuery.findByResourceAndDateAndStartTimeAndEndingTimeAndStatus(resource, date, startTime, endingTime, EStatusSchedule.ACTIVE);
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

    @Cacheable(cacheNames = CacheConfig.SCHEDULE_CACHE, unless = "#result == null")
    @Override
    public ScheduleDto findById(UUID id) {

        Optional<Schedule> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Schedule not found.");
    }

    @Override
    public List<Schedule> findByDateAndEndingTimeAndStatus(LocalDate date, LocalTime endingTime) {
        return this.repositoryQuery.findByDateAndEndingTimeAndStatus(date, endingTime);
    }

    @Override
    public void delete(UUID id) {
        ScheduleDto _schedule = this.findById(id);

        _schedule.setStatus(EStatusSchedule.INACTIVE);
        repositoryCommand.save(new Schedule(_schedule));
    }

    @Override
    public void delete(Schedule schedule) {
        schedule.setStatus(EStatusSchedule.INACTIVE);
        repositoryCommand.save(schedule);
    }

    @Override
    public Schedule create(ScheduleDto schedule) {

        schedule.setStatus(EStatusSchedule.ACTIVE);
        Schedule _schedule = repositoryCommand.save(new Schedule(schedule));

        return _schedule;
    }

    @Override
    public Schedule changeStatus(Schedule schedule, EStatusSchedule status) {

        schedule.setStatus(status);
        Schedule _schedule = repositoryCommand.save(schedule);

        return _schedule;
    }

    @Override
    public void createAll(List<ScheduleDto> schedule) {

        repositoryCommand.saveAll(schedule.stream().map(Schedule::new).collect(Collectors.toList()));
    }

    @Override
    public ScheduleDto update(ScheduleDto schedule) {
        ScheduleDto _schedule = this.findById(schedule.getId());
            if (!schedule.getDate().equals(_schedule.getDate())) {
                _schedule.setDate(schedule.getDate());
            }
            if (!schedule.getStartTime().equals(_schedule.getStartTime())) {
                List<Schedule> _schedulesStartTime = this.repositoryQuery.findByDateAndTimeInRange(new Resource(_schedule.getResource()), schedule.getDate(), schedule.getStartTime());
                if (!_schedulesStartTime.isEmpty()) {
                    throw new BusinessException(DomainErrorMessage.EXISTS_SCHEDULE_SOME_DATE_WHOSE_TIME_RANGE, "There exists a schedule on the same date, whose time range coincides at some moment with what you want to create.");
                }
                _schedule.setStartTime(schedule.getStartTime());
            }
            if (!schedule.getEndingTime().equals(_schedule.getEndingTime())) {
                List<Schedule> _schedulesStartTime = this.repositoryQuery.findByDateAndTimeInRange(new Resource(_schedule.getResource()), schedule.getDate(), schedule.getEndingTime());
                if (!_schedulesStartTime.isEmpty()) {
                    throw new BusinessException(DomainErrorMessage.EXISTS_SCHEDULE_SOME_DATE_WHOSE_TIME_RANGE, "There exists a schedule on the same date, whose time range coincides at some moment with what you want to create.");
                }
                _schedule.setEndingTime(schedule.getEndingTime());
            }
            if (!schedule.getStartTime().equals(_schedule.getStartTime()) && !schedule.getEndingTime().equals(_schedule.getEndingTime())) {
                if (this.findByResourceAndDateAndStartTimeAndEndingTime(new Resource(_schedule.getResource()), schedule.getDate(), schedule.getStartTime(), schedule.getEndingTime())) {
                    throw new BusinessException(DomainErrorMessage.EXISTS_SCHEDULE_WITH_DATE_STARTTIME_ENDTIME, "There exists a schedule with the same date, start time, and end time.");
                }
            }
            if (schedule.getStatus().equals(EStatusSchedule.INACTIVE)
                    && _schedule.getStatus().equals(EStatusSchedule.ACTIVE)) {
                _schedule.setStatus(EStatusSchedule.INACTIVE);
            }
            if (schedule.getStatus().equals(EStatusSchedule.ACTIVE)
                    && _schedule.getStatus().equals(EStatusSchedule.INACTIVE)) {

                //Un schedule se pasa de INACTIVE a ACTIVE solo se la fecha actual es menor que la fecha del schedule.
                LocalDate currentDate = LocalDate.now();
                LocalTime cTime = LocalTime.now();
                if (currentDate.isBefore(_schedule.getDate()) || (currentDate.equals(_schedule.getDate()) && cTime.isBefore(_schedule.getStartTime()))) {
                    _schedule.setStatus(EStatusSchedule.ACTIVE);
                }

            }
            repositoryCommand.save(new Schedule(_schedule));
            return _schedule;
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
        switch (condition) {
            case "EQUALS":
                return currentTime.equals(validateTime);

            case "BEFORE":
                return !(currentDate.isAfter(validateDate) || (currentDate.equals(validateDate) && currentTime.isAfter(validateTime)));

            default:
                return false;

        }
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
    public List<LocalDate> getBusinessDays(LocalDate startDate, LocalDate endDate) {
        return startDate.datesUntil(endDate)
                .filter(d -> !d.getDayOfWeek().equals(DayOfWeek.SATURDAY))
                .filter(d -> !d.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                .collect(Collectors.toList());
    }

    @Override
    public void validate(ResourceDto resource, LocalDate validateDate, LocalTime startTime, LocalTime endingTime) {
        if (this.validateStartTimeAndEndingTimeEqual(startTime, endingTime)) {
            throw new BusinessException(DomainErrorMessage.SCHEDULE_CANNOT_BE_EQUALS_STARTTIME_ENDTIME, "The start time and end time cannot be equal.");
        }
        if (!this.validateDate(validateDate, "BEFORE")
                && !this.validateDate(validateDate, "EQUALS")) {
            throw new BusinessException(DomainErrorMessage.SCHEDULE_DATE_LESS_THAN_CURRENT_DATE, "The provided date is less than the current date.");
        }
        if (!this.validateStartTime(startTime, validateDate, "BEFORE")) {
            throw new BusinessException(DomainErrorMessage.SCHEDULE_INITIAL_TIME_IS_PASSED, "The initial time has passed. Current time: " + LocalTime.now().toString());
        }
        if (!this.validateStartTimeAndEndingTime(startTime, endingTime)) {
            throw new BusinessException(DomainErrorMessage.SCHEDULE_END_TIME_IS_LESS_THAN, "The provided end time is less than the start time.");
        }
        if (this.findByResourceAndDateAndStartTimeAndEndingTime(new Resource(resource), validateDate, startTime, endingTime)) {
            throw new BusinessException(DomainErrorMessage.SCHEDULE_EXISTS_SOME_TIME_STARTTIME_EDNTIME, "There exists a schedule with the same date, start time, and end time.");
        }
        if (this.findByDateAndTimeInRange(new Resource(resource), validateDate, startTime, endingTime)) {
            throw new BusinessException(DomainErrorMessage.EXISTS_SCHEDULE_SOME_DATE_WHOSE_TIME_RANGE, "There exists a schedule on the same date, whose time range coincides at some moment with what you want to create.");//Existe horario en igual fecha, que su rango de hora coincide en algun instante de tiempo con el que se desea crear
        }
        if (this.findByDateAndTimeInRangeAndStartTimeAndEndingTime(new Resource(resource), validateDate, startTime, endingTime)) {
            throw new BusinessException(DomainErrorMessage.EXISTS_SCHEDULE_SOME_DATE_WHOSE_TIME_RANGE, "There exists a schedule on the same date, whose time range coincides at some moment with what you want to create.");//Existe horario en igual fecha, que su rango de hora coincide en algun instante de tiempo con el que se desea crear
        }
    }
}
