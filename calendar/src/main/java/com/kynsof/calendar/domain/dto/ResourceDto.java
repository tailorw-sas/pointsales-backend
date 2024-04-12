package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.domain.dto.enumType.EResourceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourceDto implements Serializable {

    private UUID id;
    private String name;
    private String registrationNumber;
    private String language;
    private EResourceStatus status;
    private Boolean expressAppointments;
    private UUID image;
    private boolean deleted;

    public ResourceDto(UUID id,  String name, String registrationNumber, String language,
                       EResourceStatus status, Boolean expressAppointments) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.language = language;
        this.status = status;
        this.expressAppointments = expressAppointments;
    }

}
