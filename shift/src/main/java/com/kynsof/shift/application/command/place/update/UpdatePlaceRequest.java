package com.kynsof.shift.application.command.place.update;

import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
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