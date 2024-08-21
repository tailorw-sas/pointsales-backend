package com.kynsof.shift.application.command.serviceType.update;

import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateServiceTypeRequest {
    private String name;
    private String image;
    private EServiceStatus status;
}