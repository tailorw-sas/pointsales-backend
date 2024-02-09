package com.kynsof.scheduled.infrastructure.repository.query;

import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.scheduled.infrastructure.entity.Resource;
import java.util.UUID;

import com.kynsof.scheduled.infrastructure.entity.Schedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleReadDataJPARepository extends JpaRepository<Schedule, UUID>, JpaSpecificationExecutor<Schedule> {
    Page<Schedule> findAll(Pageable pageable);
    Page<Schedule> findAll(Specification specification, Pageable pageable);
    List<Schedule> findByResourceId(UUID resourceId);
    List<Schedule> findByResourceIdAndStatus(UUID resourceId, EStatusSchedule status);
    List<Schedule> findByResourceIdAndDateAndStatus(UUID resourceId, LocalDate date, EStatusSchedule status);

    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.endingTime >= :endingTime AND s.status = 3")
    List<Schedule> findByDateAndEndingTimeAndStatus(LocalDate date, LocalTime endingTime);

    @Query("SELECT s FROM Schedule s WHERE s.date <= :date AND s.endingTime < :time AND s.status = 0")
    List<Schedule> findActiveSchedulesAfterDateAndTime(LocalDate date, LocalTime time);

    Schedule findByResourceAndDateAndStartTimeAndEndingTimeAndStatus(Resource resource, LocalDate date, LocalTime startTime, LocalTime endingTime, EStatusSchedule status);

    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.resource = :resource AND (:time >= s.startTime AND :time <= s.endingTime)")
    List<Schedule> findByDateAndTimeInRange(@Param("resource") Resource resource, @Param("date") LocalDate date, @Param("time") LocalTime time);

    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.resource = :resource AND (:startTime >= s.startTime AND :endingTime <= s.endingTime)")
    List<Schedule> findByDateAndTimeInRangeAndStartTimeAndEndingTime(@Param("resource") Resource resource, @Param("date") LocalDate date, @Param("startTime") LocalTime startTime, @Param("endingTime") LocalTime endingTime);

    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.resource = :resource AND (:time >= s.startTime AND :time <= s.endingTime) AND s.id != :id")
    List<Schedule> findByDateAndTimeInRangeAndIdScheduleNotEqual(@Param("resource") Resource resource, @Param("date") LocalDate date, @Param("time") LocalTime time, @Param("id") UUID id);

}
