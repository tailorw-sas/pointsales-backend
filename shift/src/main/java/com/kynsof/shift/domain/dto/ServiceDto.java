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
    private String externalCode;

    public ServiceDto(UUID id, ServiceTypeDto type, EServiceStatus status, String picture, String name,
                      String description, String code, boolean preferFlag,
                      int maxPriorityCount, int priorityCount, int currentLoop, int order) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.code = code;
        this.preferFlag = preferFlag;
        this.maxPriorityCount = maxPriorityCount;
        this.priorityCount = priorityCount;
        this.currentLoop = currentLoop;
        this.order = order;
    }

    public ServiceDto(UUID id, ServiceTypeDto type, EServiceStatus status, String picture, String name,
                      String description, String code,
                      boolean preferFlag, int maxPriorityCount,
                      int priorityCount, int currentLoop, int order,
                      String externalCode) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.picture = picture;
        this.name = name;
        this.description = description;
        this.code = code;
        this.preferFlag = preferFlag;
        this.maxPriorityCount = maxPriorityCount;
        this.priorityCount = priorityCount;
        this.currentLoop = currentLoop;
        this.order = order;
        this.externalCode = externalCode;
    }
}
