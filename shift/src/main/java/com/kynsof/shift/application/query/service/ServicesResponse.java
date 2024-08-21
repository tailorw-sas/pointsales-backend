package com.kynsof.shift.application.query.service;

import com.kynsof.shift.domain.dto.ServiceDto;
import com.kynsof.shift.domain.dto.ServiceTypeDto;
import com.kynsof.shift.domain.dto.enumType.EServiceStatus;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class ServicesResponse implements IResponse, Serializable {
    private UUID id;
    private ServiceTypeDto type;
    private EServiceStatus status;
    private String image;
    private String name;
    private String description;
    private boolean applyIva;
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private int estimatedDuration;
    private String code;

    private  boolean preferFlag;
    private  int maxPriorityCount;
    private  int priorityCount;
    private  int currentLoop;
    private  int order;


    public ServicesResponse(ServiceDto object) {
        this.id = object.getId();
        this.type = object.getType();
        this.status = object.getStatus();
        this.image = object.getPicture();
        this.name = object.getName();
        this.description = object.getDescription();
        this.code = object.getCode();

        this.preferFlag = object.isPreferFlag();
        this.maxPriorityCount = object.getMaxPriorityCount();
        this.priorityCount = object.getPriorityCount();
        this.currentLoop = object.getCurrentLoop();
        this.order = object.getOrder();
    }

}