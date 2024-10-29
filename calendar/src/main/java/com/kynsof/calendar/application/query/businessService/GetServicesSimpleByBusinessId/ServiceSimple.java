package com.kynsof.calendar.application.query.businessService.GetServicesSimpleByBusinessId;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class ServiceSimple {
    private UUID id;
    private String name;
    private EServiceStatus status;
}
