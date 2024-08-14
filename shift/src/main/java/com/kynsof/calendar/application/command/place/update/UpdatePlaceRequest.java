package com.kynsof.calendar.application.command.place.update;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePlaceRequest {
    private String name;
    private String code;
    private EServiceStatus status;
    private UUID block;
}