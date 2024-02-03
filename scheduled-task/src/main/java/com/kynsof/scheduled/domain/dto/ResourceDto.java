package com.kynsof.scheduled.domain.dto;

import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResourceDto {

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

}
