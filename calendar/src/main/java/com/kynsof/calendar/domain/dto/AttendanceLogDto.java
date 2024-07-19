package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.domain.dto.enumType.AttentionLocalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceLogDto {
    private UUID id;
    private ResourceDto resource;
    private BusinessDto business;
    private ServiceDto service;
    private AttentionLocalStatus status;
    private String local;
}
