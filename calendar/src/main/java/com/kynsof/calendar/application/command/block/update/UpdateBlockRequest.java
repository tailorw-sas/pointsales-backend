package com.kynsof.calendar.application.command.block.update;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBlockRequest {
    private String name;
    private String code;
    private EServiceStatus status;
}