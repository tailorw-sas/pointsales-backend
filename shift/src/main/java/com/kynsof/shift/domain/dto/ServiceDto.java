package com.kynsof.shift.domain.dto;

import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto implements Serializable {
    private UUID id;
    private ServiceTypeDto type;
    private EServiceStatus status;
    private String picture;
    private String name;
    private String description;
    private String code;

    private boolean preferFlag;
    private int maxPriorityCount = 0;
    private int priorityCount = 0;
    private int currentLoop = 0;
    private int order = 0;

}
