package com.kynsof.calendar.infrastructure.repository.query;

import com.kynsof.calendar.domain.dto.ScheduleAvailabilityDto;
import com.kynsof.calendar.domain.dto.ScheduleServiceInfoDto;
import com.kynsof.calendar.domain.dto.enumType.EStatusSchedule;
import com.kynsof.calendar.infrastructure.entity.Business;
import com.kynsof.calendar.infrastructure.entity.Resource;
import com.kynsof.calendar.infrastructure.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ScheduleReadDataJPARepository extends JpaRepository<Schedule, UUID>, JpaSpecificationExecutor<Schedule> {
    Page<Schedule> findAll(Pageable pageable);
    Page<Schedule> findAll(Specification specification, Pageable pageable);
    List<Schedule> findByResourceId(UUID resourceId);
    List<Schedule> findByResourceIdAndStatus(UUID resourceId, EStatusSchedule status);
    List<Schedule> findByResourceIdAndDateAndStatus(UUID resourceId, LocalDate date, EStatusSchedule status);

//    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.endingTime >= :endingTime AND s.status = 3")
//    List<Schedule> findByDateAndEndingTimeAndStatus(LocalDate date, LocalTime endingTime);
//
//    @Query("SELECT s FROM Schedule s WHERE s.date <= :date AND s.endingTime < :time AND s.status = 0")
//    List<Schedule> findActiveSchedulesAfterDateAndTime(LocalDate date, LocalTime time);

    Schedule findByResourceAndDateAndStartTimeAndEndingTimeAndStatus(Resource resource, LocalDate date, LocalTime startTime, LocalTime endingTime, EStatusSchedule status);

    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.resource = :resource AND (:time >= s.startTime AND :time <= s.endingTime)")
    List<Schedule> findByDateAndTimeInRange(@Param("resource") Resource resource, @Param("date") LocalDate date, @Param("time") LocalTime time);

    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.resource = :resource AND (:startTime >= s.startTime AND :endingTime <= s.endingTime)")
    List<Schedule> findByDateAndTimeInRangeAndStartTimeAndEndingTime(@Param("resource") Resource resource, @Param("date") LocalDate date, @Param("startTime") LocalTime startTime, @Param("endingTime") LocalTime endingTime);

    @Query("SELECT s FROM Schedule s WHERE s.date = :date AND s.resource = :resource AND (:time >= s.startTime AND :time <= s.endingTime) AND s.id != :id")
    List<Schedule> findByDateAndTimeInRangeAndIdScheduleNotEqual(@Param("resource") Resource resource, @Param("date") LocalDate date, @Param("time") LocalTime time, @Param("id") UUID id);


    @Query("SELECT s FROM Schedule s WHERE s.resource.id = :resourceId " +
                  "AND s.date = :date " +
                  "AND ((s.startTime < :endTime AND s.endingTime > :startTime))")
    List<Schedule> findOverlappingSchedules(@Param("resourceId") UUID resourceId,
                                            @Param("date") LocalDate date,
                                            @Param("startTime") LocalTime startTime,
                                            @Param("endTime") LocalTime endingTime);



    //Keimer dev
    @Query("SELECT DISTINCT s.business FROM Schedule s " +
            "WHERE s.date = :date " +
            "AND s.service.id = :serviceId " +
            "AND s.stock > 0")
    Page<Business> findBusinessesWithAvailableStockByDateAndService(@Param("date") LocalDate date,
                                                                    @Param("serviceId") UUID serviceId,
                                                                    Pageable pageable);


    @Query("SELECT s FROM Schedule s JOIN s.service ser JOIN s.business bus " +
            "WHERE ser.id = :serviceId AND bus.id = :businessId AND s.date = :date " +
            "AND s.stock > 0")
    Page<Schedule> findSchedulesWithStockByBusinessServiceAndDate(UUID businessId, UUID serviceId, LocalDate date, Pageable pageable);


    @Query("SELECT DISTINCT s.date FROM Schedule s WHERE s.service.id = :serviceId AND s.date BETWEEN :startDate AND :endDate ORDER BY s.date ASC")
    List<LocalDate> findDistinctAvailableDatesByServiceIdAndDateRange(UUID serviceId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.kynsof.calendar.domain.dto.ScheduleAvailabilityDto(s.date, s.startTime, s.endingTime, s.id) " +
            "FROM Schedule s " +
            "WHERE s.resource.id = :resourceId " +
            "AND s.business.id = :businessId " +
            "AND s.date BETWEEN :startDate AND :endDate " +
            "AND s.status = 'AVAILABLE' " +
            "AND s.stock > 0 " +
            "ORDER BY s.date ASC, s.startTime ASC")
    List<ScheduleAvailabilityDto> findAvailableSchedulesByResourceAndBusinessAndDateRange(
            @Param("resourceId") UUID resourceId,
            @Param("businessId") UUID businessId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);



    @Query("SELECT new com.kynsof.calendar.domain.dto.ScheduleServiceInfoDto(" +
            "s.business.id, s.business.name, s.business.address, s.business.logo, " +
            "MIN(bs.price), s.business.latitude, s.business.longitude) " +
            "FROM Schedule s " +
            "JOIN s.business b " +
            "JOIN BusinessServices bs ON bs.business = b AND bs.services.id = s.service.id " +
            "WHERE s.service.id = :serviceId " +
            "AND s.date BETWEEN :startDate AND :endDate " +
            "AND s.status = 'AVAILABLE' " +
            "AND b.name LIKE CONCAT('%', :businessName, '%') " +
            "AND s.stock > 0 " +
            "GROUP BY s.business.id, s.business.name, s.business.address, s.business.logo, s.business.latitude, s.business.longitude " +
            "ORDER BY MIN(bs.price) ASC")
    Page<ScheduleServiceInfoDto> findDetailedAvailableSchedulesByResourceAndBusinessAndDateRange(
            @Param("serviceId") UUID serviceId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("businessName") String businessName,
            Pageable pageable);

    @Query("SELECT s FROM Schedule s WHERE s.initialStock = s.stock AND s.date = :date")
    List<Schedule> findSchedulesWithEqualStock(@Param("date") LocalDate date);

}
