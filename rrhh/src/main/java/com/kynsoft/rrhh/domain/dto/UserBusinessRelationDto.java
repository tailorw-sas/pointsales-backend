package com.kynsoft.rrhh.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserBusinessRelationDto {
    private UUID id;
    private UserSystemDto userSystem;
    private BusinessDto business;
    private String state;
    private LocalDateTime date;
}
