package com.kynsof.calendar.application.command.business.create;

import com.kynsof.calendar.domain.dto.enumType.EBusinessStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessRequest {

    private String name;
    private String description;
    private byte[] logo;
    private String ruc;
    private EBusinessStatus status;

}
