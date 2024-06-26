package com.kynsof.calendar.application.command.block.create;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBlockRequest {

    private String name;
    private EServiceStatus status;
    private String code;

}
