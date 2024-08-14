package com.kynsof.calendar.application.query.businessService.GetServicesSimpleByBusinessId;

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
}
