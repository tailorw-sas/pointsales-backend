package com.kynsof.calendar.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessServicesDto {
    private UUID id;
    private BusinessDto business;
    private ServiceDto service;
    private LocalDateTime createdAt;

    public BusinessServicesDto(UUID id, BusinessDto business, ServiceDto service) {
        this.id = id;
        this.business = business;
        this.service = service;
    }

}
