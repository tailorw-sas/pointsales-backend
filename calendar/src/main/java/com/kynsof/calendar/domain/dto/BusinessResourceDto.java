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
public class BusinessResourceDto {
    private UUID id;
    private BusinessDto business;
    private ResourceDto resource;
    private LocalDateTime createdAt;

    public BusinessResourceDto(UUID id, BusinessDto business, ResourceDto resource) {
        this.id = id;
        this.business = business;
        this.resource = resource;
    }

}
