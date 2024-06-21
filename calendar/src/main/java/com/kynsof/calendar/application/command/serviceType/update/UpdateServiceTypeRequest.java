package com.kynsof.calendar.application.command.serviceType.update;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateServiceTypeRequest {
    private String name;
    private String image;
    private EServiceStatus status;
}