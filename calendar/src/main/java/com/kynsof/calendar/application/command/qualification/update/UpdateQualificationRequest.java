package com.kynsof.calendar.application.command.qualification.update;

import com.kynsof.calendar.domain.dto.enumType.EQualificationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateQualificationRequest {
    private UUID id;
    private String description;
    private EQualificationStatus status;
}