package com.kynsof.calendar.application.command.schedule.createlote;

import com.kynsof.calendar.application.command.schedule.createall.ScheduleAllRequest;
import java.time.DayOfWeek;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateScheduleByLoteRequest {
    private UUID resourceId;
    private UUID businessId;
    private UUID serviceId;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ScheduleAllRequest> schedules;
    List<DayOfWeek> daysToExclude;

    public CreateScheduleByLoteRequest() {
    }

    public UUID getResourceId() {
        return resourceId;
    }

    public void setResourceId(UUID resourceId) {
        this.resourceId = resourceId;
    }

    public UUID getBusinessId() {
        return businessId;
    }

    public void setBusinessId(UUID businessId) {
        this.businessId = businessId;
    }

    public UUID getServiceId() {
        return serviceId;
    }

    public void setServiceId(UUID serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<ScheduleAllRequest> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleAllRequest> schedules) {
        this.schedules = schedules;
    }

    public List<DayOfWeek> getDaysToExclude() {
        return daysToExclude;
    }

    public void setDaysToExclude(List<DayOfWeek> daysToExclude) {
        this.daysToExclude = daysToExclude;
    }

}