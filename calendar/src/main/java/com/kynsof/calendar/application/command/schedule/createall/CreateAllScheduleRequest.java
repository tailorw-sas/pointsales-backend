package com.kynsof.calendar.application.command.schedule.createall;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class CreateAllScheduleRequest {
    private UUID resourceId;
    private UUID businessId;
    private UUID serviceId;
    private LocalDate date;
    private List<ScheduleAllRequest> schedules;

    public CreateAllScheduleRequest() {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ScheduleAllRequest> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ScheduleAllRequest> schedules) {
        this.schedules = schedules;
    }

}