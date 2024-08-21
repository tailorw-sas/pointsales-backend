package com.kynsof.shift.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kynsof.shift.domain.dto.AttendanceLogDto;
import com.kynsof.shift.domain.dto.enumType.AttentionLocalStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AttendanceLog {
    @Id
    private UUID id;

    @JsonIgnoreProperties({"picture", "services", "qualifications"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @JsonIgnoreProperties({"logo", "description", "resources", "services", "schedules", "receipts"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Services service;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private AttentionLocalStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "place_id")
    private Place place;


    public AttendanceLog(AttendanceLogDto attendanceLogDto) {
        this.id = attendanceLogDto.getId();
        this.business = new Business(attendanceLogDto.getBusiness());
        this.service = new Services(attendanceLogDto.getService());
        this.resource = new Resource(attendanceLogDto.getResource());
        this.place = new Place(attendanceLogDto.getPlace());
        this.status = attendanceLogDto.getStatus();
    }

    public  AttendanceLogDto toAggregate() {
        return new AttendanceLogDto(id, resource.toAggregate(), business.toAggregate(), service.toAggregate(),status,
                place.toAggregate());
    }

}
