package com.kynsof.scheduled.application.command.qualification.update;

import com.kynsof.scheduled.domain.dto.EQualificationStatus;
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