package com.kynsof.shift.application.command.block.update;

import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBlockRequest {
    private String name;
    private String code;
    private EServiceStatus status;
}