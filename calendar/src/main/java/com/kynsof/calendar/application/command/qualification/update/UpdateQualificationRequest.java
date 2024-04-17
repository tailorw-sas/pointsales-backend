package com.kynsof.calendar.application.command.qualification.update;

import com.kynsof.calendar.domain.dto.enumType.EQualificationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQualificationRequest {
    private String description;
    private EQualificationStatus status;
}