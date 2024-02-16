package com.kynsof.calendar.application.command.qualification.update;

import com.kynsof.calendar.domain.dto.EQualificationStatus;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQualificationRequest {
    private UUID id;
    private String description;
    private EQualificationStatus status;
}