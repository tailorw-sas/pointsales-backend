package com.kynsof.calendar.domain.dto;

import com.kynsof.calendar.domain.dto.enumType.EServiceStatus;
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
    private Double normalAppointmentPrice;
    private Double expressAppointmentPrice;
    private String description;
    private Boolean applyIva;
    private int estimatedDuration;



//    public ServiceDto(UUID id, ServiceTypeDto type, String picture, String name, Double normalAppointmentPrice,
//                      Double expressAppointmentPrice, String description) {
//        this.id = id;
//        this.type = type;
//        this.picture = picture;
//        this.name = name;
//        this.normalAppointmentPrice = normalAppointmentPrice;
//        this.expressAppointmentPrice = expressAppointmentPrice;
//        this.description = description;
//    }

}
