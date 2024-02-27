package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourceDto implements Serializable {

    private UUID id;

    private String picture;

    private String name;

    private String registrationNumber;

    private String language;

    private EResourceStatus status;

    private Boolean expressAppointments;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private LocalDateTime deleteAt;

    public ResourceDto(UUID id, String picture, String name, String registrationNumber, String language, EResourceStatus status, Boolean expressAppointments) {
        this.id = id;
        this.picture = picture;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.language = language;
        this.status = status;
        this.expressAppointments = expressAppointments;
    }

}
