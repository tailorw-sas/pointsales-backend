package com.kynsof.shift.application.command.place.create;

import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreatePlaceRequest {

    private String name;
    private EServiceStatus status;
    private String code;
    private UUID block;
    private UUID business;

}
