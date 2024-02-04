package com.kynsof.scheduled.domain.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {
    private UUID id;
    private EServiceType type;
    private EServiceStatus status;
    private String picture;
    private String name;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public ServiceDto(UUID id, EServiceType type, String picture, String name, Double normalAppointmentPrice, Double expressAppointmentPrice, String description) {
        this.id = id;
        this.type = type;
        this.picture = picture;
        this.name = name;
        this.normalAppointmentPrice = normalAppointmentPrice;
        this.expressAppointmentPrice = expressAppointmentPrice;
        this.description = description;
    }

}
