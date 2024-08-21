package com.kynsof.shift.application.command.service.create;

import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateServiceRequest {

    private UUID type;
    private String image;
    private String name;
    private String description;
    private EServiceStatus status;
    private String code;


    private boolean preferFlag;
    private int maxPriorityCount;
    private int priorityCount;
    private int currentLoop;
    private int order;
}
