package com.kynsof.shift.application.command.block.create;

import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBlockRequest {

    private String name;
    private EServiceStatus status;
    private String code;
    private UUID business;

}
